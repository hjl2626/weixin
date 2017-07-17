package com.hjl.core.service;

import com.hjl.core.exception.BizException;
import com.hjl.core.model.domain.WeiXinUser;

/**
 * Created by hjl on 2017/1/10.
 */
public interface WeiXinUserService {

	int save(WeiXinUser user);

	public WeiXinUser getUserInfo(String openid) throws BizException;
	
	Object getAllUserInfo() throws BizException;
}
