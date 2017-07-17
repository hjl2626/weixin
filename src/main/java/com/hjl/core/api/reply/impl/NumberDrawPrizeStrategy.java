package com.hjl.core.api.reply.impl;

import com.hjl.core.api.reply.ReplyStrategy;
import com.hjl.base.constants.RedisKey;
import com.hjl.base.constants.WeiXinMsgType;
import com.hjl.core.message.bean.weixin.WeiXinReceiveMsg;
import com.hjl.core.message.bean.weixin.WeiXinReplyMsg;
import com.hjl.base.utils.RedisUtil;
import com.hjl.base.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * Created by hjl on 2017/3/2.
 */
@Component("numberDrawPrizeStrategy")
public class NumberDrawPrizeStrategy implements ReplyStrategy {
	
	private static Logger logger = Logger.getLogger(NumberDrawPrizeStrategy.class);
	
	private static String activityCode = "ac2dd222def";
	@Override
	public WeiXinReplyMsg reply(WeiXinReceiveMsg msg)  {
		WeiXinReplyMsg reply = new WeiXinReplyMsg()
				.setMsgType(WeiXinMsgType.MSG_TYPE_TEXT)
				.setCreateTime(System.currentTimeMillis()+"")
				.setFromUserName(msg.getToUserName())
				.setToUserName(msg.getFromUserName());
		try {
			return reply.setContent("你的抽奖活动CODE为 " + getDrawCode(msg.getFromUserName() ,activityCode ,6));
		} catch (Exception e) {
			logger.error("返回抽奖CODE失败" ,e);
			return reply.setContent("系统错误 请稍后再试");
		}
	}
	
	@Override
	public String reply(String content) {
		return null;
	}
	
	
	
	/**
	 * 根据openid + 活动 id得到code
	 * @param openId code
	 * @return String
	 * @throws Exception
	 */
	private String getDrawCode(String openId,String activityCode,Integer len) throws Exception {
		Long begin = System.currentTimeMillis();
		Jedis jedis = null;
		String usersCodeKey = RedisKey.NumDrawPrize.USER_CODE_KEY + activityCode;
		String recycleCodeKey = RedisKey.NumDrawPrize.RECYCLE_CODE_KEY + activityCode;
		String assignedCodeKey = RedisKey.NumDrawPrize.ASSIGNED_CODE_KEY + activityCode;
		String code;
		try {
			jedis = RedisUtil.getPool().getResource();
			code = jedis.lpop(recycleCodeKey);
			if(code == null){
				code = StringUtil.getRandomNumber(len);
				Long index = 0L;
				while (jedis.hexists(assignedCodeKey ,code)){
					index++;
					logger.debug("重新分配 " + index);
					code = Long.valueOf(code) + index + "";
				}
			}
			
			// 是否第一次获取
			if(jedis.hexists(usersCodeKey ,openId)){
				String old_code = jedis.hget(usersCodeKey , openId);
				// 删除 user_code
				jedis.hdel(usersCodeKey ,openId);
				// 删除已分配
				jedis.hdel(assignedCodeKey,old_code);
				// 回收old_code
				jedis.rpush(recycleCodeKey, old_code);
			}
			// 分配用户code
			jedis.hset(usersCodeKey ,openId ,code);
			// 标记code 已分配
			jedis.hset(assignedCodeKey,code ,"true");
			logger.debug("generate code time = " + (System.currentTimeMillis() - begin));
			return code;
		}catch (Exception e){
			logger.error(String.format("redis getCode openId = %s failed" ,openId) , e);
			throw e;
		}finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}
}
