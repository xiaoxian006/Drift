package com.drift.Test;

import com.drift.Kit.Timer.Timer;
import com.drift.Kit.Timer.TimerFactory;



public class TimerTest {

	//测试Time方法
	public static void main(String[] args)
	{
		int tmp = 0;
		Timer timer = TimerFactory.getSingleTimer();
		timer.start();
		while(tmp < 2)
		{
			tmp ++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		timer.end();
		System.out.println("Time cost : " + timer.costTime());
	}
}
