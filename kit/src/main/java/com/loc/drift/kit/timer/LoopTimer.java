package com.loc.drift.kit.timer;

import java.util.ArrayList;

/**
 * 循环计时器
 * @author Ray
 *
 */

public class LoopTimer implements Timer{

	private ArrayList<Long> timeList;
	
	private long tmpTime;
	
	public long start() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long end() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long costTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void suspend() {
		// TODO Auto-generated method stub
		long time = System.currentTimeMillis() - tmpTime;
		timeList.add(time);
	}

	public void resume() {
		// TODO Auto-generated method stub
		tmpTime = System.currentTimeMillis();
	}

}
