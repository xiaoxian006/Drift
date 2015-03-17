package com.kuaidi.performance.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.kuaidi.performance.Timer.Timer;
import com.kuaidi.performance.Timer.TimerFactory;

public class ReflectTest {
	
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CompTest test = new CompTest();
		Timer timer1 = TimerFactory.getSingleTimer();
		
		timer1.start();
		for(int i=0;i<100;i++)
		{
			Method  method = CompTest.class.getMethod("method1", null);
			method.invoke(test);
		}
		
		timer1.end();
		System.out.println(timer1.costTime());
		
		
		Timer timer2 = TimerFactory.getSingleTimer();
		timer2.start();
		for(int i=0;i<1;i++)
		{
			test.method1();
		}
		timer2.end();
		System.out.println(timer2.costTime());
	}

}
