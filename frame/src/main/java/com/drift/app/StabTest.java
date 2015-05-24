package com.drift.app;

import java.util.HashMap;

import com.drift.frame.Configuration;
import com.drift.frame.Executer;
import com.drift.frame.TestModel;

public abstract class StabTest {

	// 测试载荷
	public abstract TestModel setTestModel();

	// 测试名称
	public abstract String setTestName();

	// 稳定需要的qps
	public abstract int setQps();

	private Configuration conf = new Configuration();

	// 稳定性线程数
	private int ThreadNum;

	// 主函数
	public void run() {
		for (int i = 1; i < 30; i += 2) {
			conf.setSetting("ratio", "0.99");
			Executer ptestExecuter;
			ptestExecuter = new Executer(conf, i, 600) {
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
			ptestExecuter.run();
			if (setQps() < ptestExecuter.getQuota().getQps()) {
				ThreadNum = i;
				break;
			}
		}
		Executer ptestExecuter;
		conf.setSetting("ratio", "0.99");
		ptestExecuter = new Executer(conf, ThreadNum, 28800) {
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
		ptestExecuter.run();

	}
}
