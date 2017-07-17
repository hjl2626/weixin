package com.hjl.utils;


import com.hjl.config.AppConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Date;
import java.util.Properties;

/**
 * Created by hjl on 2016/12/26.
 */
public class JavaMailUtil {


	private static Logger logger = Logger.getLogger(JavaMailUtil.class);

	public static boolean sendEmail(String tos ,String subject ,String body ,String attachments){

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

		if(StringUtils.isEmpty(attachments)){
			attachments = "";
		}
		// 1. 创建一封邮件
		Properties props = new Properties();// 用于连接邮件服务器的参数配置（发送邮件时才需要用到）
		props.setProperty("mail.transport.protocol", AppConfig.getMailTransportProtocol());   // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.host", AppConfig.getMailHost());     // 发件人的邮箱的 SMTP 服务器地址
		props.setProperty("mail.smtp.auth", String.valueOf(AppConfig.getMailSmtpAuth()));            // 请求认证，参数名称与具体实现有关
		Session session= Session.getDefaultInstance(props); // 根据参数配置，创建会话对象（为了发送邮件准备的）
		session.setDebug(AppConfig.getMailIsDebug());  // 设置为debug模式, 可以查看详细的发送 log
		Transport transport = null;
		try {
			// 3. 创建一封邮件
			MimeMessage message = createMimeMessage(session, tos.trim().split(";"), subject , body ,attachments.trim().split(","));

			// 也可以保持到本地查看
			// message.writeTo(file_out_put_stream);
			// 4. 根据 Session 获取邮件传输对象

			transport = session.getTransport();
			// 5. 使用 邮箱账号 和 密码 连接邮件服务器
			//    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
			transport.connect(AppConfig.getMailUserName(), AppConfig.getMailPassword());

			// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
			transport.sendMessage(message, message.getAllRecipients());
			// 7. 关闭连接
			return true;
		} catch (Exception e) {
			logger.error("[JavaMailUtil].sendEmail failed" ,e);
			return false;
		}finally {
			try {
				transport.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}


	private static MimeMessage createMimeMessage(Session session , String[] tos ,String subject ,String body ,String[] attachments) throws Exception {
		try{
			// 1. 创建邮件对象


			MimeMessage message = new MimeMessage(session);

			// 2. From: 发件人
			message.setFrom(new InternetAddress(AppConfig.getMailUserName(), "", "UTF-8"));
			for (String s : tos) {
				// 3. To: 收件人（可以增加多个收件人、抄送、密送）
				String[] toAndNick = s.split(",");
				if (toAndNick.length != 2 || StringUtils.isEmpty(toAndNick[0]) || StringUtils.isEmpty(toAndNick[1])) {
					continue;
				}
				message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toAndNick[0], toAndNick[1], "UTF-8"));
			}
			// 4. Subject: 邮件主题
			message.setSubject(subject);
	    /*
         * 下面是邮件内容的创建:
         */
			MimeMultipart mm = new MimeMultipart();
			for (String filename : attachments) {
				if(!new File(filename).exists()){
					continue;
				}
				// 9. 创建附件“节点”
				MimeBodyPart attachment = new MimeBodyPart();
				DataHandler dh = new DataHandler(new FileDataSource(filename));  // 读取本地文件
				attachment.setDataHandler(dh);                                             // 将附件数据添加到“节点”
				attachment.setFileName(MimeUtility.encodeText(dh.getName()));              // 设置附件的文件名（需要编码）
				mm.addBodyPart(attachment);
			}
			//    最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
			//    上面的 mm_text_image 并非 BodyPart, 所有要把 mm_text_image 封装成一个 BodyPart
			MimeBodyPart content = new MimeBodyPart();
			content.setContent(body , "text/html;charset=utf-8");
			mm.addBodyPart(content);     // 如果有多个附件，可以创建多个多次添加
			// 11. 设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
			message.setContent(mm);
			// 12. 设置发件时间
			message.setSentDate(new Date());
			// 13. 保存上面的所有设置
			message.saveChanges();
			return message;
		}catch (Exception e){
			logger.error("createMimeMessage failed" , e);
			throw e;
		}
	}


	public static void main(String[] args) throws MessagingException, IOException {
		sendEmail(AppConfig.getMailTos() ,
				AppConfig.getMailSubject(),
				AppConfig.getMailBody(),
				AppConfig.getMailAttachment());
	}
}
