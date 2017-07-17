package com.hjl.utils;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by hjl on 2016/12/19.
 */
public class SerializeUtil {

	private static Logger log = Logger.getLogger(SerializeUtil.class);

	public static byte[] serialize(Object obj){
		ObjectOutputStream oos = null;
		ByteArrayOutputStream byteOut;
		try {
			byteOut = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(byteOut);
			oos.writeObject(obj);
			return byteOut.toByteArray();
		} catch (Exception e) {
			log.error("对象序列化失败:-->" + e);
		}finally {
			IOUtils.closeQuietly(oos);
		}
		return null;
	}

	public static <T> T deserialize(byte[] bytes) {
		ByteArrayInputStream byteInt;
		ObjectInputStream oi = null;
		try {
			byteInt = new ByteArrayInputStream(bytes);
			oi = new ObjectInputStream(byteInt);
			return (T)oi.readObject();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("对象反序列化失败:-->"+e);
		}finally {
			IOUtils.closeQuietly(oi);
		}
		return null;
	}


	public static void main(String[] args){


	}
}
