package com.hjl.web.controller;


import com.hjl.exception.BizException;
import com.hjl.message.bean.JsonDataResult;
import com.hjl.message.enums.RespCodeEnum;
import com.hjl.model.domain.WeiXinUser;
import com.hjl.service.WeiXinUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hjl on 2016/12/28.
 */

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private WeiXinUserService weiXinUserService;


	@RequestMapping("getUser")
	@ResponseBody
	public JsonDataResult getUser(String openId) throws BizException {
		JsonDataResult<WeiXinUser> result = new JsonDataResult<>();

		WeiXinUser user =  weiXinUserService.getUserInfo(openId);
		if(user != null){
			result.init();
			result.setData(user);
		}else {
			result.init(RespCodeEnum.RC_0001.code() ,RespCodeEnum.RC_0001.description());
		}
		return result;
	}

	@RequestMapping("getAllUser")
	@ResponseBody
	public JsonDataResult getAllUser() throws BizException {
		JsonDataResult<Object> result = new JsonDataResult<>();

		Object openids =  weiXinUserService.getAllUserInfo();
		if(openids != null){
			result.init();
			result.setData(openids);
		}else {
			result.init(RespCodeEnum.RC_0001.code() ,RespCodeEnum.RC_0001.description());
		}
		return result;
	}

}
