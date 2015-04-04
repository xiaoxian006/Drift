package com.drift.Frame.Rpc;
/**
 * 性能测试模板类
 * @author Ray
 *
 */
public abstract class DefaultPerformanceTest extends Thread{
	
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
			
			invoke();
			
			teardown();
		}
}
