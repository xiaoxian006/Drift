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
		
		//只知道测试接口的方法和名字
		public void invoke(String className,String methodName)
		{
			try {
				
				Class newclass = Class.forName(className);
				
				Object target = newclass.newInstance();
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
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
