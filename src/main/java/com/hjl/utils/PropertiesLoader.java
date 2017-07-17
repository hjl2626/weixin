package com.hjl.utils;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * Created by hjl on 2016/12/19.
 */
public class PropertiesLoader {

	private Long lastModify;

	private Properties properties;

	private static Logger logger = Logger.getLogger(PropertiesLoader.class);

	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	private Resource resource;

	public PropertiesLoader(String resourcesPath) {
		this.resource = resourceLoader.getResource(resourcesPath);
		properties = new Properties();
		loadProperties();
	}

	public Properties getProperties() {
		return properties;
	}

	/**
	 * 取出Property。
	 */
	private String getValue(String key) {
		long lm;
		try {
			lm = resource.getFile().lastModified();
			if (lm != lastModify) {
				loadProperties();
			}
		} catch (IOException e) {
			logger.error("can't getFile from resource" , e);
		}
		String systemProperty = System.getProperty(key);
		if (systemProperty != null) {
			return systemProperty;
		}
		return properties.getProperty(key);
	}

	/**
	 * 取出String类型的Property,如果都為Null则抛出异常.
	 */
	public String getProperty(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return value;
	}

	/**
	 * 取出String类型的Property.如果都為Null則返回Default值.
	 */
	public String getProperty(String key, String defaultValue) {
		String value = getValue(key);
		return value != null ? value : defaultValue;
	}

	/**
	 * 取出Integer类型的Property.如果都為Null或内容错误则抛出异常.
	 */
	public Integer getInteger(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Integer.valueOf(value);
	}

	/**
	 * 取出Integer类型的Property.如果都為Null則返回Default值，如果内容错误则抛出异常
	 */
	public Integer getInteger(String key, Integer defaultValue) {
		String value = getValue(key);
		return value != null ? Integer.valueOf(value) : defaultValue;
	}

	/**
	 * 取出Double类型的Property.如果都為Null或内容错误则抛出异常.
	 */
	public Double getDouble(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Double.valueOf(value);
	}

	/**
	 * 取出Double类型的Property.如果都為Null則返回Default值，如果内容错误则抛出异常
	 */
	public Double getDouble(String key, Integer defaultValue) {
		String value = getValue(key);
		return value != null ? Double.valueOf(value) : defaultValue;
	}

	/**
	 * 取出Boolean类型的Property.如果都為Null抛出异常,如果内容不是true/false则返回false.
	 */
	public Boolean getBoolean(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Boolean.valueOf(value);
	}

	/**
	 * 取出Boolean类型的Propert.如果都為Null則返回Default值,如果内容不为true/false则返回false.
	 */
	public Boolean getBoolean(String key, boolean defaultValue) {
		String value = getValue(key);
		return value != null ? Boolean.valueOf(value) : defaultValue;
	}

	private void loadProperties() {
		InputStream is = null;
		try {
			is = resource.getInputStream();
			this.properties.load(is);
			this.lastModify = resource.getFile().lastModified();
		} catch (IOException ex) {
			logger.info("Could not load properties " ,ex);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	public static void main(String[] args){

		new PropertiesLoader("classpath:redis.properties");
	}
}

