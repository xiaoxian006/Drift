package com.drift.frame;

import org.apache.log4j.Logger;

import com.drift.frame.PerformanceTestModel;

public class ptestmodel extends PerformanceTestModel {
private Logger logger = Logger.getLogger(ptestmodel.class);
	
	@Override
	public void setup() {
		System.out.println("start");
	}

	@Override
	public void invoke() {
		for(int i=0;i<1000;i++){
			i++;
			logger.info(i);
		}
	}

	@Override
	public void teardown() {
		System.out.println("end");
	}

}
