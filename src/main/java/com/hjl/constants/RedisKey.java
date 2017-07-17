package com.hjl.constants;

/**
 * Created by hjl on 2017/3/2.
 */
public class RedisKey {
	
	public static class NumDrawPrize{
		
		/**
		 * 用户抽奖code
		 */
		public static final String USER_CODE_KEY = "user.numDraw.code.";
		
		/**
		 * 已经分配的code
		 */
		public static final String ASSIGNED_CODE_KEY = "assigned.numDraw.code.";
		
		/**
		 * 回收的code
		 */
		public static final String RECYCLE_CODE_KEY = "recycle.numDraw.code.";
		
		/**
		 * 活动code超时 15天
		 */
		public static final Integer NUMDRAW_TIMEOUT = 60 * 60 * 24 * 15;
	}
}
