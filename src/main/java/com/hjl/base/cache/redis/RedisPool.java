package com.hjl.base.cache.redis;


import com.hjl.base.utils.PropertiesLoader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class RedisPool {
	
	private static JedisPool pool;
	
	private static Logger logger = LoggerFactory.getLogger(RedisPool.class);
	
	static {
		PropertiesLoader POOL_DEFAULT_VALUE = new PropertiesLoader("classpath:redis.properties");
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(POOL_DEFAULT_VALUE.getInteger("redis.pool.maxActive"));
		config.setMaxIdle(POOL_DEFAULT_VALUE.getInteger("redis.pool.maxIdle"));
		config.setMaxWaitMillis(POOL_DEFAULT_VALUE.getInteger("redis.pool.maxWait"));
		config.setTestOnBorrow(POOL_DEFAULT_VALUE.getBoolean("redis.pool.testOnBorrow"));
		config.setTestOnReturn(POOL_DEFAULT_VALUE.getBoolean("redis.pool.testOnReturn"));
		Integer dbIndex = POOL_DEFAULT_VALUE.getInteger("redis.save.db");
		String redisPwd = POOL_DEFAULT_VALUE.getProperty("redis.pool.pwd");
		String[] url = POOL_DEFAULT_VALUE.getProperty("redis.server.url").split(":");
		pool = new JedisPool(config, url[0], Integer.valueOf(url[1]), Protocol.DEFAULT_TIMEOUT,
				StringUtils.isNotBlank(redisPwd) ? redisPwd : null, dbIndex);
	}
	
	/**
	 * 拿到JedisPool
	 * @return JedisPool
	 */
	public static JedisPool getPool() {
		return pool;
	}
	
	/**
	 * set
	 * @param key key
	 * @param value value
	 * @return String
	 */
	public static String set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getPool().getResource();
			return jedis.set(key, value);
		} catch (Exception e) {
			logger.error("[Jedis set] 失败!  key = {} , value = {}" , key , value , e);
			throw e;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	/**
	 * get 方法
	 * @param key key
	 * @return value
	 */
	public static String get(String key) {
		Jedis jedis = null;
		try {
			jedis = getPool().getResource();
			return jedis.get(key);
		} catch (Exception e) {
			logger.error("[Jedis get] 失败!  key = {} " , key , e);
			throw e;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	/**
	 * del key
	 * @param key key
	 * @return long
	 */
	public static Long del(String key) {
		Jedis jedis = null;
		try {
			jedis = getPool().getResource();
			return jedis.del(key);
		} catch (Exception e) {
			logger.error("[Jedis del] 失败!  key = {} " , key , e);
			throw e;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	/**
	 * 设置超时
	 * @param key key
	 * @param seconds 超时时间  秒
	 * @return
	 */
	public static Long expire(String key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = getPool().getResource();
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			logger.error("[Jedis expire] 失败!  key = {} " , key , e);
			throw e;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	/**
	 * 判断key 是否存在
	 * @param key key
	 * @return Boolean
	 */
	public static boolean exists(String key) {
		Jedis jedis = null;
		try {
			jedis = getPool().getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			logger.error("[Jedis exists] 失败!  key = {} " , key , e);
			throw e;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	/**
	 * hset
	 * @param key key
	 * @param field field
	 * @param value value
	 * @return long
	 */
	public static Long hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = getPool().getResource();
			return jedis.hset(key, field, value);
		} catch (Exception e) {
			logger.error("[Jedis hset] 失败!  key = {} " , key , e);
			throw e;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	/**
	 * 删除Hash field
	 * @param key key
	 * @param field field
	 * @return long
	 */
	public static Long hdel(String key, String... field) {
		Jedis jedis = null;
		try {
			jedis = getPool().getResource();
			return jedis.hdel(key, field);
		} catch (Exception e) {
			logger.error("[Jedis hdel] 失败!  key = {} " , key , e);
			throw e;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
}

