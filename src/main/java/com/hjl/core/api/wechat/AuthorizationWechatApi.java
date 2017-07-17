
package com.hjl.core.api.wechat;

import com.alibaba.fastjson.JSONObject;
import com.hjl.base.cache.redis.RedisPool;
import com.hjl.base.config.AppConfig;
import com.hjl.base.utils.HttpUtil;
import com.hjl.base.utils.JacksonUtil;
import com.hjl.core.dto.wechat.WechatErrorRep;
import com.hjl.core.exception.BizException;
import org.apache.http.client.utils.HttpClientUtils;

import java.io.IOException;
import java.io.InputStream;

public class AuthorizationWechatApi {

	

	/**
	 * 获取access_token
	 *
	 * @return 2016年5月18日上午11:07:28
	 */


	public static JSONObject getAccessToken() throws BizException, IOException {
		StringBuilder url = new StringBuilder(AppConfig.Wechat.getAccessToken());
		url.append("?appid=").append(AppConfig.Wechat.getConfigWexinAppID());
		url.append("&secret=").append(AppConfig.Wechat.getConfigWexinAppsecret());
		url.append("&grant_type=").append(AppConfig.Wechat.getGrantType().split(",")[2]);
		JSONObject object = null;
		try {
			object = JSONObject.parseObject(
					HttpUtil.httpGet(url.toString()));
			if (object.containsKey("errcode")) {// 异常
				WechatErrorRep error = (WechatErrorRep) JacksonUtil.fromJson(object.toString(), WechatErrorRep.class);
				throw new BizException("wechat.api.accesstoken.reponse.error.msg", error.getErrCode() +","+ error.getErrMsg());
			}
		}catch (Exception e) {
			throw e;
		}
		return object;
	}


	/**
	 * 获取ticket
	 * @param accessToken
	 * @return
	 * @throws IOException
	 * @throws BizException
	 * @throws Exception
	 * 2016年5月18日下午5:09:10
	*/

	public static JSONObject getTicket(String accessToken) throws BizException, IOException {
		StringBuilder url = new StringBuilder("");
		url.append("access_token=").append(accessToken);
		url.append("&type=").append(AppConfig.Wechat.getWechatType());
		JSONObject object = null;
		try {
			object = JSONObject.parseObject(
					HttpUtil.httpGet(url.toString()));
			if (object.containsKey("errcode")&&!object.getString("errcode").equals("0")) {// 异常
				WechatErrorRep error = (WechatErrorRep) JacksonUtil.fromJson(object.toString(), WechatErrorRep.class);
				throw new BizException("wechat.api.ticket.reponse.error.msg", error.getErrCode()+"," + error.getErrMsg());
			}
		} catch (Exception e) {
			throw e;
		}
		return object;
	}
	
	
	

	/**
	 * 网页授权
	 * @param code
	 * @return
	 * @throws ApiException
	 * @throws Exception
	 * 2016年6月17日上午9:45:53
	 */

