package com.manager.manager.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	private static final Pattern EMAIL_PATTERN = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
	private static final Pattern TEL_PATTERN = Pattern.compile("^(1[3578][0-9])\\d{8}$");
	private static final Pattern WORD_PATTERN = Pattern.compile("^[A-Za-z0-9]{8,20}$"); // 由字母、数字组成，8-20位
	private static final Pattern HAVE_LETTER = Pattern.compile(".*[a-zA-Z].*");
	private static final Pattern HAVE_DIGIT = Pattern.compile(".*[0-9].*");

	/**
	 * 检测邮箱地址是否合法
	 * 
	 * @param email
	 * @return true合法 false不合法
	 */
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email)) {
			return false;
		}
		// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
		Matcher m = EMAIL_PATTERN.matcher(email);
		return m.matches();
	}
	
	/**
	 * 检测手机号是否合法
	 * 
	 * @param tel
	 * @return
	 */
	public static boolean isTel(String tel) {
		boolean flag = false;
		if (null == tel || "".equals(tel)) {
			return false;
		}
		try {
			Matcher matcher = TEL_PATTERN.matcher(tel);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 检测密码强度。 字母数字组成，8-20个字符
	 * @param password
	 * @return
	 */
	public static boolean isPasswordStrong(String password) {
		boolean flag = false;
		if (StringUtils.isBlank(password)) {
			return false;
		}
		try {

			Matcher matcher = WORD_PATTERN.matcher(password);
			flag = matcher.matches();
			if (flag) {
				Matcher matcher1 = HAVE_LETTER.matcher(password);
				flag = matcher1.matches();
			}
			if (flag) {
				Matcher matcher2 = HAVE_DIGIT.matcher(password);
				flag = matcher2.matches();
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 生成UUID
	 * 
	 * @return
	 */
	public static String generateUUID(){
		  String s = UUID.randomUUID().toString();
		  return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
	}
	
	public static void main(String args[]) {
		String myEmail0 = "abc@100tal.com";
		String myEmail1 = "";
		/*
		String myEmail2 = "abc_test@100tal.com";
		String myEmail3 = "abc-test@100tal.com";
		String myEmail4 = "abc-123-test@100tal.com";
		String myEmail5 = "abc.123@test@100tal.com";
		 */
		System.out.println(myEmail0 + " :" + isEmail(myEmail0));
		System.out.println(myEmail1 + " :" + isEmail(myEmail1));
		/*
		System.out.println(myEmail2 + " :" + isEmail(myEmail2));
		System.out.println(myEmail3 + " :" + isEmail(myEmail3));
		System.out.println(myEmail4 + " :" + isEmail(myEmail4));
		System.out.println(myEmail5 + " :" + isEmail(myEmail5));
		System.out.println(generateUUID());
		*/
		String myTel0 = "17080152398";
		String myTel1 = "13863893258";
		String myTel2 = "12345678901";
		String myTel3 = "";
		System.out.println(myTel0 + ":" + isTel(myTel0));
		System.out.println(myTel1 + ":" + isTel(myTel1));
		System.out.println(myTel2 + ":" + isTel(myTel2));
		System.out.println(myTel3 + ":" + isTel(myTel3));
		
		String myPass0 = "123123123";
		String myPass1 = "abcabcasdf";
		String myPass2 = "1234abcDeef";
		String myPass3 = "123abD";
		System.out.println(myPass0 + ":" + isPasswordStrong(myPass0));
		System.out.println(myPass1 + ":" + isPasswordStrong(myPass1));
		System.out.println(myPass2 + ":" + isPasswordStrong(myPass2));
		System.out.println(myPass3 + ":" + isPasswordStrong(myPass3));
	}

}
