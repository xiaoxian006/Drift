package com.drift.frame;

import com.drift.frame.Configuration;
import com.drift.frame.Executer;
import com.drift.frame.TestModel;

public class ptest {
	private static long max_qps = 0;
	public static void main(String[] args) {
		for(int i=20;i<100;i+=2){
			Configuration conf = new Configuration();
			conf.setSetting("ratio", "0.99");
			Executer ptestExecuter;
			ptestExecuter = new Executer(conf,i,5){
				@Override
				public TestModel setInvokeClass() {
					// TODO Auto-generated method stub
					return new ptestmodel();
				}

				@Override
				public String setExecuterName() {
					// TODO Auto-generated method stub
					return "Regular Test";
				}};                                                                          
			ptestExecuter.run();
			if(max_qps < ptestExecuter.getQuota().getQps()){
				max_qps = ptestExecuter.getQuota().getQps();
			}else if(max_qps > ptestExecuter.getQuota().getQps()*1.1){
				break;
			}
		}
		
	}
}
