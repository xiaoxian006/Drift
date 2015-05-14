package com.lol.drift.frame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class PerformanceTest { 
	 
	
	private static int POOL_SIZE = 1;
	private static Logger logger  =  Logger.getLogger(PerformanceTest. class );
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		Properties p = new Properties();
	    p.load(new FileInputStream("conf/config.properties"));
	    POOL_SIZE = Integer.parseInt(p.getProperty("threads"));
		
		Properties pro = new Properties();
		pro.put("log4j.rootLogger", "ERROR,R");
	    pro.put("log4j.appender.R", "org.apache.log4j.RollingFileAppender");
        pro.put("log4j.appender.R.File", "logs/invoke_" + POOL_SIZE + "_threads.log");
        pro.put("log4j.appender.R.MaxFileSize", "10000KB");
        pro.put("log4j.appender.R.MaxBackupIndex", "20");	
        pro.put("log4j.appender.R.Threshold", "INFO");
        pro.put("log4j.appender.R.layout", "org.apache.log4j.PatternLayout");
        pro.put("log4j.appender.R.layout.ConversionPattern", "%n[%d{HH:mm:ss}] [%p] %m");
        
//        pro.put("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
//        pro.put("log4j.appender.stdout.Threshold", "INFO");
//        pro.put("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
//        pro.put("log4j.appender.stdout.layout.ConversionPattern", "%n[%d{HH:mm:ss}] [%p] %m");
        PropertyConfigurator.configure(pro);
	    
	    
	    
	  //构造线程池
		ExecutorService testPool = Executors.newFixedThreadPool(POOL_SIZE);
		
		/**
		 * 构造数据
		 */
		
		
		logger.error("========IntelligentPricing Start==POOL_SIZE为" + POOL_SIZE + "=============");
		ArrayList<PerfClient> pList = new ArrayList<PerfClient>();
		for(int i=0;i<POOL_SIZE;i++)
		{
			PerfClient perf = new PerfClient();
			perf.setTime(10);
			testPool.submit(perf);
			pList.add(perf);
		}
		testPool.shutdown();
		long time = 0;
		long times = 0;
		long max_time = 0;
		long ninty_percent_time = 0 ;
		long retCode = 0;
		long retAcq = 0;
		while(true){
			if(testPool.isTerminated()){
				for(PerfClient perf:pList){
					time += perf.getTotal();
					times += perf.getInvoke_times();
					if(max_time < perf.getMax_time()){
						max_time = perf.getMax_time();
					}
					retCode = times - perf.getRetCode();
					retAcq = times - perf.getRetAcq();
				}
				logger.error("Total Invoke Times : " + times + " , "
						+ "Single Intf Costs : " + time / times
						+ "ms , " + "Max Time : " + max_time
						+ "ms , "
						+ " 90% Request returns in : " + ninty_percent_time + " ms , "
						+ "retCodeWrong Num:" + retCode + " , " + "retAcqWrong Num : "
						+ retAcq);
				System.out.println("Total Invoke Times : " + times + " , "
						+ "Single Intf Costs : " + time / times
						+ "ms , " + "Max Time : " + max_time
						+ "ms , "
						+ " 90% Request returns in : " + ninty_percent_time + " ms , "
						+ "retCodeWrong Num:" + retCode + " , " + "retAcqWrong Num : "
						+ retAcq);
				break;
			}
		}
	}
	
}
