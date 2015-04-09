package com.drift.Test;

import com.drift.Frame.TestSuite;
import com.drift.Frame.Rpc.Configuration;

public class Suitetest {
	
	public static void main(String[] args)
	{
		Ptest ptest = new Ptest();
		Configuration conf = new Configuration();
		conf.setThreadNum(10);
		try {
			TestSuite tmp = new TestSuite(conf,ptest);
			tmp.main();
			System.out.println(ptest.kk);
			System.exit(0);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
