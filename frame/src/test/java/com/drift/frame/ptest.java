package com.drift.frame;

import com.drift.frame.rpc.Configuration;
import com.drift.frame.rpc.Executer;
import com.drift.frame.rpc.PerformanceTestModel;

public class ptest {
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		Executer ptestExecuter;
		try {
			ptestExecuter = new Executer(conf,1,10){
				@Override
				public PerformanceTestModel setInvokeClass() {
					// TODO Auto-generated method stub
					return new ptestmodel();
				}};
			ptestExecuter.run();
		} catch (ReflectiveOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
