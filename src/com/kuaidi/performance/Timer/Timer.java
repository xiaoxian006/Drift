package com.kuaidi.performance.Timer;

/**
 * 计时器调用接口
 * @author Ray
 *
 */

public interface Timer {
	
	//代码块开始时刻
	public long start();
	
	//代码块结束时刻
	public long end();
	
	//代码块耗时
	public long costTime();
}
