package com.hjl.core.service;

import com.hjl.core.exception.BizException;
import com.hjl.core.message.bean.weixin.WeiXinReceiveMsg;
import com.hjl.core.message.bean.weixin.replyMsg.WeiXinReplyMsg;
import com.hjl.core.message.bean.weixin.menu.Menu;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * IndexService
 *
 * @author hjl
 * @date 2017/1/9
 */


public interface WeiXinService {

    String Authorize(String signature ,String timestamp, String nonce ,String echostr);
    String getToken() throws BizException;
    String getTokenFromServer() throws IOException, URISyntaxException;
    
    WeiXinReplyMsg chat(WeiXinReceiveMsg message) throws IOException;
	
	String createMenu(Menu menu) throws BizException, IOException;
}
