package com.loc.drift.kit.timer;

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
	
	//计时暂停
	public void suspend();
	
	//恢复计时
	public void resume();
	
	//代码块耗时
	public long costTime();
	
}
