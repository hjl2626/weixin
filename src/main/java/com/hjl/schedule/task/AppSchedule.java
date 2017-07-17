package com.hjl.schedule.task;

import com.hjl.config.AppConfig;
import com.hjl.constants.GlobalConstant;
import com.hjl.service.WeiXinService;
import com.hjl.service.WeiXinUserService;
import com.hjl.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by hjl on 2016/12/28.
 */
@Component
@EnableScheduling
public class AppSchedule {


	private static Logger logger = Logger.getLogger(AppSchedule.class);

	@Autowired
	private WeiXinService weiXinService;

	@Scheduled(cron = "0 0 0/1 * * ?")   //每个小时执行一次
	public void setTokenForSure() {
		for (int i = 0; i < 10; i++) {
			logger.debug("定时器定时获取access_token 第 " + i + " 次" );
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
				logger.error("定时器写入token 入缓存失败", e);
			}
			return false;
		} else {
			logger.error("从微信服务器获取token失败");
			return false;
		}
	}
}
