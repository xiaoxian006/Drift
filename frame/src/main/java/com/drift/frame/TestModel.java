package com.drift.frame;

import com.drift.kit.timer.LoopTimer;
import com.drift.kit.timer.Timer;
import com.drift.kit.timer.TimerFactory;
import org.apache.log4j.Logger;

/**
 * 性能测试载荷
 * 
 * @author Ray
 *
 */
public abstract class TestModel extends Thread {

	private Logger logger = Logger.getLogger(TestModel.class);
	//开始时间
	private long begin;
	// 计时器
	private LoopTimer timer = (LoopTimer) TimerFactory.getLoopTimer();
	// 执行时间
	private long time;
	// 请求正确数
	private long correct_times = 0;
	// 请求未返回数
	private long not_rt_times = 0;

	public void setBegin(long begin) {
		this.begin = begin;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * 前置操作
	 */
	public abstract void setup();


	/**
	 * 每次invoke1前的操作
	 */
	public abstract void pre_invoke();

	/**
	 * 每次invoke后的操作
	 */
	public abstract void post_invoke();

	/**
	 * 需要测试的接口
	 */
	public abstract void invoke() throws Exception;
	
	/**
	 * 判断返回值是否正确
	 * @return
	 */
	public abstract boolean Assert();

	/**
	 * 后置操作
	 */
	public abstract void teardown();

	// 启动函数
	public void run() {
		setup();
		//持续时间不能为0
		if (time == 0) {
			logger.error("time can't be null");
			throw new NullPointerException();
		}
		while (System.currentTimeMillis() - begin < time * 1000) {
			pre_invoke();
			timer.start();
			try {
				invoke();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				not_rt_times++;
			}finally{
				timer.end();
			}
			post_invoke();
			if (Assert()) {
				correct_times++;
			}
		}
		teardown();
	}

	public long getCorrect_times() {
		return correct_times;
	}

	public long getNot_rt_times() {
		return not_rt_times;
	}
}
