package com.drift.frame;

import com.drift.frame.PerformanceTestModel;

public class ptestmodel extends PerformanceTestModel {

	@Override
	public void setup() {
		System.out.println("start");
	}

	@Override
	public void invoke() {
		System.out.println("hello");
	}

	@Override
	public void teardown() {
		System.out.println("end");
	}

}
