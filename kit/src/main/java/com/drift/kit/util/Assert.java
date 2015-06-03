package com.drift.kit.util;

public class Assert {
	/**
	 * 断言结果非空
	 * 
	 * @param object 要判断的结果
	 * @return
	 */
	public static boolean assertNotNull(Object object) {
		return object != null;
	}
	
	/**
	 * 断言结果正确
	 * @param expect 期望结果
	 * @param actual 实际结果
	 * @return
	 */
	public static boolean assertCorrect(Object expect , Object actual){
		return expect.equals(actual);
	}
}