	public static JSONObject getNetAccessToken(String code) throws ApiException, Exception  {
		StringBuilder str = new StringBuilder("");
		str.append("appid=").append(AppConfig.Wechat.getConfigWexinAppID());
		str.append("&secret=").append(AppConfig.Wechat.getConfigWexinAppsecret());
		str.append("&code=").append(code);
		str.append("&grant_type=").append(AppConfig.Wechat.getGrantType().split(",")[0]);
		JSONObject object=null;
		try {
			object = JSONObject.parseObject(
					HttpUtil.httpGet(str.toString()));
			if (object.containsKey("errcode")&&!object.getString("errcode").equals("0")) {// 异常
				WechatErrorRep error = (WechatErrorRep) JacksonUtil.fromJson(object.toString(), WechatErrorRep.class);
				throw new BizException("wechat.api.net.accesstoken.reponse.error.msg", error.getErrCode()+"," + error.getErrMsg());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
	

    /**
	 * 刷新网页授权
	 * @param refreshToken
	 * @return
	 * @throws ApiException
	 * @throws Exception
	 * 2016年6月17日上午9:45:58
	*/

	public static JSONObject refreshNetAccessToken(String refreshToken) throws IOException  {
		StringBuilder str = new StringBuilder(AppConfig.Wechat.getRefreshNetAccessToken());
		str.append("appid=").append(AppConfig.Wechat.getConfigWexinAppID());
		str.append("&refresh_token=").append(refreshToken);
		str.append("&grant_type=").append(AppConfig.Wechat.getGrantType().split(",")[1]);
		JSONObject object=null;
		try {
			object = JSONObject.parseObject(
					HttpUtil.httpGet(str.toString()));
			if (object.containsKey("errcode")&&!object.getString("errcode").equals("0")) {// 异常
				WechatErrorRep error = (WechatErrorRep) JacksonUtil.fromJson(object.toString(), WechatErrorRep.class);
				throw new BizException("wechat.api.net.refresh.accesstoken.reponse.error.msg", error.getErrCode()+"," + error.getErrMsg());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	

    /**
	 * 获取用户信息 调用频率限制5百万次
	 * @param accessToken
	 * @param openid
	 * @return
	 * @throws ApiException
	 * @throws Exception
	 * 2016年6月17日上午9:46:13
	 */

	public static JSONObject getUserInfo(String accessToken, String openid) throws  Exception  {
		StringBuilder str = new StringBuilder(AppConfig.Wechat.getUserInfo());
		str.append("access_token=").append(accessToken);
		str.append("&openid=").append(openid);
		str.append("&lang=").append("zh_CN");
		JSONObject object = null;
		try {
			object = JSONObject.parseObject(
					HttpUtil.httpGet(str.toString()));
			if (object.containsKey("errcode")&&!object.getString("errcode").equals("0")) {// 异常
				WechatErrorRep error = (WechatErrorRep) JacksonUtil.fromJson(object.toString(), WechatErrorRep.class);
				throw new BizException("wechat.api.userinfo.reponse.error.msg", error.getErrCode()+"," + error.getErrMsg());
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
	
	

    /**
	 * 获取网页授权用户信息
	 * @param accessToken
	 * @param openid
	 * @return
	 * @throws Exception
	 * 2016年6月17日上午9:46:29
	*/

	public static JSONObject getNetUserInfo(String accessToken, String openid) throws ApiException, Exception  {
		StringBuilder str = new StringBuilder(AppConfig.Wechat.getNetUserInfo());
		str.append("access_token=").append(accessToken);
		str.append("&openid=").append(openid);
		str.append("&lang=").append("zh_CN");
		JSONObject object = null;
		try {
			object = JSONObject.parseObject(
					HttpUtil.httpGet(str.toString()));
			if (object.containsKey("errcode")&&!object.getString("errcode").equals("0")) {// 异常
				WechatErrorRep error = (WechatErrorRep) JacksonUtil.fromJson(object.toString(), WechatErrorRep.class);
                if("40001".equalsIgnoreCase(error.getErrCode())) {
                    RedisPool.del(openid);
                }
				throw new ApiException("wechat.api.net.userinfo.reponse.error.msg", error.getErrCode()+"," + error.getErrMsg());
			}
			
		}catch (ApiException ap) {
			throw ap;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
	

*
	 * 用来判断accesstoken是否失效
	 *
	 * @return 2016年5月18日上午11:07:28
	 

	public static WechatErrorRep getWechatIp(String accessToken) throws ApiException, Exception {
		StringBuffer str = new StringBuffer("");
		str.append("access_token=").append(accessToken);
		JSONObject object = null;
		WechatErrorRep wechat = null;
		try {
			object = JSONObject.fromObject(
					HttpClientUtils.sendHttpsPostRequest(WechatRequestAddress.wechatGetWechatIp, str.toString()));
			if (object.containsKey("errcode")) {// 异常
				wechat= (WechatErrorRep) JsonUtils.jsonToBean(object.toString(), WechatErrorRep.class);
			}
		} catch (ApiException ap) {
			throw ap;
		} catch (Exception e) {
			throw e;
		}
		return wechat;
	}
	
	

*
	 * 添加菜单
	 * @param token token
	 * @param param 菜单参数 json
	 * @return
	 * @throws Exception
	 

	public static JSONObject addMenu(String token,String param) throws Exception{
		StringBuffer str = new StringBuffer("");
		str.append("access_token=").append(token);
		JSONObject object = null;
		try {
			object = JSONObject.fromObject(
					HttpClientUtils.sendHttpsPostRequest(WechatRequestAddress.wechatAddMenu+"?"+str.toString(), param));
		} catch (ApiException ap) {
			throw ap;
		} catch (Exception e) {
			throw e;
		}
		return object;
	}
	
	
	

*
	 * 删除当前菜单
	 * @param token token
	 * @return
	 * @throws Exception
	 

	public static JSONObject deleteMenu(String token) throws Exception{
		StringBuffer str = new StringBuffer("");
		str.append("access_token=").append(token);
		JSONObject object = null;
		try {
			object = JSONObject.fromObject(
					HttpClientUtils.sendHttpsPostRequest(WechatRequestAddress.wechatDeleteMenu+"?"+str.toString(), null));
		} catch (ApiException ap) {
			throw ap;
		} catch (Exception e) {
			throw e;
		}
		return object;
	}
	
	

*
	 * 获取带参数二维码
	 * @param token
	 * @param sceneId
	 * @return
	 * @throws Exception
	 

	public static JSONObject getQrcode(String token , String sceneId) throws Exception{
		JSONObject objectt = new JSONObject();
		objectt.put("action_name", "QR_LIMIT_SCENE");
		
		JSONObject objectnt = new JSONObject();
		JSONObject objectnnt = new JSONObject();
		objectnnt.put("scene_id", sceneId);
		objectnt.put("scene", objectnnt);
		objectt.put("action_info", objectnt);
		
		JSONObject object;
		try {
			object = JSONObject.fromObject(
					HttpClientUtils.sendHttpsPostRequest(WechatRequestAddress.wechatGetQrcode+"?access_token="+token,objectt.toString()));
		} catch (Exception e) {
			throw e;
		}
		return object;
	}
	
	

*
	 * 微信消息群发  根据TAGID
	 * @param msg 消息
	 * @param token token
	 * @return
	 * @throws Exception
	 

	public static JSONObject sendMsgAll(SendMsgBase msg , String token) throws Exception {
		String reqUrl = WechatRequestAddress.wechatMsgSendAll+"?access_token="+token;
		JSONObject object;
		try {
			object = JSONObject.fromObject(
					HttpClientUtils.sendHttpsPostRequest(reqUrl, com.alibaba.fastjson.JSONObject.toJSONString(msg))
			);
		}catch (Exception e){
			throw e;
		}
		
		return object;
	}
	
	
	

*
	 * 微信消息群发  根据TAGID
	 * @param msg 消息
	 * @param token token
	 * @return
	 * @throws Exception
	 

	public static JSONObject sendMsgByOpenIds(SendMsgBase msg , String token) throws Exception {
		String reqUrl = WechatRequestAddress.wechatMsgSendByOpenIds  + "?access_token="+token;
		JSONObject object;
		try {
			object = JSONObject.fromObject(
					HttpClientUtils.sendHttpsPostRequest(reqUrl, com.alibaba.fastjson.JSONObject.toJSONString(msg))
			);
		}catch (Exception e){
			throw e;
		}
		
		return object;
	}
	
	
	public static JSONObject addMaterial(InputStream in , String token , String type , String media , String title , String intro) throws Exception {
		String reqUrl = WechatRequestAddress.wechatAddMaterial  + "?access_token=" + token + "&type=" + type;
    
    
    }
}

