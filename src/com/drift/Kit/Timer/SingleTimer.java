package com.drift.Kit.Timer;

import org.apache.log4j.Logger;

/**
 * 单次计时器的实现类
 * @author Ray
 *
 */

public class SingleTimer implements Timer{
	
	Logger logger = Logger.getLogger(SingleTimer.class);

	//定时器开始时间
	private long START_TIME = 0;
	//定时器结束时间
	private long END_TIME = 0;
	//定时器中断开始时间
	private long INTR_START_TIME = 0;
	//定时器中断结束时间
	private long INTR_END_TIME = 0;
	
	@Override
	public long start() {
		// TODO Auto-generated method stub
		START_TIME = System.currentTimeMillis();
		return START_TIME;
	}
	
	@Override
	public long end() {
		// TODO Auto-generated method stub
		END_TIME = System.currentTimeMillis();
		return END_TIME;
	}
	
	@Override
	public long costTime() {
		// TODO Auto-generated method stub
		if(END_TIME == 0)
		{
			logger.error("计时器未结束");
			return -1;
		}
		else if(START_TIME == 0)
		{
			logger.error("计时器未开始");
			return -1;
		}
		
		if((INTR_END_TIME - INTR_START_TIME) < 0)
		{
			logger.error("计时器未恢复");
			return -1;
		}
		else if(INTR_END_TIME == (INTR_END_TIME - INTR_START_TIME)&&(INTR_END_TIME != 0))
		{
			logger.error("计时器未暂停或恢复次数超过暂停次数");
			return -1;
		}
		else
		{
			long time = END_TIME - START_TIME - (INTR_END_TIME - INTR_START_TIME);
			return time;
		}
		
	}

	@Override
	public void interrupt() {
		// TODO Auto-generated method stub
		INTR_START_TIME = System.currentTimeMillis();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		INTR_END_TIME = System.currentTimeMillis();
	}
		
	
}
