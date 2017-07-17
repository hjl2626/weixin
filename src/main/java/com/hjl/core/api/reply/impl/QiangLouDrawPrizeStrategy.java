package com.hjl.core.api.reply.impl;

import com.hjl.core.api.reply.ReplyStrategy;
import com.hjl.core.message.bean.weixin.WeiXinReceiveMsg;
import com.hjl.core.message.bean.weixin.replyMsg.WeiXinReplyMsg;

/**
 * Created by hjl on 2017/3/3.
 */
public class QiangLouDrawPrizeStrategy implements ReplyStrategy{
	@Override
	public WeiXinReplyMsg reply(WeiXinReceiveMsg msg) {
		return null;
	}
	
	@Override
	public String reply(String content) {
		return null;
	}
}
