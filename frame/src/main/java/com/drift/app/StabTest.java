package com.drift.app;

import com.drift.frame.Configuration;
import com.drift.frame.Executor;
import com.drift.frame.TestModel;

public abstract class StabTest {

	private Configuration conf = new Configuration();
	// 稳定性线程数
	private int ThreadNum;

	// 测试载荷
	public abstract TestModel setTestModel();

	// 测试名称
	public abstract String setTestName();

	// 稳定需要的qps
	public abstract int setQps();

	// 主函数
	public void run() {
		for (int i = 1; i < 30; i += 2) {
			conf.setSetting("ratio", "0.99");
			Executor ptestExecutor;
			ptestExecutor = new Executor(conf, i, 600) {
				@Override
				public TestModel setInvokeClass() {
					// TODO Auto-generated method stub
					return setTestModel();
				}

				@Override
				public String setExecuterName() {
					// TODO Auto-generated method stub
					return setTestName();
				}
			};
			ptestExecutor.run();
			if (setQps() < ptestExecutor.getQuota().getQps()) {
				ThreadNum = i;
				break;
			}
		}
		Executor ptestExecutor;
		conf.setSetting("ratio", "0.99");
		ptestExecutor = new Executor(conf, ThreadNum, 28800) {
			@Override
			public TestModel setInvokeClass() {
				// TODO Auto-generated method stub
				return setTestModel();
			}

			@Override
			public String setExecuterName() {
				// TODO Auto-generated method stub
				return setTestName();
			}
		};
		ptestExecutor.run();

	}
}
