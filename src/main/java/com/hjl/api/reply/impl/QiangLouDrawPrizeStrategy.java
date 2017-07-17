package com.hjl.api.reply.impl;

import com.hjl.api.reply.ReplyStrategy;
import com.hjl.message.bean.weixin.WeiXinReceiveMsg;
import com.hjl.message.bean.weixin.WeiXinReplyMsg;

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
