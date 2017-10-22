package com.zhuma.demo.util;

/**
 * @desc 字符串操作工具类
 * 
 * @author zhumaer
 * @since 6/20/2017 16:37 PM
 */
public class StringUtil {

	/**
	 * 判断传入的字符串是否为空串
	 */
	public static boolean isEmpty(String str) {
		return str == null || (str.trim().equals(""));
	}

	/**
	 * 判断传入的字符串是否为空串
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 判断传入的是否存在空字符串
	 */
	public static boolean isAnyBlank(CharSequence... css) {
		if (css == null || css.length == 0) {
			return true;
		}

		for (CharSequence cs : css) {
			if (isBlank(cs)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 子字符串出现的个数
	 */
	public static int getSubStrCount(String str, String subStr) {
		int count = 0;
		int index = 0;
		while ((index = str.indexOf(subStr, index)) != -1) {
			index = index + subStr.length();
			count++;
		}
		return count;
	}
}
