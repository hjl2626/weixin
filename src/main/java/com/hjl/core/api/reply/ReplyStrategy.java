package com.hjl.core.api.reply;

import com.hjl.core.message.bean.weixin.WeiXinReceiveMsg;
import com.hjl.core.message.bean.weixin.replyMsg.WeiXinReplyMsg;

import java.io.IOException;

/**
 * Created by hjl on 2017/3/1.
 */
public interface ReplyStrategy {
	
	WeiXinReplyMsg reply(WeiXinReceiveMsg msg) throws IOException;
	
	String reply(String content);
	
}
