package com.hjl.core.service;

import com.hjl.core.exception.BizException;
import com.hjl.core.message.bean.weixin.WeiXinReceiveMsg;
import com.hjl.core.message.bean.weixin.replyMsg.WeiXinReplyMsg;
import com.hjl.core.message.bean.weixin.menu.Menu;

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
