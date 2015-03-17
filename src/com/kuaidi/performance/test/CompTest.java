package com.kuaidi.performance.test;

public class CompTest {
	
	public void method1()
	{
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void method2()
	{
		System.out.println("this is method2");
	}
}
