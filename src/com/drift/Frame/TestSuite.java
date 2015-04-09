package com.drift.Frame;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.drift.Frame.Rpc.Configuration;
import com.drift.Frame.Rpc.DefaultPerformanceTest;

public class TestSuite {

	private static Configuration conf = new Configuration();
	
	private static ArrayList<DefaultPerformanceTest> ptestList = new ArrayList();
	
	private static DefaultPerformanceTest ptest;
	
	public TestSuite(Configuration conf,DefaultPerformanceTest ptest) throws InstantiationException, IllegalAccessException
	{
		TestSuite.conf = conf;
		for(int i=0;i<conf.getThreadNum();i++)
		{
			DefaultPerformanceTest tmp = ptest.getClass().newInstance();
			ptestList.add(tmp);
		}
//		this.ptest = ptest;
		
	}
	
	
	public void main()
	{
		ExecutorService Client_Pool = Executors.newFixedThreadPool(conf.getThreadNum());
		
		for(DefaultPerformanceTest ptest : ptestList)
		{
			Client_Pool.submit(ptest);
		}
		while(Client_Pool.isTerminated())
		{
			
			System.out.println(Client_Pool.isTerminated());
			System.exit(0);
		}
//		for(int i=0;i<conf.getThreadNum();i++)
//		{
//			Client_Pool.submit(ptest);
//		}
		
	}

}

