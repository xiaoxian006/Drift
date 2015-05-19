package com.drift.frame;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.drift.kit.timer.LoopTimer;

public abstract class Executer {

	private Logger logger = Logger.getLogger(Executer.class);

	// 线程数量
	private int ThreadNum;
	
	//持续时间
	private long duration_time;

	private static Configuration conf = new Configuration();

	private static ArrayList<PerformanceTestModel> ptestList = new ArrayList<PerformanceTestModel>();
	//设定调用接口
	public abstract PerformanceTestModel setInvokeClass();
	
	/**
	 * 导入执行器需要的参数
	 * 
	 * @param conf
	 *            Configuration类
	 * @param threadNum 并发线程数
	 * @param duration_time 持续时间，单位为秒
	 * @throws ReflectiveOperationException
	 */
	public Executer(Configuration conf, int threadNum , long duration_time) {
		Executer.conf = conf;
		this.ThreadNum = threadNum;
		this.duration_time = duration_time;
		for (int i = 0; i < ThreadNum; i++) {
			ptestList.add(setInvokeClass());
		}
		logger.info("exexcuter init sucess!");
	}

	public Executer(PerformanceTestModel ptest) {
		for (int i = 0; i < ThreadNum; i++) {
			PerformanceTestModel tmp;
			try {
				tmp = ptest.getClass().newInstance();
				ptestList.add(tmp);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				logger.error("can't get instance of Performance Test");
				logger.error(e.getCause());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				logger.error("can't get instance of Performance Test");
				logger.error(e.getCause());
			}
		}

	}

	/**
	 * 启动函数
	 * 
	 * @throws NullPointerException
	 *             ThreadNum为0会抛出异常
	 */
	public void run() throws NullPointerException {
		logConfig();
		// 设置线程池
		if (ThreadNum == 0) {
			logger.error("Thread Num is null");
			throw new NullPointerException();
		}
		ExecutorService Client_Pool = Executors.newFixedThreadPool(ThreadNum);
		for (PerformanceTestModel ptest : ptestList) {
			ptest.setTime(duration_time);
			Client_Pool.submit(ptest);
		}
		Client_Pool.shutdown();
		// 监听任务是否完成
		while (true) {
			long max_time = 0;
			long time = 0;
			long times = 0;
			long correct_times = 0;
			long rt_times = 0;
			long ratio_time = 0;
			double ratio = 0.9;
			if (Client_Pool.isTerminated()) {
				for (PerformanceTestModel ptest : ptestList) {
					LoopTimer timer = ((LoopTimer) ptest.getTimer());
					time += timer.getTIME();
					times += timer.getLOOP_TIMES();
					if (max_time < timer.getMAX_TIME()) {
						max_time = timer.getMAX_TIME();
					}
					ratio_time += timer.getRATIO_TIME(ratio);
					correct_times += ptest.getCorrect_times();
					rt_times += ptest.getRt_times();
					if(conf.getSetting("ratio") != null){
						ratio = Double.parseDouble(conf.getSetting("ratio"));
					}
				}
				System.out.println("Request Times : " + times + " , "
						+ "QPS : " + times * 1000 / time + " , " + "Avg Time : "
						+ time / times + "ms , " + "Max Time : " + max_time
						+ "ms , " + (int)(ratio*100) + "% Request returns in : "
						+ ratio_time / ThreadNum
						+ " ms , " + "retCodeWrong Num:"
						+ (times - correct_times) + " , "
						+ "retAcqWrong Num : " + (times - rt_times));
				break;
			}
		}
	}
	private void logConfig(){
		Properties pro = new Properties();
		pro.put("log4j.rootLogger", "INFO,R");
	    pro.put("log4j.appender.R", "org.apache.log4j.RollingFileAppender");
//        pro.put("log4j.appender.R.File", "logs/invoke_" + POOL_SIZE + "_threads.log");
	    pro.put("log4j.appender.R.File", "logs/drift.log");
        pro.put("log4j.appender.R.MaxFileSize", "10000KB");
        pro.put("log4j.appender.R.MaxBackupIndex", "20");	
        pro.put("log4j.appender.R.Threshold", "INFO");
        pro.put("log4j.appender.R.layout", "org.apache.log4j.PatternLayout");
        pro.put("log4j.appender.R.layout.ConversionPattern", "%n[%d{HH:mm:ss}] [%p] %m");
        PropertyConfigurator.configure(pro);
	}
}
