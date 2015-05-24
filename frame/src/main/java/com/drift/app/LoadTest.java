package com.drift.app;

import java.util.HashMap;

import com.drift.frame.Configuration;
import com.drift.frame.Executer;
import com.drift.frame.TestModel;
import com.drift.frame.ptestmodel;

public abstract class LoadTest {
	private static long max_qps = 0;
	//测试载荷
	public abstract TestModel setTestModel();
	//测试名称
	public abstract String setTestName();
	//最优负载参数
	public abstract HashMap<String, Double> setStrategy();

	//主函数
	public void run() {
		for(int i=20;i<30;i+=2){
			Configuration conf = new Configuration();
			conf.setSetting("ratio", "0.99");
			Executer ptestExecuter;
			ptestExecuter = new Executer(conf,i,1800){
				@Override
				public TestModel setInvokeClass() {
					// TODO Auto-generated method stub
					return setTestModel();
				}

				@Override
				public String setExecuterName() {
					// TODO Auto-generated method stub
					return setTestName();
				}};                                                                          
			ptestExecuter.run();
			if(max_qps < ptestExecuter.getQuota().getQps()){
				max_qps = ptestExecuter.getQuota().getQps();
			}else if(max_qps > ptestExecuter.getQuota().getQps()*1.11){
				break;
			}
		}
		
	}
}
