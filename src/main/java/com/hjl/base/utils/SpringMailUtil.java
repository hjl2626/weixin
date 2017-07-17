package com.hjl.base.utils;

import com.hjl.base.config.AppConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by hjl on 2016/12/26.
 */
public class SpringMailUtil {

	private static Logger logger = Logger.getLogger(SpringMailUtil.class);

	private static boolean sennMail(String tos, String subject, String body, List<Resource> attachments){

		if(StringUtils.isEmpty(tos)){
			logger.error("[JavaMailUtil].sendEmail 收件人不能为空");
			return false;
		}

		if(StringUtils.isEmpty(subject)){
			subject = "无主题";
		}

		if(StringUtils.isEmpty(body)){
			body = "";
		}

		try{
			JavaMailSenderImpl sender = new JavaMailSenderImpl();
			sender.setHost("smtp.163.com");
			MimeMessage msg = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true ,"utf-8");
			helper.setSubject(subject);
			helper.setText(body ,true);
			helper.setFrom(AppConfig.getMailUserName(),"nick");

			for (String s : tos.split(";")) {
				String[] tosAndNick = s.split(",");
				if (tosAndNick.length != 2 || StringUtils.isEmpty(tosAndNick[0]) || StringUtils.isEmpty(tosAndNick[1])) {
					continue;
				}
				helper.addTo(tosAndNick[0], tosAndNick[1]); // 接收邮箱
			}
			// 添加附件
			for (Resource r : attachments) {
				helper.addAttachment(MimeUtility.encodeText(r.getFilename()) ,r);
			}
			sender.setUsername(AppConfig.getMailUserName()); // 根据自己的情况,设置username
			sender.setPassword(AppConfig.getMailPassword()); // 根据自己的情况, 设置password
			Properties prop = new Properties();
			prop.put("mail.smtp.auth", AppConfig.getMailSmtpAuth()); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
			prop.put("mail.smtp.timeout", "25000");
			sender.setJavaMailProperties(prop);
			sender.send(msg);
			return true;
		}catch (Exception e){
			logger.error("[SpringMailUtil] sennMailFromClassPath failed" ,e);
			return false;
		}
	}


	public static boolean sennMailFromClassPath(String tos, String subject, String body, String attachments){
		if(attachments == null){
			attachments = "";
		}
		try{
			List<Resource> resources = new ArrayList<Resource>();
			// 添加附件
			for (String s : attachments.split(",")) {
				if (StringUtils.isEmpty(s)) {
					continue;
				}
				Resource resource = new ClassPathResource(s);
				if(resource.exists()) {
					resources.add(resource);
				}
			}
			sennMail(tos , subject ,body ,resources);
			return true;
		}catch (Exception e){
			logger.error("[SpringMailUtil] sennMailFromClassPath failed" ,e);
			return false;
		}
	}

	public static boolean sennMailFromSysPath(String tos, String subject, String body, String attachments){
		if(attachments == null){
			attachments = "";
		}
		try{
			List<Resource> resources = new ArrayList<Resource>();
			// 添加附件
			for (String s : attachments.split(",")) {
				if (StringUtils.isEmpty(s)) {
					continue;
				}
				Resource resource = new FileSystemResource(s);
				if(resource.exists()){
					resources.add(resource);
				}

			}
			sennMail(tos , subject ,body ,resources);
			return true;
		}catch (Exception e){
			logger.error("[SpringMailUtil] sennMailFromClassPath failed" ,e);
			return false;
		}
	}

	public static void main(String[] args){

		sennMailFromClassPath("799728970@qq.com,hjl" ,"" ,"<h1>测试</h1>" , "jdbc.properties,mail.properties");

	}
}
