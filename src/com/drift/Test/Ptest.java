package com.drift.Test;
import com.drift.Frame.Rpc.DefaultPerformanceTest;

public class Ptest extends DefaultPerformanceTest{
	
	public int kk = 0;
	@Override
	public void setup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void invoke() {
		// TODO Auto-generated method stub
		kk++;
		System.out.println(kk);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void invoke(String className, String methodName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void teardown() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}