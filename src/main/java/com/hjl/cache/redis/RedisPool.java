package com.hjl.cache.redis;


import com.hjl.utils.PropertiesLoader;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

	private static JedisPool pool;

	private RedisPool(){

	}
	
	static {

		PropertiesLoader POOL_DEFAULT_VALUE = new PropertiesLoader("classpath:redis.properties");
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(POOL_DEFAULT_VALUE.getInteger("redis.pool.maxActive"));
		config.setMaxIdle(POOL_DEFAULT_VALUE.getInteger("redis.pool.maxIdle"));
		config.setMaxWaitMillis(POOL_DEFAULT_VALUE.getInteger("redis.pool.maxWait"));
		config.setTestOnBorrow(POOL_DEFAULT_VALUE.getBoolean("redis.pool.testOnBorrow"));
		config.setTestOnReturn(POOL_DEFAULT_VALUE.getBoolean("redis.pool.testOnReturn"));
		String[] url = POOL_DEFAULT_VALUE.getProperty("redis.server.url").split(":");
		pool = new JedisPool(config, url[0], Integer.valueOf(url[1])
				,POOL_DEFAULT_VALUE.getInteger("redis.pool.timeOut") ,
				null
				,POOL_DEFAULT_VALUE.getInteger("POOL_DEFAULT_VALUE"));
	}
	
	public static JedisPool getPool() {
		return pool;
	}
}
