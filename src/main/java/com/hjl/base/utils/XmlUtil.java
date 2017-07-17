package com.hjl.base.utils;


import com.hjl.core.message.bean.weixin.WeiXinTextMessage;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by hjl on 2017/1/10.
 */
public class XmlUtil {
	private static Logger logger = Logger.getLogger(XmlUtil.class);
	/**
	 * xml转换为java对象
	 */
	public static Object fromXml(String xml, Class clz) {
		Object t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clz);
			Unmarshaller unMarshaller = context.createUnmarshaller();
			t =  unMarshaller.unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
			logger.error("unmarshal xml : " + xml + "FAILED" ,e);
		}
		return t;
	}

	/**
	 * java对象转换为xml
	 * @param t
	 * @return
	 */
	public static String toXml(Object t) {
		StringWriter writer = new StringWriter();
		try {
			JAXBContext context = JAXBContext.newInstance(t.getClass());
			Marshaller marshaller = context.createMarshaller();

			marshaller.marshal(t, writer);
			return writer.toString();
		} catch (JAXBException e) {
			logger.error("marshal obj : " + t + "FAILED" ,e);
		}finally {
			IOUtils.closeQuietly(writer);
		}
		return "";
	}

	public static void print(Object o) {
		System.out.print(o);
	}

	public static void println(Object o) {
		System.out.println(o);
	}

	public static void main(String[] args){

		println(fromXml("<xml>" +
				"<ToUserName><![CDATA[toUser]]></ToUserName>" +
				" <FromUserName><![CDATA[fromUser]]></FromUserName>" +
				" <CreateTime>1348831860</CreateTime>" +
				" <MsgType><![CDATA[text]]></MsgType>" +
				" <Content><![CDATA[this is a test]]></Content>" +
				" <MsgId>1234567890123456</MsgId>" +
				"</xml>" , WeiXinTextMessage.class));
	}
}
