package com.hjl.service.impl;

import com.hjl.config.AppConfig;
import com.hjl.constants.GlobalConstant;
import com.hjl.exception.BizException;
import com.hjl.message.enums.RespCodeEnum;
import com.hjl.model.domain.WeiXinUser;
import com.hjl.service.WeiXinUserService;
import com.hjl.utils.GsonUtil;
import com.hjl.utils.HttpUtil;
import com.hjl.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by hjl on 2017/1/10.
 */
@Service("weiXinUserService")
public class WeiXinUserServiceImpl implements WeiXinUserService {

	private static Logger logger =Logger.getLogger(WeiXinUserServiceImpl.class);
	
	
	@Override
	public int save(WeiXinUser user) {
		return 0;
	}
	
	@Override
	public WeiXinUser getUserInfo(String openid) throws BizException {
		if(StringUtils.isEmpty(openid)){
			throw new BizException(RespCodeEnum.RC_0003.code() ,RespCodeEnum.RC_0003.description());
		}

		String token;
		try {
			token = RedisUtil.get(GlobalConstant.WEIXIN_TOKEN_KEY);
		}catch (Exception e){
			logger.error("获取微信access_token失败" ,e);
			return null;
		}

		if(StringUtils.isEmpty(token)){
			throw new BizException(RespCodeEnum.RC_0001.code() ,RespCodeEnum.RC_0001.description());
		}

		String url = AppConfig.getConfigWexinUrl() + "user/info?" + "access_token="+ token + "&openid=" + openid + "&lang=zh_CN";
		WeiXinUser user;
		try {
			String userStr = HttpUtil.httpGet(url);
			logger.info("getUserInfo weixin接口返回" + userStr);
			user = (WeiXinUser) GsonUtil.fromJson(userStr, WeiXinUser.class);
			// 微信返回错误
			if(StringUtils.isEmpty(user.getOpenid())){
				throw new BizException(RespCodeEnum.RC_0001.code() ,RespCodeEnum.RC_0001.description());
			}
		}catch (BizException e){
			logger.error(e);
			throw e;
		}catch (Exception e){
			logger.error(e);
			throw new BizException(RespCodeEnum.RC_0001.code() ,RespCodeEnum.RC_0001.description());
		}
		return user;
	}

	@Override
	public Object getAllUserInfo() throws BizException {

		String token;
		try {
			token = RedisUtil.get(GlobalConstant.WEIXIN_TOKEN_KEY);
		}catch (Exception e){
			logger.error("获取微信access_token失败" ,e);
			return null;
		}
//		https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID
		String url = AppConfig.getConfigWexinUrl() + "user/get?" + "access_token="+ token + "&next_openid=";

		Map<String ,Object> resp;
		try {
			String result = HttpUtil.httpGet(url);
			// 微信返回错误
			 resp =(Map) GsonUtil.fromJson(result,Map.class);
			if(resp.get("data") == null){
				throw new BizException(RespCodeEnum.RC_0001.code() ,RespCodeEnum.RC_0001.description());
			}
		}catch (BizException e){
			logger.error(e);
			throw e;
		}catch (Exception e){
			logger.error(e);
			throw new BizException(RespCodeEnum.RC_0001.code() ,RespCodeEnum.RC_0001.description());
		}
		return resp.get("data");
	}

	
}
