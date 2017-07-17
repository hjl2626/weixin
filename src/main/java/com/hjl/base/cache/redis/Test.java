package com.hjl.base.cache.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Test {

	public static void main(String[] args) {
		JedisPool pool = RedisPool.getPool();
		Jedis jedis = null;
		for (int i = 0; i < 100; i++) {
			try {
				jedis = pool.getResource();
				jedis.set(String.valueOf(i), String.valueOf(i + 100));
				System.out.println(jedis.toString() + "----" + jedis.dbSize() + "--------"
						+ jedis.get(String.valueOf(i)));
				jedis.del(String.valueOf(i));
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(jedis != null){
					System.out.println("close jedis");
					jedis.close();
				}
			}
		}
	}
}
