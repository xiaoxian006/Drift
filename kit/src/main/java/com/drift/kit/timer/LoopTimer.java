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

	// 定时器开始时间
	private long START_TIME;
	// 定时器结束时间
	private long END_TIME;
	// 耗时时间list
	private ArrayList<Long> timeList;
	// 平均时间
	private long AVG_TIME;
	// 最小时间
	private long MIN_TIME;
	// 最大时间
	private long MAX_TIME;
	// 定制百分比的时间
	private long RATIO_TIME;

	public ArrayList<Long> getTimeList() {
		return timeList;
	}

	public long getAVG_TIME() {
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
		timeList.add(costTime());
	}

	public long costTime() {
		// TODO Auto-generated method stub
		return END_TIME - START_TIME;
	}

}
