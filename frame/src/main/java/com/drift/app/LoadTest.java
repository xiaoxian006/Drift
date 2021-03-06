package com.drift.app;

import com.drift.frame.Configuration;
import com.drift.frame.Executor;
import com.drift.frame.TestModel;

import java.util.HashMap;

public abstract class LoadTest {
	//设置起始线程数
	public int StartThreadNum = 1;
	//持续时间，设定默认时间为600s
	public int time = 600;

	/**
	 * 构造函数
	 * @param StartThreadNum 起始线程数
	 * @param LoadTime 每次测试时间
	 */
	public LoadTest(int StartThreadNum, int LoadTime) {
		this.StartThreadNum = StartThreadNum;
		this.time = LoadTime;
	}

	//测试载荷
	public abstract TestModel setTestModel();

	//测试名称
	public abstract String setTestName();

	//最优负载参数
	public abstract HashMap<String, Double> setStrategy();

	//主函数
	public void run() {
		for (int i = StartThreadNum; i < Integer.MAX_VALUE; i += 1) {
			Configuration conf = new Configuration();
			conf.setSetting("ratio", "0.99");
			Executor ptestExecutor;
			ptestExecutor = new Executor(conf, i, time) {
				@Override
				public TestModel setInvokeClass() {
					// TODO Auto-generated method stub
					return setTestModel();
				}

				@Override
				public String setExecutorName() {
					// TODO Auto-generated method stub
					return setTestName();
				}};
			ptestExecutor.run();
		}

	}
}
