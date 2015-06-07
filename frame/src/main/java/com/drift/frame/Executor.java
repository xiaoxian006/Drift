package com.drift.frame;

import com.drift.kit.timer.LoopTimer;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Executor {

	private static Configuration conf = new Configuration();
	private Logger logger = Logger.getLogger(Executor.class);
	/**
	 * 线程数量
	 */
	private int ThreadNum;

	/**
	 * 持续时间
	 */
	private long duration_time;
	private ArrayList<TestModel> ptestList = new ArrayList<TestModel>();
	// 相关指标
	private long max_time = 0;
	private long time = 0;
	private long times = 0;
	private long correct_times = 0;
	private long biz_fail_times = 0;
	private long ratio_time = 0;
	private double ratio = 0.9;
	private Quota quota;

	/**
	 * 导入执行器需要的参数
	 *
	 * @param conf
	 *            Configuration类
	 * @param threadNum
	 *            并发线程数
	 * @param duration_time
	 *            持续时间，单位为秒
	 */
	public Executor(Configuration conf, int threadNum, long duration_time) {
		Executor.conf = conf;
		this.ThreadNum = threadNum;
		this.duration_time = duration_time;
		long begin = System.currentTimeMillis();
		for (int i = 0; i < ThreadNum; i++) {
			TestModel ptest = setInvokeClass();
			ptest.setBegin(begin);
			ptest.setTime(duration_time);
			ptestList.add(ptest);
		}
		logger.info("exexcuter init sucess!");
	}

	/**
	 * 设定执行器名字
	 *
	 * @return 这个返回的名字将会在测试报告中出现
	 */
	public abstract String setExecuterName();

	/**
	 * 设定调用接口
	 *
	 * @return 请返回实现的PerformanceTestModel类
	 */
	public abstract TestModel setInvokeClass();

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
		for (TestModel ptest : ptestList) {
			Client_Pool.submit(ptest);
		}
		Client_Pool.shutdown();
		// 监听任务是否完成
		while (true) {
			if (Client_Pool.isTerminated()) {
				for (TestModel ptest : ptestList) {
					//依次获取参数
					LoopTimer timer = ((LoopTimer) ptest.getTimer());
					time += timer.getTIME();
					times += timer.getLOOP_TIMES();
					if (max_time < timer.getMAX_TIME()) {
						max_time = timer.getMAX_TIME();
					}
					ratio_time += timer.getRATIO_TIME(ratio);
					correct_times += ptest.getCorrect_times();
					biz_fail_times += ptest.getNot_rt_times();
					if (conf.getSetting("ratio") != null) {
						ratio = Double.parseDouble(conf.getSetting("ratio"));
					}
				}
				boolean needPrintConsole = conf.getSetting("needprint") == null ? true
						: Boolean.parseBoolean(conf
								.getSetting("needprintConsle"));
				boolean needPrintLog = conf.getSetting("needprint") == null ? true
						: Boolean.parseBoolean(conf.getSetting("needprintLog"));
				if (needPrintConsole) {
					printConsole();
				}
				if (needPrintLog) {
					printLog();
				}
				quota = new Quota(ThreadNum, time, times, max_time,
						duration_time, ratio_time, correct_times,
						biz_fail_times);
				break;
			}
		}
	}

	/**
	 * 获取指标
	 *
	 * @return Quota 指标类
	 */
	public Quota getQuota() {
		return quota;
	}

	private void printConsole() {
		System.out.println("--------------" + setExecuterName()
				+ "----------------");
		System.out.println("线程数 : " + ThreadNum);
		System.out.println("持续时间 : " + duration_time + "s");
		System.out.println("Request Times : " + times + " , " + "TPS : "
				+ times * 1000 * ThreadNum / time + " , " + "Avg Time : "
				+ time * 1.0 / times + "ms , " + "Max Time : " + max_time
				+ "ms , " + (int) (ratio * 100) + "% Request returns in : "
				+ ratio_time / ThreadNum + " ms , " + "incorrect Num:"
				+ (times - correct_times) + " , " + "biz failed : "
				+ biz_fail_times + " , "
				+ (int) ((double) biz_fail_times / (double) times * 100) + "%");
	}

	private void printLog() {
		logger.info("--------------" + setExecuterName() + "----------------");
		logger.info("线程数 : " + ThreadNum);
		logger.info("持续时间 : " + duration_time + "s");
		logger.info("Request Times : " + times + " , " + "TPS : "
				+ times * 1000 * ThreadNum / time + " , " + "Avg Time : "
				+ time * 1.0 / times + "ms , " + "Max Time : " + max_time
				+ "ms , " + (int) (ratio * 100) + "% Request returns in : "
				+ ratio_time / ThreadNum + " ms , " + "incorrect Num:"
				+ (times - correct_times) + " , " + "biz failed : "
				+ biz_fail_times + " , "
				+ (int) ((double) biz_fail_times / (double) times * 100) + "%");
	}

	private void logConfig() {
		Properties pro = new Properties();
		pro.put("log4j.rootLogger", "INFO,R");
		pro.put("log4j.appender.R", "org.apache.log4j.RollingFileAppender");
		// pro.put("log4j.appender.R.File", "logs/invoke_" + POOL_SIZE +
		// "_threads.log");
		pro.put("log4j.appender.R.File", "logs/drift.log");
		pro.put("log4j.appender.R.MaxFileSize", "10000KB");
		pro.put("log4j.appender.R.MaxBackupIndex", "20");
		pro.put("log4j.appender.R.Threshold", "INFO");
		pro.put("log4j.appender.R.layout", "org.apache.log4j.PatternLayout");
		pro.put("log4j.appender.R.layout.ConversionPattern",
				"%n[%d{HH:mm:ss}] [%p] %m");
		PropertyConfigurator.configure(pro);
	}

}
