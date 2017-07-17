package com.hjl.service;

import com.hjl.exception.BizException;
import com.hjl.model.domain.WeiXinUser;

import java.util.List;

/**
 * Created by hjl on 2017/1/10.
 */
public interface WeiXinUserService {

	int save(WeiXinUser user);

	public WeiXinUser getUserInfo(String openid) throws BizException;
	
	Object getAllUserInfo() throws BizException;
}
