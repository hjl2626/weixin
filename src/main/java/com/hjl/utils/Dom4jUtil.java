package com.hjl.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

/**
 * Created by hjl on 2017/1/23.
 */
public class Dom4jUtil {
	
	
	public static Document toDoc(String _xml) throws DocumentException {
		Document doc = DocumentHelper.parseText(_xml);
		return doc;
	}
	
	
	public static void main(String[] args) throws DocumentException {
		
		System.out.print(toDoc("<xml>" +
				"<ToUserName><![CDATA[toUser]]></ToUserName>" +
				" <FromUserName><![CDATA[fromUser]]></FromUserName>" +
				" <CreateTime>1348831860</CreateTime>" +
				" <MsgType><![CDATA[text]]></MsgType>" +
				" <Content><![CDATA[this is a test]]></Content>" +
				" <MsgId>1234567890123456</MsgId>" +
				"</xml>").asXML());
		
		
	}
	
}
