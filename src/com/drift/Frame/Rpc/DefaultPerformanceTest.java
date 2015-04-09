package com.drift.Frame.Rpc;

import com.drift.Kit.Timer.Timer;
import com.drift.Kit.Timer.TimerFactory;

/**
 * 性能测试模板类
 * @author Ray
 *
 */
public abstract class DefaultPerformanceTest extends Thread{
	
		//计时器
		Timer timer = TimerFactory.getSingleTimer();
	
		//前置操作
		public abstract void setup();
		
		//需要测试的接口
		public abstract void invoke();
		
		//后置操作
		public abstract void teardown();
		
		//启动函数
		public void run()
		{
			setup();
			
			timer.start();
			
			invoke();
			
			timer.end();
			
			teardown();
		}
}
