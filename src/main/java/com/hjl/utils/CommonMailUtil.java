package com.hjl.utils;

import com.hjl.config.AppConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;

import javax.mail.internet.MimeUtility;
import java.io.File;

/**
 * Created by hjl on 2016/12/26.
 */
public final class CommonMailUtil {

	private static Logger logger = Logger.getLogger(CommonMailUtil.class);


	public static boolean sendEMail(String tos, String subject, String body, String attachments) {

		if(StringUtils.isEmpty(tos)){
			logger.error("收件人不能为空");
			return false;
		}

		if(StringUtils.isEmpty(subject)){
			subject = "无主题";
		}

		if(StringUtils.isEmpty(body)){
			body = "";
		}

		if(StringUtils.isEmpty(attachments)){
			attachments = "";
		}


		try {
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName(AppConfig.getMailHost()); // 发送服务器
			email.setAuthentication(AppConfig.getMailUserName(), AppConfig.getMailPassword()); // 发送邮件的用户名和密码
			email.setDebug(AppConfig.getMailIsDebug());
			for (String s : tos.split(";")) {
				String[] tosAndNick = s.split(",");
				if (tosAndNick.length != 2 || StringUtils.isEmpty(tosAndNick[0]) || StringUtils.isEmpty(tosAndNick[1])) {
					continue;
				}
				email.addTo(tosAndNick[0], tosAndNick[1]); // 接收邮箱
			}

			email.setFrom("nick <" + AppConfig.getMailUserName() + ">"); // 发送邮箱
			email.setSubject(subject);// 主题
			email.addPart(body, "text/html;charset=utf-8");
			// 添加附件
			for (String s : attachments.split(",")) {
				if (StringUtils.isEmpty(s) || !new File(s).exists()) {
					continue;
				}
				EmailAttachment attachment = new EmailAttachment();
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				attachment.setPath(s);
				attachment.setName(MimeUtility.encodeText(attachment.getName())); //设置附件的中文编码
				email.attach(attachment);
			}
			// 发送邮件
			email.send();
			return true;
		} catch (Exception e) {
			logger.error("[CommonMailUtil] .sendEmail failed", e);
			return false;
		}
	}


	public static void main(String[] args) throws Exception {

	}


}
