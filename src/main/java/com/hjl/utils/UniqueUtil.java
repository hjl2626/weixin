package com.hjl.utils;

/**
 * Created by hjl on 2016/12/28.
 */
public class UniqueUtil {

	/* 1 nano = 100000 millis */
	private static int NANO = 100000;

	/*  min nano value */
	private static final int MIN_NANO = 100000;
	/* max nano value */
	private static final int MAX_NANO = 999999;

	/* last millis value */
	private static long LAST_MILLIS;


	public static String generateUUID() {

		Long millisTime = System.currentTimeMillis();

		if (MAX_NANO == NANO) {
			if(LAST_MILLIS == millisTime) {
				do {
					millisTime = System.currentTimeMillis();
				} while(LAST_MILLIS == millisTime);
			}
			NANO = MAX_NANO;
		} else {
			++NANO;
			LAST_MILLIS = millisTime;
		}
		return "" + millisTime + NANO;
	}

	public static void main(String[] args){


		System.out.println(generateUUID());
	}
}
