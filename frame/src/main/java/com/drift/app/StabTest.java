package com.drift.app;

import com.drift.frame.Configuration;
import com.drift.frame.Executor;
import com.drift.frame.TestModel;

public abstract class StabTest {

	private Configuration conf = new Configuration();
	// 稳定性线程数
	private int ThreadNum;
	private int TimeUnit;

	// 测试载荷
	public abstract StabTestModel setStabTestModel();

	// 测试名称
	public abstract String setTestName();

	// 稳定需要的qps
	public abstract int setTps();

	// 主函数
	public void run() {
		TimeUnit = 1000 / (setTps() / ThreadNum);
		Executor ptestExecutor;
		conf.setSetting("ratio", "0.99");
		ptestExecutor = new Executor(conf, ThreadNum, 28800) {
			@Override
			public String setExecuterName() {
				return setTestName();
			}

			@Override
			public TestModel setInvokeClass() {
				return setStabTestModel();
			}
		};
		ptestExecutor.run();

	}

}
