package com.hjl.base.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.hjl.core.message.bean.tuling.BaseResp;
import com.hjl.core.message.bean.tuling.LinkResp;
import org.apache.log4j.Logger;

/**
 * Created by hjl on 2016/12/19.
 */
public final class GsonUtil {

	private static Logger logger = Logger.getLogger(GsonUtil.class);

	private static Gson gson = new Gson();
	private static JsonParser parser = new JsonParser();



	private GsonUtil() {

	}

	public static String toJson(Object ob) {
		return gson.toJson(ob);
	}

	public static Object fromJson(String json, TypeToken Type) {

		return gson.fromJson(json, Type.getType());
	}

	public static Object fromJson(String json, Class clazz) {
		return gson.fromJson(json, clazz);
	}

	public static JsonObject fromJson(String json) {
		return parser.parse(json).getAsJsonObject();
	}


	public static void main(String[] args) {

		String json = "{\"code\":200000,\"text\":\"亲，已帮你找到新闻信息\",\"url\":\"http://m.toutiao.com/#channel=__all__\"}";

		BaseResp baseResp = (LinkResp) GsonUtil.fromJson(json ,LinkResp.class);
		System.out.println(GsonUtil.toJson(baseResp));
		System.out.println(GsonUtil.toJson((LinkResp)baseResp));
	}

}
