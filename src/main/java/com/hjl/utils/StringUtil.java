package com.hjl.utils;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by hjl on 2016/12/28.
 */
public class StringUtil {

	/**
	 * 判断是否为手机号
	 *
	 * @param mobile
	 * @return
	 */

	public static boolean isMobile(String mobile) {
		if (mobile == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$");
		return pattern.matcher(mobile).matches();
	}

	/**
	 * 判断用户名
	 *
	 * @param username
	 * @return
	 */
	public static boolean isUserName(String username) {
		if (username == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("[a-zA-Z0-9_]{5,15}");
		return pattern.matcher(username).matches();
	}

	/**
	 * 检测邮箱
	 *
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_-]){1,16}@([a-zA-Z0-9_-]){1,16}\\.([a-zA-Z0-9_-]){1,6}");
		return pattern.matcher(email).matches();
	}

	/**
	 * 检测密码
	 *
	 * @param password
	 * @return
	 */
	public static boolean isPassword(String password) {
		if (password == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$])[0-9a-zA-Z!@#$]{8,18}$");
		return pattern.matcher(password).matches();
	}

	/**
	 * 获得随机字符串
	 *
	 * @param len 长度
	 * @return
	 */

	public static String getRandomString(int len) {
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		for (int i = 0; i < len; ++i) {
			builder.append(base.charAt(random.nextInt(base.length())));
		}
		return builder.toString();

	}

	/**
	 * 随机数字
	 * @param len
	 * @return
	 */
	public static String getRandomNumber(int len) {
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		String base = "0123456789";
		for (int i = 0; i < len; ++i) {
			builder.append(base.charAt(random.nextInt(base.length())));
		}
		return builder.toString();
	}

	public static boolean isSmsCode(String code) {

		if (code == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9a-zA-Z]{6}");
		return pattern.matcher(code).matches();
	}


	public static void main(String[] args) {
		System.out.println(isSmsCode("222222"));

	}
}
