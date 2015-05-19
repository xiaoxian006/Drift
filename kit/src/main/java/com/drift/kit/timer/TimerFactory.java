package com.drift.kit.timer;

/**
 * 计时器工厂
 * 可以获取单次计时器，计时器簇和循环计时器
 * @author Ray
 *
 */

public class TimerFactory {

	// 获取单次计时器
	public static Timer getSingleTimer() {
		return new SingleTimer();
	}

	// 获取计时器簇
	public static Timer getMultiTimer() {
		return new MultiTimer();
	}

	// 获取循环计时器
	public static Timer getLoopTimer() {
		return new LoopTimer();
	}
}
