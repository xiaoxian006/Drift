package com.drift.Kit.Timer;

import java.util.ArrayList;

/**
 * 计时器簇的实现类
 * @author Ray
 *
 */

public class TimerItems implements Timer{

	private ArrayList<Long> timeList;
	
	private long tmpTime;
	
	@Override
	public long start() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long end() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long costTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void interrupt() {
		// TODO Auto-generated method stub
		long time = System.currentTimeMillis() - tmpTime;
		timeList.add(time);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		tmpTime = System.currentTimeMillis();
	}

}
