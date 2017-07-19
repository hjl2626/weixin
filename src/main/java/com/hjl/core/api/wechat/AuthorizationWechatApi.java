package com.hjl.core.api.wechat;


import com.alibaba.fastjson.JSONObject;

import com.hjl.base.cache.redis.RedisPool;
import com.hjl.base.config.AppConfig;
import com.hjl.base.constants.WeiXinMsgType;
import com.hjl.base.utils.HttpUtil;
import com.hjl.base.utils.JacksonUtil;
import com.hjl.core.dto.wechat.WechatErrorRep;
import com.hjl.core.exception.ApiException;
import com.hjl.core.factory.SendMsgFactory;
import com.hjl.core.message.bean.weixin.sendMsg.SendMsgBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationWechatApi {
	
	private static Logger logger = LoggerFactory.getLogger(AuthorizationWechatApi.class);
	
	/**
	 * 获取access_token
	 * @return JSONObject
	 * @throws Exception
	 */
	public static JSONObject getAccessToken() throws Exception{
		StringBuilder url = new StringBuilder(128);
		url.append(AppConfig.Wechat.getAccessToken());
		url.append("?appid=").append(AppConfig.Wechat.getConfigWexinAppID());
		url.append("&secret=").append(AppConfig.Wechat.getConfigWexinAppsecret());
		url.append("&grant_type=").append(AppConfig.Wechat.getGrantType().split(",")[2]);
		JSONObject object;
		try {
			object = JSONObject.parseObject(
					HttpUtil.httpGet(url.toString() ,null)
			);
			if (object.containsKey("errcode")) {// 异常
				WechatErrorRep error = (WechatErrorRep) JacksonUtil.fromJson(object.toString(), WechatErrorRep.class);
				throw new ApiException("wechat.api.accesstoken.reponse.error.msg", error.getErrCode() + ","+ error.getErrMsg());
			}
		} catch (Exception e) {
			logger.error("[wechat api] [getAccessToken] 获取 access_token 失败 " , e);
			throw e;
		}
		return object;
	}
	
	/**
	 * 获取ticket
	 * @param accessToken token
	 * @return JSONObject
	 * @throws Exception
	 * 2016年5月18日下午5:09:10
	 */
	public static JSONObject getTicket(String accessToken) throws Exception {
		StringBuilder str = new StringBuilder(256);
		str.append(AppConfig.Wechat.getGetticket());
		str.append("?access_token=").append(accessToken);
		str.append("&type=").append(AppConfig.Wechat.getWechatType());
		JSONObject object = null;
		try {
			object = JSONObject.parseObject(
					HttpUtil.httpGet(str.toString() , null)
			);
			if (object.containsKey("errcode")&&!object.getString("errcode").equals("0")) {// 异常
				WechatErrorRep error = (WechatErrorRep) JacksonUtil.fromJson(object.toString(), WechatErrorRep.class);
				throw new ApiException("wechat.api.ticket.reponse.error.msg", error.getErrCode()+"," + error.getErrMsg());
			}
		} catch (Exception e) {
			logger.error("[wechat api]  [getTicket] 获取 ticket 失败 " , e);
			throw e;
		}
		return object;
	}
	
	
	/**
	 * 网页授权access_token
	 * @param code code
	 * @return JSONObject
	 * @throws Exception
	 * 2016年6月17日上午9:45:53
	 */
	public static JSONObject getNetAccessToken(String code) throws Exception  {
		StringBuilder str = new StringBuilder(256);
		str.append(AppConfig.Wechat.getNetAccessToken());
		str.append("?appid=").append(AppConfig.Wechat.getConfigWexinAppID());
		str.append("&secret=").append(AppConfig.Wechat.getConfigWexinAppsecret());
		str.append("&code=").append(code);
		str.append("&grant_type=").append(AppConfig.Wechat.getGrantType().split(",")[0]);
		JSONObject object;
		try {
			object = JSONObject.parseObject(
					HttpUtil.httpGet(str.toString() , null)
			);
			if (object.containsKey("errcode")&&!object.getString("errcode").equals("0")) {// 异常
				WechatErrorRep error = (WechatErrorRep) JacksonUtil.fromJson(object.toString(), WechatErrorRep.class);
				throw new ApiException("wechat.api.net.accesstoken.reponse.error.msg", error.getErrCode()+"," + error.getErrMsg());
			}
		}catch (Exception e) {
			logger.error("[wechat api]  [getNetAccessToken] 获取 NetAccessToken 失败 code = {}" ,code , e);
			throw e;
		}
		return object;
	}
	
	/**
	 * 刷新网页授权
	 * @param refreshToken refreshToken
	 * @return JSONObject
	 * @throws Exception
	 * 2016年6月17日上午9:45:58
	 */
	public static JSONObject refreshNetAccessToken(String refreshToken) throws Exception  {
		StringBuilder str = new StringBuilder(256);
        str.append(AppConfig.Wechat.getRefreshNetAccessToken());
        str.append("?appid=").append(AppConfig.Wechat.getConfigWexinAppID());
		str.append("&refresh_token=").append(refreshToken);
		str.append("&grant_type=").append(AppConfig.Wechat.getGrantType().split(",")[1]);
		JSONObject object;
		try {
			object = JSONObject.parseObject(
					HttpUtil.httpGet(str.toString() , null)
            );
			if (object.containsKey("errcode")&&!object.getString("errcode").equals("0")) {// 异常
				WechatErrorRep error = (WechatErrorRep) JacksonUtil.fromJson(object.toString(), WechatErrorRep.class);
				throw new ApiException("wechat.api.net.refresh.accesstoken.reponse.error.msg", error.getErrCode()+"," + error.getErrMsg());
			}
		}catch (Exception e) {
			logger.error("[wechat api]  [refreshNetAccessToken] 刷新 NetAccessToken 失败 " , e);
			throw e;
		}
		return object;
	}
	
	/**
	 * 获取用户信息 调用频率限制5百万次
	 * @param accessToken token
	 * @param openId openId
	 * @return JSONObject
	 * @throws Exception e
	 * 2016年6月17日上午9:46:13
	 */
	public static JSONObject getUserInfo(String accessToken, String openId) throws Exception  {
		StringBuilder str = new StringBuilder(256);
		str.append(AppConfig.Wechat.getUserInfo());
		str.append("?access_token=").append(accessToken);
		str.append("&openid=").append(openId);
		str.append("&lang=").append("zh_CN");
		JSONObject object;
		try {
			object = JSONObject.parseObject(
					HttpUtil.httpGet(str.toString() ,null)
			);
			if (object.containsKey("errcode") && !object.getString("errcode").equals("0")) {// 异常
				WechatErrorRep error = (WechatErrorRep) JacksonUtil.fromJson(object.toString(), WechatErrorRep.class);
				throw new ApiException("wechat.api.userinfo.reponse.error.msg", error.getErrCode()+"," + error.getErrMsg());
			}
			
		}catch (Exception e) {
			logger.error("[wechat api]  [getUserInfo] 获取用户信息 失败 openId = {}" , openId , e);
			throw e;
		}
		return object;
	}
	
	
	/**
	 * 获取网页授权用户信息
	 * @param accessToken 网页授权token
	 * @param openId openId
	 * @return JSONObject
	 * @throws Exception e
	 * 2016年6月17日上午9:46:29
	 */
	public static JSONObject getNetUserInfo(String accessToken, String openId) throws Exception  {
		StringBuilder str = new StringBuilder(256);
		str.append(AppConfig.Wechat.getNetUserInfo());
		str.append("?access_token=").append(accessToken);
		str.append("&openid=").append(openId);
		str.append("&lang=").append("zh_CN");
		JSONObject object = null;
		try {
			object = JSONObject.parseObject(
					HttpUtil.httpGet(str.toString() ,null)
			);
			if (object.containsKey("errcode") && !object.getString("errcode").equals("0")) {// 异常
				WechatErrorRep error = (WechatErrorRep) JacksonUtil.fromJson(object.toString(), WechatErrorRep.class);
				if("40001".equalsIgnoreCase(error.getErrCode())) {
					RedisPool.del(openId);
				}
				throw new ApiException("wechat.api.net.userinfo.reponse.error.msg", error.getErrCode() + "," + error.getErrMsg());
			}
			
		} catch (Exception e) {
			logger.error("[wechat api]  [getNetUserInfo] 获取网页用户信息 失败 openId = {}" ,openId , e);
			throw e;
		}
		return object;
	}
	
	/**
	 * 判断access_token 是否失效
	 * @param accessToken token
	 * @return WechatErrorRep
	 * @throws Exception e
	 */
	public static WechatErrorRep getWechatIp(String accessToken) throws Exception {
		StringBuilder str = new StringBuilder(256);
		str.append(AppConfig.Wechat.getWechatip());
		str.append("?access_token=").append(accessToken);
		JSONObject object;
		WechatErrorRep wechat = null;
		try {
			object = JSONObject.parseObject(
					HttpUtil.httpGet(str.toString() ,null)
			);
			logger.debug("微信IP {}" , object);
			if (object.containsKey("errcode")) {// 异常
				wechat= (WechatErrorRep) JacksonUtil.fromJson(object.toString(), WechatErrorRep.class);
			}
		} catch (Exception e) {
			logger.error("[wechat api]  [getWechatIp] 获取微信IP地址 失败 " , e);
			throw e;
		}
		return wechat;
	}
	
	/**
	 * 添加菜单
	 * @param token token
	 * @param param 菜单参数 json
	 * @return JSONObject
	 * @throws Exception e
	 */
	public static JSONObject addMenu(String token,String param) throws Exception{
		StringBuilder url = new StringBuilder(256);
		url.append(AppConfig.Wechat.getAddMenu());
		url.append("?access_token=").append(token);
		JSONObject object;
		try {
			object = JSONObject.parseObject(
					HttpUtil.postJson(url.toString(), param)
			);
		} catch (Exception e) {
			logger.error("[wechat api]  [addMenu] 添加菜单 失败 param = {} " , param , e);
			throw e;
		}
		return object;
	}
	
	
	/**
	 * 删除当前菜单
	 * @param token token
	 * @return JSONObject
	 * @throws Exception e
	 */
	public static JSONObject deleteMenu(String token) throws Exception{
		StringBuilder url = new StringBuilder(256);
		url.append(AppConfig.Wechat.getDelMenu());
		url.append("?access_token=").append(token);
		JSONObject object ;
		try {
			object = JSONObject.parseObject(
					HttpUtil.httpGet(url.toString() ,null)
			);
		} catch (Exception e) {
			logger.error("[wechat api]  [deleteMenu] 删除菜单 失败 " , e);
			throw e;
		}
		return object;
	}
	
	/**
	 * 获取带参数二维码  整形sceneId
	 * @param token token
	 * @param sceneId sceneId
	 * @return JSONObject
	 * @throws Exception e
	 */
	public static JSONObject getQrcode(String token , String sceneId) throws Exception{
		StringBuilder url = new StringBuilder(256);
		url.append(AppConfig.Wechat.getGetQrcode());
		url.append("?access_token=").append(token);
		
		JSONObject objectt = new JSONObject();
		objectt.put("action_name", "QR_LIMIT_SCENE");
		
		JSONObject objectnt = new JSONObject();
		JSONObject objectnnt = new JSONObject();
		objectnnt.put("scene_id", sceneId);
		objectnt.put("scene", objectnnt);
		objectt.put("action_info", objectnt);
		
		JSONObject object;
		try {
			object = JSONObject.parseObject(
					HttpUtil.postJson(url.toString(),objectt.toString())
			);
		} catch (Exception e) {
			logger.error("[wechat api]  [getQrcode] 获取整形带参数二维码 失败 sceneId = {} " , sceneId , e);
			throw e;
		}
		return object;
	}
	
	/**
	 * 获取带参数二维码  字符串sceneStr
	 * @param token token
	 * @param sceneStr sceneStr
	 * @return JSONObject
	 * @throws Exception e
	 */
	public static JSONObject getQrcodeStr(String token , String sceneStr) throws Exception{
		StringBuilder url = new StringBuilder(256);
		url.append(AppConfig.Wechat.getGetQrcode());
		url.append("?access_token=").append(token);
		
		JSONObject objectt = new JSONObject();
		objectt.put("action_name", "QR_LIMIT_STR_SCENE");
		
		JSONObject objectnt = new JSONObject();
		JSONObject objectnnt = new JSONObject();
		objectnnt.put("scene_str", sceneStr);
		objectnt.put("scene", objectnnt);
		objectt.put("action_info", objectnt);
		
		JSONObject object;
		try {
			object = JSONObject.parseObject(
					HttpUtil.postJson(url.toString(),objectt.toString())
			);
		} catch (Exception e) {
			logger.error("[wechat api]  [getQrcodeStr] 获取字符串带参数二维码 失败 sceneStr = {} " , sceneStr , e);
			throw e;
		}
		return object;
	}
	
	/**
	 * 微信消息群发  根据TAGID
	 * @param msg 消息
	 * @param token token
	 * @return JSONObject
	 * @throws Exception e
	 */
	public static JSONObject sendMsgAll(String token ,SendMsgBase msg ) throws Exception {
		StringBuilder reqUrl = new StringBuilder(256);
		reqUrl.append(AppConfig.Wechat.getMsgSendAll());
		reqUrl.append("?access_token=").append(token);
		
		JSONObject object;
		try {
			object = JSONObject.parseObject(
					HttpUtil.postJson(reqUrl.toString(), JSONObject.toJSONString(msg))
			);
		}catch (Exception e){
			logger.error("[wechat api]  [sendMsgAll] 根据tagsId 群发消息 失败 msg = {}" , msg , e);
			throw e;
		}
		
		return object;
	}
	
	
	/**
	 * 微信消息群发  根据openIds
	 * @param msg 消息
	 * @param token token
	 * @return JSONObject
	 * @throws Exception e
	 */
	public static JSONObject sendMsgByOpenIds(SendMsgBase msg , String token) throws Exception {
		String reqUrl = AppConfig.Wechat.getMsgSendByOpenIds()  + "?access_token="+token;
		JSONObject object;
		try {
			object = JSONObject.parseObject(
					HttpUtil.postJson(reqUrl, JSONObject.toJSONString(msg))
			);
		}catch (Exception e){
			logger.error("[wechat api]  [sendMsgByOpenIds] 根据openIds 群发消息 失败 msg = {}" , msg , e);
			throw e;
		}
		
		return object;
	}
	
	/**
	 * 添加素材
	 * @param file 文件
	 * @param token token
	 * @param type 类型 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @param title 视频标题
	 * @param intro 视频简介  type 为video时必传
	 * @return JSONObject
	 * @throws IOException e
	 */
	public static JSONObject addMaterial(File file , String token , String type , String title , String intro) throws IOException {
		StringBuilder url = new StringBuilder(200);
		url.append(AppConfig.Wechat.getAddMaterial());
		url.append("?access_token=").append(token).append("&type=").append(type);
		Map<String, Object> param = null;
		Map<String , File> fileParam = new HashMap<>();
		fileParam.put("media" , file);
		if(WeiXinMsgType.MSG_TYPE_VIDEO.equalsIgnoreCase(type)){
			param = new HashMap<>();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("title" , title);
			jsonObject.put("introduction" , intro);
			param.put("description" , jsonObject.toString());
		}
		JSONObject object;
		try{
			object = JSONObject.parseObject(
					HttpUtil.uploadFiles(url.toString() , fileParam , param)
			);
		}catch (IOException e){
			logger.error("[wechat api]  [addMaterial] 添加永久素材 失败 type = {}" , type , e);
			throw e;
		}
		return object;
	}
	
	/**
	 * 批量获取素材
	 * @param token token
	 * @param type 类型
	 * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	 * @param count 返回素材的数量，取值在1到20之间
	 * @return JSONObject <a href='https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738734' />
	 * @throws IOException e
	 */
	public static JSONObject batchGetMaterial(String token , String type , Integer offset , Integer count) throws IOException {
		StringBuilder url = new StringBuilder(256);
		url.append( AppConfig.Wechat.getBatchGetMaterial());
		url.append("?access_token=").append(token);
		Map<String, Object> param = new HashMap<>();
		param.put("type" , type);
		param.put("offset" , offset);
		param.put("count" , count);
		
		JSONObject object;
		try{
			object = JSONObject.parseObject(
					HttpUtil.postJson(url.toString() , JSONObject.toJSONString(param))
			);
		}catch (IOException e){
			logger.error("[wechat api]  [batchGetMaterial] 批量获取永久素材 失败 type = {} , offset = {} , count = {} " ,type , offset , count , e);
			throw e;
		}
		return object;
	}
	
	/**
	 * 长链接转短链接
	 * @param token token
	 * @param longUrl 长链接
	 * @return JSONObject
	 * @throws IOException e
	 */
	public static JSONObject urlLong2Short(String token , String longUrl) throws IOException {
        StringBuilder url = new StringBuilder(256);
        url.append(AppConfig.Wechat.getUrlShort2Long());
        url.append("?access_token=").append(token);
        Map<String, Object> param = new HashMap<>();
        param.put("action" , "long2short");
        param.put("long_url" , longUrl);

        JSONObject object;
        try{
            object = JSONObject.parseObject(
                    HttpUtil.postJson(url.toString() , JSONObject.toJSONString(param))
            );
        }catch (IOException e){
            logger.error("[wechat api]  [urlLong2Short] 长链接转短链接失败 失败 longUrl = {} " , longUrl , e);
            throw e;
        }
        return object;
	}
	public static void main(String[] args) throws Exception {
		String token = "ehnbDUOMgx8hH_mcIIdPGdjdewxGz4WJEz6dgYob3ksNPUx5tg2AyHzFfY2E6DlTeESvt1xB9tmzZcauJvtpfTSj_QwE_-bdB_qiaXB-SI-obhR17g5Lsq4frNrK6cLjAXXcAFAHEE";
		SendMsgBase base = SendMsgFactory.getTextMsg(true , null , "测试消息");
		System.out.println(urlLong2Short(token , "http://baidu.com").get("short_url"));
	}
}
