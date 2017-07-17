package com.hjl.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hjl on 2016/12/20.
 */

public class AppContext {

	private AppContext(){

	}

	private static ThreadLocal<Map<String , Object>> context = new ThreadLocal<Map<String, Object>>(){
		protected Map<String , Object> initialValue(){
			return new HashMap<>();
		}
	};

	private static ApplicationContext ctx;

	public static ThreadLocal<Map<String , Object>>  getContext() {
		return context;
	}

	public static void setContextValue(String key, Object value) {
		context.get().put(key, value);
	}

	public static Object getContextValue(String key) {
		return context.get().get(key);
	}

	public static void setSpringContext(ApplicationContext applicationContext) {
		ctx = applicationContext;
	}

	public static ApplicationContext currentSpringContext() {
		return ctx;
	}

	public static Object getBeanFromSpringAppContext(String beanName) {
		return (ctx == null) ? null : ctx.getBean(beanName);
	}

	public static void publishEvent(ApplicationEvent event){
		ctx.publishEvent(event);
	}

}
