package com.drift.frame;

import com.drift.kit.timer.LoopTimer;
import com.drift.kit.timer.Timer;
import com.drift.kit.timer.TimerFactory;
import com.drift.kit.util.Assert;
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
	// 判断值
	private Object expect;
	private Object actual;
	private Object target;

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
				not_rt_times++;
			}finally{
				timer.end();
				//判断expect是否存在
				if (expect != null) {
					if (Assert.assertCorrect(expect, actual)) {
						correct_times++;
					}
				}
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
