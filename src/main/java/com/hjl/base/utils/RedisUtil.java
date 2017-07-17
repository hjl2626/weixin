package com.hjl.base.utils;

import com.hjl.base.cache.redis.RedisPool;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;

/**
 * Created by hjl on 2016/12/19.
 */
public final class RedisUtil {

	private static JedisPool pool;
	
	
	private static Logger logger = Logger.getLogger(RedisUtil.class);

	private RedisUtil(){

	}

	// 加锁标志
	private static final String LOCKED = "TRUE";
	private static final long ONE_MILLI_NANOS = 1000000L;
	// 默认超时时间（毫秒）
	private static final long DEFAULT_TIME_OUT = 3000;
	// 锁的超时时间（秒），过期删除
	private static final int EXPIRE = 5 * 60;

	static {
		pool = RedisPool.getPool();
	}
	
	
	public static JedisPool getPool() {
		return pool;
	}
	
	/**
	 *
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String set(String key, String value) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.set(key, value);
		}catch (Exception e){
			logger.error(String.format("redis set key(%s) = value(%s) failed " ,key ,value) , e);
			throw e;
		}finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	/**
	 *
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static boolean setnx(String key, String value) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.setnx(key, value) == 1L;
		}catch (Exception e){
			logger.error(String.format("redis set key(%s) = value(%s) failed " ,key ,value) , e);
			throw e;
		}finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	/**
	 *
	 * @param key
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String set(byte[] key , Serializable obj) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.set(key, SerializationUtils.serialize(obj));
		}catch (Exception e){
			logger.error("redis set key(%s) = value(%s) failed " , e);
			throw e;
		}finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	/**
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String get(String key) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.get(key);
		}catch (Exception e){
			logger.error("redis get str failed " , e);
			throw e;
		}finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	/**
	 *
	 * @param key
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public static <T extends Serializable> T get(byte[] key) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return SerializationUtils.deserialize(jedis.get(key));
		}catch (Exception e){
			logger.error("redis get obj failed " , e);
			throw e;
		}finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	/**
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public  static Long del(String key) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.del(key);
		}catch (Exception e){
			logger.error("redis del str failed " , e);
			throw e;
		}finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	/**
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Long del(byte[] key) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.del(key);
		}catch (Exception e){
			logger.error("redis del obj failed" , e);
			throw e;
		}finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	/**
	 *
	 * @param key
	 * @param seconds
	 * @return
	 * @throws Exception
	 */
	public static Long expire(String key, int seconds) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			logger.error("redis expire(String key,int seconds) method exception:" + e.getMessage(), e);
			throw e;
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	/**
	 *
	 * @param key
	 * @param seconds
	 * @return
	 * @throws Exception
	 */
	public static Long expire(byte[] key, int seconds) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			logger.error("redis expire(byte[] key,int seconds) method exception:" + e.getMessage(), e);
			throw e;
		} finally {
			if( null != jedis) {
				jedis.close();
			}
		}
	}

	/**
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static boolean exists(String key) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			logger.error("redis exists(String key) method exception:" + e.getMessage(), e);
			throw e;
		} finally {
			if(null != jedis) {
				jedis.close();
			}
		}
	}

	/**
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static boolean exists(byte[] key) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			logger.error("redis exists(byte[] key) method exception:" + e.getMessage(), e);
			throw e;
		} finally {
			if(null != jedis) {
				jedis.close();
			}
		}
	}

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public static Long getDbSize() throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.dbSize();
		} catch (Exception e) {
			logger.error("redis getDbSize method exception:" + e.getMessage(), e);
			throw e;
		} finally {
			if(null != jedis) {
				jedis.close();
			}
		}
	}

	private static boolean lock(String key , Long timeout , Integer expire){
		Long nano = System.nanoTime();
		timeout *= ONE_MILLI_NANOS;
		Jedis jedis = null;
		Long result = 0L;
		try{
			 jedis = pool.getResource();
			while (System.nanoTime() - nano < timeout){
				result = jedis.setnx(key , LOCKED);
				if(result == 1L){
					if(expire == null || expire <= 0){
						jedis.expire(key , EXPIRE);
					}else{
						jedis.expire(key , expire);
					}
					return true;
				}
				Thread.sleep(3);
			}
		}catch (Exception e){
			logger.error("redis this key lock method fail --> :", e);
		}finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return false;
	}

	/**
	 * lock
	 * @param key
	 * @param expire
	 * @return
	 */
	public static boolean lock(String key , Integer expire){
		return lock(key ,DEFAULT_TIME_OUT , expire);
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public static boolean isLock(String key){
		try {
			return !(get(key) == null);
		} catch (Exception e) {
			logger.error("method isLock failed " , e);
		}
		return false;
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public static boolean unLock(String key){
		try {
			if (isLock(key)){
				del(key);
				return true;
			}
		}catch (Exception e){
			logger.error("method unLock failed" , e);
		}
		return false;
	}

	/**
	 * 加
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static Long incrBy(String key, Long value) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.incrBy(key, value);
		}catch (Exception e){
			logger.error(String.format("redis set key(%s) = value(%s) failed " ,key ,value) , e);
			throw e;
		}finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	/**
	 * 减
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static Long decrBy(String key, Long value) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.decrBy(key, value);
		}catch (Exception e){
			logger.error(String.format("redis set key(%s) = value(%s) failed " ,key ,value) , e);
			throw e;
		}finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		set("wwwww" ,"3");
		System.out.println(del("wwwww"));
	}
}
