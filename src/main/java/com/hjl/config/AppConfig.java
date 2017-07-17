package com.hjl.config;


import com.hjl.utils.PropertiesLoader;

/**
 * Created by hjl on 2016/12/30.
 */
public class AppConfig {


    private AppConfig() {

    }

    private static PropertiesLoader config = new PropertiesLoader("classpath:config.properties");

    private static PropertiesLoader mail = new PropertiesLoader("classpath:mail.properties");

    private static PropertiesLoader sms = new PropertiesLoader("classpath:sms.properties");

    //config config
    public static Integer getConfigDefaultPageSize() {
        return config.getInteger("default.page.size");
    }

    public static String getConfigExcelSavePath() {
        return config.getProperty("excel.save.path");
    }

    public static String getConfigSmsLoginMsg() {
        return config.getProperty("sms.login.msg");
    }

    public static String getConfigSmsRegisterCodeMsg() {
        return config.getProperty("sms.register.code.msg");
    }

    public static String getConfigSmsRegisterSuccessMsg() {
        return config.getProperty("sms.register.success.msg");
    }

    public static String getConfigWexinToken() {
        return config.getProperty("weixin.token");
    }

    public static String getConfigWexinAppID() {
        return config.getProperty("weixin.appID");
    }

    public static String getConfigWexinAppsecret() {
        return config.getProperty("weixin.appsecret");
    }

    public static String getConfigWexinUrl() {
        return config.getProperty("weixin.url");
    }

    public static Integer getConfigWexinTokenExpire() {
        return config.getInteger("weixin.token.expire");
    }

    public static String getConfigTuLingApiUrl() {
        return config.getProperty("tuling.api.url");
    }

    public static String getConfigTuLingApiKey() {
        return config.getProperty("tuling.api.apikey");
    }


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
