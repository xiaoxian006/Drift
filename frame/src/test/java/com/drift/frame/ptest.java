package com.drift.frame;

import com.drift.frame.Configuration;
import com.drift.frame.Executer;
import com.drift.frame.PerformanceTestModel;

public class ptest {
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		conf.setSetting("ratio", "0.99");
		Executer ptestExecuter;
		ptestExecuter = new Executer(conf,1,1){
			@Override
			public PerformanceTestModel setInvokeClass() {
				// TODO Auto-generated method stub
				return new ptestmodel();
			}

			@Override
			public String setExecuterName() {
				// TODO Auto-generated method stub
				return "Regular Test";
			}};
		ptestExecuter.run();
	}
}
