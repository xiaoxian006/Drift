package com.drift.kit;

public class Assert {
	/**
	 * 断言结果非空
	 * 
	 * @param object 要判断的结果
	 * @return
	 */
	public static boolean assertNotNull(Object object) {
		if (object != null)
			return true;
		return false;
	}
	
	/**
	 * 断言结果正确
	 * @param expect 期望结果
	 * @param actual 实际结果
	 * @return
	 */
	public static boolean assertCorrect(Object expect , Object actual){
		if (expect.equals(actual))
			return true;
		return false;
	}
}
