package com.drift.frame;

import org.apache.log4j.Logger;

import com.drift.kit.Assert;
import com.drift.kit.timer.LoopTimer;
import com.drift.kit.timer.Timer;
import com.drift.kit.timer.TimerFactory;

/**
 * 性能测试模板
 * 
 * @author Ray
 *
 */
public abstract class PerformanceTestModel extends Thread {

	private Logger logger = Logger.getLogger(PerformanceTestModel.class);

	// 计时器
	private LoopTimer timer = (LoopTimer) TimerFactory.getLoopTimer();

	public Timer getTimer() {
		return timer;
	}

	// 执行时间
	private long time;

	public void setTime(long time) {
		this.time = time;
	}

	// 请求正确数
	private long correct_times = 0;

	// 请求返回数
	private long rt_times = 0;

	// 判断值
	private Object expect;
	private Object actual;
	private Object target;

	/**
	 * 前置操作
	 */
	public abstract void setup();

	/**
	 * 需要测试的接口
	 */
	public abstract void invoke() throws Exception;

	/**
	 * 判断结果是否正确
	 * 
	 * @param expect
	 *            期望值
	 * @param actual
	 *            实际值
	 */
	public void setCorrectJudge(Object expect, Object actual) {
		this.expect = expect;
		this.actual = actual;
	}
	
	/**
	 * 判断返回值是否正确
	 * @param judge
	 * @return
	 */
	public abstract boolean Assert(Object... judge);

	/**
	 * 判断结果是否为空
	 * 
	 * @param target
	 *            实际值
	 */
	public void setNotNullJudge(Object target) {
		this.target = target;
	}

	/**
	 * 后置操作
	 */
	public abstract void teardown();

	// 启动函数
	public void run() {
		setup();
		long begin = System.currentTimeMillis();
		//持续时间不能为0
		if (time == 0) {
			logger.error("time can't be null");
			throw new NullPointerException();
		}
		while (System.currentTimeMillis() - begin < time * 1000) {
			timer.start();
			try {
				invoke();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			timer.end();
			//判断expect是否存在
			if (expect != null) {
				if (Assert.assertCorrect(expect, actual)) {
					correct_times++;
				}
			}
			if (Assert.assertNotNull(target)) {
				rt_times++;
			}
		}
		teardown();
	}

	public long getCorrect_times() {
		return correct_times;
	}

	public long getRt_times() {
		return rt_times;
	}
}
