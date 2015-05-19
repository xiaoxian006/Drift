package com.drift.kit.timer;

import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * 循环计时器 针对多次循环代码体的计时，可以获取平均时间，中位数时间，最大时间，最小时间，定制百分比循环数的时间
 * 
 * @author Ray
 *
 */

public class LoopTimer implements Timer {

	Logger logger = Logger.getLogger(LoopTimer.class);

	// 计时器单次循环开始时间
	private long START_TIME;
	// 计时器单次循环结束时间
	private long END_TIME;
	// 耗时时间list
	private ArrayList<Long> timeList = new ArrayList<Long>();
	// 平均时间
	private long AVG_TIME = 0;
	// 最小时间
	private long MIN_TIME = 0;
	// 最大时间
	private long MAX_TIME = 0;
	// 定制百分比的时间
	private long RATIO_TIME;
	// 计时器循环次数
	private long LOOP_TIMES;
	// 计时器总时间
	private long TIME = 0;

	// 包内可见，确保调用方法是通过工厂调用
	LoopTimer() {
	}

	public long getTIME() {
		for (long time : timeList) {
			TIME += time;
		}
		return TIME;
	}

	public long getLOOP_TIMES() {
		LOOP_TIMES = timeList.size();
		return LOOP_TIMES;
	}

	public long getAVG_TIME() {
		AVG_TIME = TIME / LOOP_TIMES;
		return AVG_TIME;
	}

	public long getMIN_TIME() {
		return MIN_TIME;
	}

	public long getMAX_TIME() {
		return MAX_TIME;
	}

	public long getRATIO_TIME(double ratio) {
		RATIO_TIME = timeList.get((int) (timeList.size() * ratio));
		return RATIO_TIME;
	}

	public void start() {
		START_TIME = System.currentTimeMillis();
	}

	public void end() {
		END_TIME = System.currentTimeMillis();
		// 判断最大时间是否变更
		if (MAX_TIME < costTime()) {
			MAX_TIME = costTime();
		}
		if (MIN_TIME > costTime()) {
			MAX_TIME = costTime();
		}
		timeList.add(costTime());
	}

	public long costTime() {
		// TODO Auto-generated method stub
		return END_TIME - START_TIME;
	}

}
