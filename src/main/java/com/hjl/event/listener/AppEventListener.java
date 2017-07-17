package com.hjl.event.listener;

import com.hjl.config.AppConfig;
import com.hjl.constants.GlobalConstant;
import com.hjl.service.WeiXinService;
import com.hjl.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * Created by hjl on 2016/12/27.
 */

@Component
@EnableAsync
public class AppEventListener {

	@Autowired
	WeiXinService weiXinService;
	private static Logger logger = Logger.getLogger(AppEventListener.class);


	@EventListener({ContextRefreshedEvent.class})
	@Async
	public void getTokenFirst() {

		// 获取十次 确保获取到token
		for (int i = 0; i < 10; i++) {
			logger.debug(this.hashCode() + " 服务启动时 - 获取access_token 第 " + i + " 次");
			if (setToken()) {
				break;
			}
		}

	}


	private boolean setToken() {
		String token = weiXinService.getTokenFromServer();
		if (StringUtils.isNotEmpty(token)) {
			try {
				RedisUtil.set(GlobalConstant.WEIXIN_TOKEN_KEY, token);
				RedisUtil.expire(GlobalConstant.WEIXIN_TOKEN_KEY, AppConfig.getConfigWexinTokenExpire());
				return true;
			} catch (Exception e) {
				logger.error("服务启动时 写token入缓存 失败", e);
			}
			return false;
		} else {
			logger.error("从微信服务器获取token失败");
			return false;
		}
	}
}
