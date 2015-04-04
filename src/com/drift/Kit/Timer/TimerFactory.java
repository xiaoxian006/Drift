package com.drift.Kit.Timer;


/**
 * 计时器工厂
 * @author Ray
 *
 */

public class TimerFactory {
	
	//获取单次计时器
	public static Timer getSingleTimer()
	{
		return new SingleTimer();
	}
	
	//获取计时器簇
	public static Timer getTimerItems()
	{
		return null;
		
	}
}