package com.hjl.base.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by hjl on 2017/1/10.
 */
public final class HttpUtil {

	private static Logger logger = Logger.getLogger(HttpUtil.class);
	
	
	private static CloseableHttpClient client = HttpClients.createDefault();
	private HttpUtil(){

	}

	/**
	 * HttpGet
	 * @param url
	 * @return
	 */
	public static String httpGet(String url){
		
		CloseableHttpResponse response = null;
		String result = "";
		HttpGet get = new HttpGet(url);
		try {
			response = client.execute(get);
			result = EntityUtils.toString(response.getEntity() , "utf-8");
			return result;
		} catch (Exception e){
			logger.error("请求url = " + url + " FAILED", e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return result;
	}

	/**
	 * HttpPost
	 * @param url
	 * @return
	 */
	public static String httpPost(String url , Map<String ,Object> data) {
		CloseableHttpResponse response = null;
		HttpPost post = new HttpPost(url);
		String result ="";
		if (data == null || data.size() == 0) {
			logger.info("post 数据为空");
		} else {

			Iterator<Map.Entry<String ,Object>> ite= data.entrySet().iterator();
			List<NameValuePair> postData = new ArrayList<>();
			while (ite.hasNext()){
				Map.Entry<String ,Object> entry = ite.next();
				NameValuePair pair = new BasicNameValuePair(entry.getKey() ,entry.getValue().toString());
				postData.add(pair);
			}
			try {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postData);
				post.setEntity(entity);
				response = client.execute(post);
				if(response != null) {
					result = EntityUtils.toString(response.getEntity() , "utf-8");
				}
				return result;
			} catch (Exception e) {
				logger.error("请求url = " + url + " FAILED", e);
			} finally {
				try {
					if (response != null) {
						response.close();
					}
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
		return result;
	}


	/**
	 * HttpPostJson
	 * @param url
	 * @return
	 */
	public static String httpPostWithJson(String url , String content) {
		CloseableHttpResponse response = null;
		HttpPost post = new HttpPost(url);
		String result ="";
		try {
			StringEntity entity = new StringEntity(content,"utf-8");//解决中文乱码问题
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			post.setEntity(entity);
			response = client.execute(post);
			if(response != null) {
				result = EntityUtils.toString(response.getEntity() , "utf-8");
				EntityUtils.consume(response.getEntity());
			}
			return result;
		} catch (Exception e) {
			logger.error("请求url = " + url + " FAILED", e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return result;
	}

	public static void main(String[] args){
		System.out.print(httpGet("https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=13_1"));
	}

}
