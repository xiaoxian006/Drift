package com.kuaidi.performance.Timer;


/**
 * 计时器的实现类
 * @author Ray
 *
 */
public class SingleTimer implements Timer{

	//定时器开始时间
	private long START_TIME;
	//定时器结束时间
	private long END_TIME;
	
	@Override
	public long start() {
		// TODO Auto-generated method stub
		START_TIME = System.currentTimeMillis();
		return START_TIME;
	}
	
	@Override
	public long end() {
		// TODO Auto-generated method stub
		END_TIME = System.currentTimeMillis();
		return END_TIME;
	}
	
	@Override
	public long costTime() {
		// TODO Auto-generated method stub
		return END_TIME - START_TIME;
	}
		
	
}
