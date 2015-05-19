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
//			for(int j=0;j<1000;j++){
//				j++;
//				for(int n=0;n<1000;n++){
//					n++;
//					double tmp = Math.sqrt(i*j*n);
//					System.out.println(Double.toString(tmp));
//				}
//			}
		}
	}

	@Override
	public void teardown() {
		System.out.println("end");
	}

}
