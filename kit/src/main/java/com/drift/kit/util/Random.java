package com.drift.kit.util;

import java.util.ArrayList;

public class Random {

	// 复用java的随机器
	private static java.util.Random random = new java.util.Random();

	/**
	 * 随机整数
	 * 
	 * @param min
	 *            随机下限
	 * @param max
	 *            随机上限
	 * @return
	 */
	public static int randomInt(int min, int max) {
		return random.nextInt(max - min) + min;
	}

	/**
	 * 随机double类型
	 * 
	 * @param min
	 *            随机下限
	 * @param max
	 *            随机上限
	 * @return
	 */
	public static double randomDouble(double min, double max) {
		return random.nextDouble() * (max - min) + min;
	}

	/**
	 * 在词表中随机取值
	 * 
	 * @param list
	 *            词表
	 * @return
	 */
	@Deprecated
	public static Object randomInList(ArrayList<Object> list) {
		return list.get(random.nextInt(list.size()));
	}
}
