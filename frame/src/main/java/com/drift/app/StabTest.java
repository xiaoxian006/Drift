package com.drift.app;

import com.drift.frame.Configuration;
import com.drift.frame.Executor;
import com.drift.frame.TestModel;

public abstract class StabTest {

	private Configuration conf = new Configuration();
	// 稳定性线程数
	private int ThreadNum;
	// 单位时间
	private int TimeUnit;
	// 稳定需要的qps
	private int tps;
	// 持续时间
	private int DurationTime = 8;

	// 构造函数
	public StabTest(int tps, int ThreadNum, int DurationTime) {
		this.tps = tps;
		this.ThreadNum = ThreadNum;
		this.DurationTime = DurationTime;
	}

	// 测试载荷
	public abstract StabTestModel setStabTestModel();

	// 测试名称
	public abstract String setTestName();

	// 主函数
	public void run() {
		TimeUnit = 1000 / (tps / ThreadNum);
		Executor ptestExecutor;
		conf.setSetting("ratio", "0.99");
		ptestExecutor = new Executor(conf, ThreadNum, DurationTime * 3600) {
			@Override
			public String setExecutorName() {
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
