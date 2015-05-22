package com.drift.frame;

public class Quota {
	private int ThreadNum;
	private long max_time = 0;
	private long time = 0;
	private long times = 0;
	private long correct_times = 0;
	private long rt_times = 0;
	private long ratio_time = 0;
	private double ratio = 0.9;
	private long rt_time = 0;
	private long qps = 0;

	public Quota(int ThreadNum,long time, long times, long max_time, double ratio,
			long ratio_time, long correct_times, long rt_times) {
		this.ThreadNum = ThreadNum;
		this.time = time;
		this.times = times;
		this.max_time = max_time;
		this.ratio = ratio;
		this.ratio_time = ratio_time;
		this.correct_times = correct_times;
		this.rt_times = rt_times;
	}

	public long getRt_time() {
		rt_time = time  * ThreadNum / times;
		return rt_time;
	}

	public long getMax_time() {
		return max_time;
	}

	public long getTime() {
		return time;
	}

	public long getTimes() {
		return times;
	}

	public long getCorrect_times() {
		return correct_times;
	}

	public long getRt_times() {
		return rt_times;
	}

	public long getRatio_time() {
		return ratio_time;
	}

	public double getRatio() {
		return ratio;
	}

	public long getQps() {
		qps = times * 1000 / time;
		return qps;
	}
}
