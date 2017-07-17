package com.hjl.service.impl;

import com.hjl.api.reply.ReplyStrategy;
import com.hjl.config.AppConfig;
import com.hjl.constants.GlobalConstant;
import com.hjl.constants.WeiXinEventType;
import com.hjl.constants.WeiXinMsgType;
import com.hjl.context.AppContext;
import com.hjl.exception.BizException;
import com.hjl.factory.ReplyMsgFactory;
import com.hjl.message.bean.weixin.WeiXinReceiveMsg;
import com.hjl.message.bean.weixin.WeiXinReplyMsg;
import com.hjl.message.bean.weixin.WeiXinToken;
import com.hjl.message.bean.weixin.menu.Menu;
import com.hjl.message.enums.RespCodeEnum;
import com.hjl.service.WeiXinService;
import com.hjl.service.WeiXinUserService;
import com.hjl.utils.GsonUtil;
import com.hjl.utils.HttpUtil;
import com.hjl.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * WeiXinServiceImpl
 *
 * @author hjl
 * @date 2017/1/9
 */

@Service("weiXinService")
public class WeiXinServiceImpl implements WeiXinService {

	@Autowired
	private WeiXinUserService weiXinUserService;

	

	private static Logger logger = Logger.getLogger(WeiXinServiceImpl.class);


	@Override
	public String Authorize(String signature, String timestamp, String nonce, String echostr) {
		String result = "";
		if (StringUtils.isEmpty(signature) || StringUtils.isEmpty(nonce)
				|| StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(echostr)) {
			result = "";
		} else {
			if (checkAuthorize(signature, timestamp, nonce)) {
				result = echostr;
			}
		}
		return result;
	}

	@Override
	public String getToken() throws BizException {
		String result = getTokenFromRedis();
		if (StringUtils.isEmpty(result)) {
			throw new BizException(RespCodeEnum.RC_0001.code(), RespCodeEnum.RC_0001.description());
		}
		return result;
	}


	@Override
	public String getTokenFromServer() {
		String appID = AppConfig.getConfigWexinAppID();
		String appsecret = AppConfig.getConfigWexinAppsecret();
		String url = AppConfig.getConfigWexinUrl() + "token?" + "grant_type=client_credential&appid=" + appID + "&secret=" + appsecret;
		String result = HttpUtil.httpGet(url);
		WeiXinToken weiXinToken = (WeiXinToken) GsonUtil.fromJson(result, WeiXinToken.class);
		if (weiXinToken != null && StringUtils.isNotEmpty(weiXinToken.getAccessToken())) {
			return weiXinToken.getAccessToken();
		} else {
			return "";
		}
	}

	@Override
	public WeiXinReplyMsg chat(WeiXinReceiveMsg message)  {
		ReplyStrategy replyStrategy;
		
		if(WeiXinMsgType.MSG_TYPE_EVENT.equals(message.getMsgType())){
			if(StringUtils.isNotBlank(message.getEventKey())){
				String eventKey = message.getEventKey();
				String prefix = "qrscene_";
				// 扫描关注事件  未关注
				if(WeiXinEventType.EVENT_TYPE_SUB.equalsIgnoreCase(message.getEvent())){
					if(eventKey.contains(prefix)){
						String code = eventKey.substring(prefix.length());
						if(StringUtils.isNotBlank(code)){
							return ReplyMsgFactory.getTextMsg(message).setContent(code);
						}
					}
					// 扫描关注事件  已经关注
				}else if(WeiXinEventType.EVENT_TYPE_SCAN.equalsIgnoreCase(message.getEvent())){
					if(StringUtils.isNotBlank(eventKey)){
						return ReplyMsgFactory.getTextMsg(message).setContent(eventKey);
					}
				}
			}
		}
		
		if(!WeiXinMsgType.MSG_TYPE_TEXT.equals(message.getMsgType())){
			return new WeiXinReplyMsg()
					.setContent("<p>\n" +
							"\t1、活动时间为5月25日-6月1日，领奖截止时间为6月4日23：59：59，逾期未领取视为自愿放弃；\n" +
							"</p>\n" +
							"<p>\n" +
							"\t2、活动赠送话费与流量默认领取号码为绑定中国移动10086公众号的手机号码，不可更改；如到期未绑定，视为自愿放弃；\n" +
							"</p>\n" +
							"<p>\n" +
							"\t3、活动期间，每日领取的粽子仅限当日有效；\n" +
							"</p>\n" +
							"<p>\n" +
							"\t4、活动期间，用户需累计参加活动5天，才可参与终极大奖；\n" +
							"</p>\n" +
							"<p>\n" +
							"\t5、本次活动所有奖品将在6月23日前发放。 流量奖品可进入中国移动10086微信公众号，底部菜单【我的服务】-【查套餐查流量】，点击页面上【流量】的“展开详情”查询到账情况。活动流量无法共享，当月有效，流量不结转。\n" +
							"</p><a href=\"http://www.w3school.com.cn\">W3School</a>")
					.setToUserName(message.getFromUserName())
					.setFromUserName(message.getToUserName())
					.setMsgType(WeiXinMsgType.MSG_TYPE_TEXT)
					.setCreateTime(System.currentTimeMillis()/1000+"");
		}
		
		if(message.getContent().contains("抽奖")){
			replyStrategy = (ReplyStrategy)AppContext.getBeanFromSpringAppContext("numberDrawPrizeStrategy");
		}else {
			replyStrategy = (ReplyStrategy)AppContext.getBeanFromSpringAppContext("tulingReplyStrategy");
		}
		
		WeiXinReplyMsg reply = replyStrategy.reply(message);
		
		String from = message.getFromUserName();
		return reply;
	}
	
	@Override
	public String createMenu(Menu menu) throws BizException {
		String url = AppConfig.getConfigWexinUrl() +"menu/create?access_token=" + getToken();
		
		logger.info("createMenu = " +  GsonUtil.toJson(menu));
		String resp = HttpUtil.httpPostWithJson(url , GsonUtil.toJson(menu));
		return resp;
	}
	
	

	private String getTokenFromRedis() {
		String result = "";
		try {
			if (RedisUtil.exists(GlobalConstant.WEIXIN_TOKEN_KEY)) {
				result = RedisUtil.get(GlobalConstant.WEIXIN_TOKEN_KEY);
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}


	private boolean checkAuthorize(String signature, String timestamp, String nonce) {
		String token = AppConfig.getConfigWexinToken();
		boolean result = false;
		String[] data = {token , timestamp , nonce};
		Arrays.sort(data);
		String str = StringUtils.join(data, "");
		String signStr = new SimpleHash("sha1", str).toHex();
		if (signStr.equals(signature)) {
			result = true;
		}
		return result;
	}


	public static void print(Object o) {
		System.out.print(o);
	}

	public static void println(Object o) {
		System.out.println(o);
	}

	public static void main(String[] args) {
	}
}
