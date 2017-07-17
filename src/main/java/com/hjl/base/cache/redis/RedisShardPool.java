package com.hjl.base.cache.redis;

import redis.clients.jedis.ShardedJedisPool;

public class RedisShardPool {

	private static ShardedJedisPool pool;

	private RedisShardPool(){

	}

	/*
	static {
		PropertiesLoader POOL_DEFAULT_VALUE = new PropertiesLoader("classpath:redis.properties");
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(POOL_DEFAULT_VALUE.getInteger("redis.pool.maxActive"));
		config.setMaxIdle(POOL_DEFAULT_VALUE.getInteger("redis.pool.maxIdle"));
		config.setMaxWaitMillis(POOL_DEFAULT_VALUE.getInteger("redis.pool.maxWait"));
		config.setTestOnBorrow(POOL_DEFAULT_VALUE.getBoolean("redis.pool.testOnBorrow"));
		config.setTestOnReturn(POOL_DEFAULT_VALUE.getBoolean("redis.pool.testOnReturn"));
		String[] urls = POOL_DEFAULT_VALUE.getProperty("redis.servers.url").split(",");
		List<JedisShardInfo> jdsInfoList =new ArrayList<JedisShardInfo>();
		for (String url : urls) {
			String[] urlSplit = url.split(":");
			jdsInfoList.add(new JedisShardInfo(urlSplit[0], Integer.valueOf(urlSplit[1])));
		}
		pool = new ShardedJedisPool(config, jdsInfoList);
	}
	*/
	
	public static ShardedJedisPool getShardPool() {
		return pool;
	}
}
