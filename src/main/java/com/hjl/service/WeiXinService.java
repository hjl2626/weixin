package com.hjl.service;

import com.hjl.exception.BizException;
import com.hjl.message.bean.weixin.WeiXinReceiveMsg;
import com.hjl.message.bean.weixin.WeiXinReplyMsg;
import com.hjl.message.bean.weixin.menu.Menu;

/**
 * IndexService
 *
 * @author hjl
 * @date 2017/1/9
 */


public interface WeiXinService {

    String Authorize(String signature ,String timestamp, String nonce ,String echostr);
    String getToken() throws BizException;
    String getTokenFromServer();
    
    WeiXinReplyMsg chat(WeiXinReceiveMsg message);
	
	String createMenu(Menu menu) throws BizException;
}
