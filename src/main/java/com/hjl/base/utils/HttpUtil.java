package com.hjl.base.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hjl on 2017/1/10.
 */
public final class HttpUtil {
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	public static final Integer connTimeout=20000;
	public static final Integer readTimeout=20000;
	public static final String charset="UTF-8";
	private static HttpClient client = null;
	
	private static RequestConfig.Builder customReqConf = null;
	
	
	static {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(128);
		cm.setDefaultMaxPerRoute(128);
		client = HttpClients.custom().setConnectionManager(cm).build();
		// 设置参数
		customReqConf = RequestConfig.custom();
		if (connTimeout != null) {
			customReqConf.setConnectTimeout(connTimeout);
		}
		if (readTimeout != null) {
			customReqConf.setSocketTimeout(readTimeout);
		}
	}
	
	/**
	 * 上传文件接口
	 * @param reqUrl 请求地址
	 * @param files 文件列表
	 * @param param post 参数
	 * @return res
	 * @throws IOException
	 */
	public static String uploadFiles(String reqUrl , Map<String , File> files , Map<String, Object> param) throws IOException {
		HttpPost httppost = null;
		String res = "";
		try {
			httppost = new HttpPost(reqUrl);
			httppost.setConfig(customReqConf.build());
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			
			if(param != null && param.size() > 0){
				for(Map.Entry<String , Object> entry : param.entrySet()){
					builder.addTextBody(entry.getKey() , entry.getValue().toString());
				}
			}
			
			if(files != null && files.size() > 0){
				for(Map.Entry<String , File> entry: files.entrySet()){
					builder.addBinaryBody(entry.getKey() , entry.getValue());
				}
			}
			httppost.setEntity(builder.build());
			
			HttpResponse response = client.execute(httppost);
			
			int statusCode = response.getStatusLine().getStatusCode();
			
			
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity resEntity = response.getEntity();
				res = EntityUtils.toString(resEntity);
				logger.debug("[HttpUtil uploadFiles] 调用完成 ,调用结果 {}" ,res);
				HttpClientUtils.closeQuietly(response);
			}
			
		} catch (Exception e){
			logger.error("[HttpUtil uploadFiles] 上传文件失败 {} " ,e);
			throw e;
		} finally {
			if(httppost != null){
				httppost.releaseConnection();
			}
		}
		
		return res;
	}
	
	/**
	 * GET 请求
	 * @param reqUrl 请求地址
	 * @param params 请求参数
	 * @return res
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static String httpGet(String reqUrl ,Map<String, Object> params) throws IOException, URISyntaxException {
		HttpGet httpGet = null;
		String res = "";
		try {
			httpGet = new HttpGet();
			
			URIBuilder builder = new URIBuilder(reqUrl);
			
			if(params != null && params.size() > 0){
				for(Map.Entry<String , Object> entry : params.entrySet()) {
					builder.addParameter(entry.getKey() , entry.getValue().toString());
				}
			}
			httpGet.setURI(builder.build());
			
			HttpResponse response = client.execute(httpGet);
			
			int statusCode = response.getStatusLine().getStatusCode();
			
			
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity resEntity = response.getEntity();
				res = EntityUtils.toString(resEntity);
				logger.debug("[HttpUtil httpGet] 调用完成 ,调用结果 {}" ,res);
				HttpClientUtils.closeQuietly(response);
			}
			
		} catch (URISyntaxException ex){
			logger.error("[HttpUtil httpGet] URI 语法错误");
			throw ex;
		}
		catch (Exception e){
			logger.error("[HttpUtil httpGet] GET 请求失败 {} " ,e);
			throw e;
		} finally {
			if(httpGet != null){
				httpGet.releaseConnection();
			}
		}
		
		return res;
	}
	
	
	/**
	 * post 请求
	 * @param reqUrl 请求地址
	 * @param params 请求参数
	 * @return res
	 * @throws IOException
	 */
	public static String httpPost(String reqUrl ,Map<String, Object> params) throws IOException {
		HttpPost httpPost = null;
		String res = "";
		try {
			httpPost = new HttpPost(reqUrl);
			
			if(params != null && params.size() > 0){
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
				}
				
				httpPost.setEntity(new UrlEncodedFormEntity(nvps , charset));
				
			}
			HttpResponse response = client.execute(httpPost);
			
			int statusCode = response.getStatusLine().getStatusCode();
			
			
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity resEntity = response.getEntity();
				res = EntityUtils.toString(resEntity);
				logger.debug("[HttpUtil httpPost] 调用完成 ,调用结果 {}" ,res);
				HttpClientUtils.closeQuietly(response);
			}
		} catch (IOException e) {
			logger.error("[HttpUtil httpPost] POST 请求失败 {} " ,e);
			throw e;
		} finally {
			if(httpPost != null){
				httpPost.releaseConnection();
			}
		}
		
		return res;
	}
	
	
	/**
	 * post json 请求
	 * @param reqUrl 请求地址
	 * @param jsonContent json 内容
	 * @return res
	 * @throws IOException
	 */
	public static String postJson(String reqUrl ,String jsonContent) throws IOException {
		HttpPost httpPost = null;
		String res = "";
		try {
			httpPost = new HttpPost(reqUrl);
			
			
			StringEntity entity = new StringEntity(jsonContent,"utf-8");//解决中文乱码问题
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			HttpResponse response = client.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			logger.debug("请求参数 statusCode {} {}" ,jsonContent , statusCode);
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity resEntity = response.getEntity();
				res = EntityUtils.toString(resEntity);
				logger.debug("[HttpUtil postJson] 调用完成 ,调用结果 {}" ,res);
				HttpClientUtils.closeQuietly(response);
			}
		} catch (IOException e) {
			logger.error("[HttpUtil postJson] postJson 请求失败 {} " ,e);
			throw e;
		} finally {
			if(httpPost != null){
				httpPost.releaseConnection();
			}
		}
		
		return res;
	}
	
	public static void destroy(){
		HttpClientUtils.closeQuietly(client);
	}
	public static void main(String[] args) throws IOException {
//    	File file = new File("C:\\Users\\hjl\\Desktop\\project\\NEW_EC\\B_AuthorizationService\\src\\main\\resources\\log4j.properties");
//    	Map<String , Object> param = new HashMap<>();
////    	params.put(file.getName() , file);
//        param.put("type" , "news");
//        param.put("offset" , 0);
//        param.put("count" , 10);
//    	if(file.exists()){
//
//    	    postJson("http://localhost:8082/upload/img" , JSONObject.toJSONString(param));
//        }
		
		logger.error("[Jedis set] 失败 key = {} , value = {}" , 1 , 2);
		
		
	}
}
