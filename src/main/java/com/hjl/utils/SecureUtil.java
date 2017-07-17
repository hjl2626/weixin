package com.hjl.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by hjl on 2016/12/28.
 */
public class SecureUtil {


	private static SecureRandomNumberGenerator srng = new SecureRandomNumberGenerator();


	/**
	 * 得到加密盐
	 * <功能详细描述>
	 *
	 * @return String [返回类型说明]
	 * @throws throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String getSalt() {
		return srng.nextBytes().toHex();
	}


	/**
	 * 得到MD5加密2次后的密码
	 * <功能详细描述>
	 *
	 * @param password
	 *
	 * @param salt
	 * @return String [返回类型说明]
	 * @throws throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String getMD5PwdStr(String password, String salt) {
		return new Md5Hash(password, salt, 2).toString();
	}


	public static void main(String[] args) {
		System.out.println(getSalt());
	}

}
