package com.hjl.core.api.reply.impl;

import com.hjl.core.activity.Activity;
import com.hjl.core.activity.Prize;
import com.hjl.core.api.reply.ReplyStrategy;
import com.hjl.core.factory.ReplyMsgFactory;
import com.hjl.core.message.bean.weixin.WeiXinReceiveMsg;
import com.hjl.core.message.bean.weixin.replyMsg.WeiXinReplyMsg;
import com.hjl.base.utils.JacksonUtil;
import com.hjl.base.utils.RedisUtil;
import com.hjl.base.utils.StringUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjl on 2017/3/3.
 */
public class LuckyNumberDrawPrizeStrategy implements ReplyStrategy {
	
	private static Logger logger = Logger.getLogger(LuckyNumberDrawPrizeStrategy.class);
	@Override
	public WeiXinReplyMsg reply(WeiXinReceiveMsg msg) {
		try {
			List<String> activitysStr = new ArrayList<>();
			
			for (String str : activitysStr) {
				Activity activity = (Activity) JacksonUtil.fromJson(str, Activity.class);
				if(activity.getKeyword().equals(msg.getContent())){
					if("1".equals(checkActivityState(activity))){
						return replyForActivity(msg ,activity);
					}
				}
			}
		}catch (Exception e){
			logger.error(e);
		}
		return ReplyMsgFactory.getReplyDefaultMsg(msg);
	}
	
	private WeiXinReplyMsg replyForActivity(WeiXinReceiveMsg msg, Activity activity) {
		try {
			if ("1".equals(activity.getType())) {
				String code = getCodeForLuckNumber();
				List<String> prizes = new ArrayList<>();
				return receivePrize(prizes ,code ,msg);
			} else if ("2".equals(activity.getType())) {
				String code = getCodeForQiangLou();
				List<String> prizes = new ArrayList<>();
				return receivePrize(prizes ,code ,msg);
			}
		}catch (Exception e){
			return null;
		}
		
		return ReplyMsgFactory.getReplyDefaultMsg(msg);
	}
	
	private Boolean insertUserPrize(Prize prize, String fromUserName) {
		return true;
	}
	
	private String checkActivityState(Activity activity) {
		
		return "1";
	}
	
	private String getCodeForLuckNumber(){
		return StringUtil.getRandomNumber(8);
	}
	
	private String getCodeForQiangLou() throws Exception {
		return RedisUtil.incrBy("activity_key" ,1L).toString();
	}
	
	private WeiXinReplyMsg receivePrize(List<String> prizes ,String code ,WeiXinReceiveMsg msg){
		try {
			for(String prizeStr : prizes){
				Prize prize = (Prize) JacksonUtil.fromJson(prizeStr , Prize.class);
				
				if("1".equals(prize.getRule())){
					if(code.equals(prize.getRule())){
						if(checkPrizeCondition(prize)){
							insertUserPrize(prize ,msg.getFromUserName());
							return ReplyMsgFactory.getTextMsg(msg).setContent("兑1奖");
						}else {
							return ReplyMsgFactory.getPrizeNotEnoughMsg(msg);
						}
					}
				}else if("2".equals(prize.getRule())){
					if(code.contains(prize.getRule())){
						if(checkPrizeCondition(prize)){
							insertUserPrize(prize ,msg.getFromUserName());
							return ReplyMsgFactory.getTextMsg(msg).setContent("兑2奖");
						}else {
							return ReplyMsgFactory.getPrizeNotEnoughMsg(msg);
						}
						
					}
				}else if("3".equals(prize.getRule())){
					if(code.endsWith(prize.getRule())){
						if(checkPrizeCondition(prize)){
							insertUserPrize(prize ,msg.getFromUserName());
							return ReplyMsgFactory.getTextMsg(msg).setContent("兑3奖");
						}else {
							return ReplyMsgFactory.getPrizeNotEnoughMsg(msg);
						}
						
					}
				}else if("4".equals(prize.getRule())){
					if(Integer.valueOf(code) % 2 == 0){
						if(checkPrizeCondition(prize)){
							insertUserPrize(prize ,msg.getFromUserName());
							return ReplyMsgFactory.getTextMsg(msg).setContent("兑4奖");
						}else {
							return ReplyMsgFactory.getPrizeNotEnoughMsg(msg);
						}
						
					}
				}
			}
		}catch (Exception e){
			return ReplyMsgFactory.getDrawDefaultMsg(msg);
		}
		
		return ReplyMsgFactory.getDrawDefaultMsg(msg);
	}
	
	private boolean checkPrizeCondition(Prize prize) {
		return true;
	}
	
	@Override
	public String reply(String content) {
		return null;
	}
}
