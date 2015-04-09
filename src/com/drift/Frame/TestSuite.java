package com.drift.Frame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.drift.Frame.Rpc.Configuration;

public class TestSuite {

	private static Configuration conf = new Configuration();
	
	private static Thread ChildThread;
	
	public TestSuite(Configuration conf,Thread ChildThread)
	{
		TestSuite.conf = conf;
		TestSuite.ChildThread = ChildThread;
	}
	
	
	public static void main()
	{
		ExecutorService Client_Pool = Executors.newFixedThreadPool(conf.getThreadNum());
		
		Client_Pool.submit(ChildThread);
		
	}

}

