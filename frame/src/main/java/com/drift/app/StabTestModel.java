package com.drift.app;

import com.drift.frame.TestModel;

/**
 * Created by Ray on 15/6/5.
 */
public abstract class StabTestModel extends TestModel {

	private int TIME_UNIT;

	// 稳定需要的qps
	public void setTPS(int qps, int thread_num) {
		TIME_UNIT = 1000 * thread_num / qps;
	}

	@Override
	public void post_invoke() {
		if (super.getTimer().costTime() < TIME_UNIT) {
			try {
				if (TIME_UNIT == 0) {
					throw new NullPointerException("you should set tps first");
				}
				Thread.sleep(TIME_UNIT - super.getTimer().costTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
