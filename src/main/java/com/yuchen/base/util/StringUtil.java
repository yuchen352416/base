package com.yuchen.base.util;

public class StringUtil {
	
	private StringUtil() {}
	
	public static boolean isEmpty(String string) {
		if (string != null && string.length() > 0) {
			return false;
		}
		return true;
	}
	
	
}
