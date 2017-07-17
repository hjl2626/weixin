package com.hjl.api.reply;

import com.hjl.message.bean.weixin.WeiXinReceiveMsg;
import com.hjl.message.bean.weixin.WeiXinReplyMsg;

/**
 * Created by hjl on 2017/3/1.
 */
public interface ReplyStrategy {
	
	WeiXinReplyMsg reply(WeiXinReceiveMsg msg);
	
	String reply(String content);
	
}
