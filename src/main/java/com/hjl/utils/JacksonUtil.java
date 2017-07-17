package com.hjl.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjl.model.domain.WeiXinUser;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by hjl on 2017/1/10.
 */
public class JacksonUtil {


	private final static ObjectMapper objectMapper = new ObjectMapper();

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	/**
	 * toJson
	 *
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static String toJson(Object object) throws IOException {
		StringWriter sw = new StringWriter();
		JsonGenerator gener;
		gener = new JsonFactory().createGenerator(sw);
		objectMapper.writeValue(gener, object);
		gener.close();
		return sw.toString();
	}

	/**
	 * json2obj
	 * @param json
	 * @param clz
	 * @return
	 * @throws IOException
	 */
	public static Object fromJson(String json, Class clz) throws IOException {
		return objectMapper.readValue(json, clz);
	}

	/**
	 * json2obj
	 * @param json
	 * @param reference
	 * @return
	 * @throws IOException
	 */
	public static Object fromJson(String json, TypeReference reference) throws IOException {
		return objectMapper.readValue(json, reference);
	}


	public static void main(String[] args) throws IOException {

		System.out.println(fromJson("{\"subscribe_time\" : \"122222\"}" , WeiXinUser.class));

	}


}
