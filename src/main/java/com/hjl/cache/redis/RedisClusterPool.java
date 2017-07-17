package com.hjl.cache.redis;

import redis.clients.jedis.JedisCluster;

public class RedisClusterPool {

	private static JedisCluster clusterPool;

	private RedisClusterPool(){

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
		String[] urls = POOL_DEFAULT_VALUE.getProperty("redis.cluster.url").split(",");
		Set<HostAndPort> serverSet =new HashSet<HostAndPort>();
		for (String url : urls) {
			String[] urlSplit = url.split(":");
			serverSet.add(new HostAndPort(urlSplit[0], Integer.valueOf(urlSplit[1])));
		}
		clusterPool = new JedisCluster(serverSet, config);
	}
	*/
	
	public static JedisCluster getClusterPool() {
		return clusterPool;
	}
}
