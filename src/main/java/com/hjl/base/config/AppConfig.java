package com.hjl.base.config;


import com.hjl.base.utils.PropertiesLoader;

/**
 * Created by hjl on 2016/12/30.
 */
public class AppConfig {
    
    private static PropertiesLoader mail = new PropertiesLoader("classpath:mail.properties");
    
    private static PropertiesLoader sms = new PropertiesLoader("classpath:sms.properties");
    
    private static PropertiesLoader config = new PropertiesLoader("classpath:commons.properties");
    
    private static PropertiesLoader wechat = new PropertiesLoader("classpath:wechat.properties");

    private AppConfig() {}

    public static class Wechat{
        
        private Wechat(){}
        
        public static String getConfigWexinToken() {
            return wechat.getProperty("wechat.token");
        }
    
        public static String getConfigWexinAppID() {
            return wechat.getProperty("wechat.appID");
        }
    
        public static String getConfigWexinAppsecret() {
            return wechat.getProperty("wechat.appsecret");
        }
    
        public static String getConfigWexinUrl() {
            return wechat.getProperty("wechat.url");
        }
    
        public static String getGrantType(){
            return wechat.getProperty("wechat.grant.type");
        }
    
        public static Integer getConfigWexinTokenExpire() {
            return wechat.getInteger("wechat.token.expire");
        }
        
        public static String getNetAccessToken(){
            return wechat.getProperty("wechat.get.net.accessToken");
        }
    
        public static String getRefreshNetAccessToken(){
            return wechat.getProperty("wechat.refresh.net.accessToken");
        }
    
    
        public static String getNetUserInfo() {
            return wechat.getProperty("wechat.get.net.userinfo");
        }
    
        public static String getWechatType() {
            return wechat.getProperty("wechat.type");
        }
        
    
        public static String getUserInfo() {
            return wechat.getProperty("wechat.get.userinfo");
        }
    
        public static String getAccessToken(){
            return wechat.getProperty("wechat.get.accessToken");
        }
    
        public static String getGetticket(){
            return wechat.getProperty("wechat.get.getticket");
        }
        
        
    
        public static String getWechatip() {
            return wechat.getProperty("wechat.get.wechatip");
        }
    
        public static String getAddMenu() {
            return wechat.getProperty("wechat.add.wechatMenu");
        }
    
        public static String getDelMenu(){
            return wechat.getProperty("wechat.delete.wechatMenu");
        }
        public static String getGetQrcode(){
            return wechat.getProperty("wechat.get.qrCode");
        }
    
        public static String getMsgSendAll(){
            return wechat.getProperty("wechat.msg.send.all");
        }
    
        public static String getAddMaterial() {
            return wechat.getProperty("wechat.add.material");
        }
    
        public static String getMsgSendByOpenIds() {
            return wechat.getProperty("wechat.msg.send.filter.openid");
        }
    
    
        public static String getBatchGetMaterial() {
            return wechat.getProperty("wechat.batchget.material");
        }
    
        public static String getUrlShort2Long() {
            return wechat.getProperty("wechat.url.short2long");
        }
    
    
        
       
       
    }
    
    
   public static class Config{
    
       //config config
       public static Integer getConfigDefaultPageSize() {
           return config.getInteger("default.page.size");
       }
    
       public static String getConfigExcelSavePath() {
           return config.getProperty("excel.save.path");
       }
    
       public static String getConfigTuLingApiUrl() {
           return config.getProperty("tuling.api.url");
       }
    
       public static String getConfigTuLingApiKey() {
           return config.getProperty("tuling.api.apikey");
       }
       
    }
    
    
    public static class Sms{
    
        public static String getConfigSmsLoginMsg() {
            return config.getProperty("sms.login.msg");
        }
    
        public static String getConfigSmsRegisterCodeMsg() {
            return config.getProperty("sms.register.code.msg");
        }
    
        public static String getConfigSmsRegisterSuccessMsg() {
            return config.getProperty("sms.register.success.msg");
        }
    
        // sms config
        public static String getSmsDirectSendUrl() {
            return sms.getProperty("server.send_direct_SMS.url");
        }
    
        public static String getSmsScheduleSendUrl() {
            return sms.getProperty("server.send_schedule_SMS.url");
        }
    
        public static String getSmsSsid() {
            return sms.getProperty("server.ssid");
        }
    
        public static String getSmsType() {
            return sms.getProperty("server.type");
        }
    
        public static String getSmsUserName() {
            return sms.getProperty("server.username");
        }
    }
    
    
    public static class Mail{
    
        //  mail config
        public static boolean getMailIsDebug() {
            return mail.getBoolean("mail.debug");
        }
    
        public static boolean getMailSmtpAuth() {
            return mail.getBoolean("mail.smtp.auth");
        }
    
        public static String getMailHost() {
            return mail.getProperty("mail.host");
        }
    
        public static String getMailTransportProtocol() {
            return mail.getProperty("mail.transport.protocol");
        }
    
        public static String getMailUserName() {
            return mail.getProperty("mail.username");
        }
    
        public static String getMailPassword() {
            return mail.getProperty("mail.password");
        }
    
        public static String getMailTos() {
            return mail.getProperty("mail.tos");
        }
    
        public static String getMailSubject() {
            return mail.getProperty("mail.subject");
        }
    
        public static String getMailBody() {
            return mail.getProperty("mail.body");
        }
    
        public static String getMailAttachment() {
            return mail.getProperty("mail.attachment");
        }
        
    }
    
}
