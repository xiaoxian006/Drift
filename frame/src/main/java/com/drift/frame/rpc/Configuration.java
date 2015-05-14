package com.drift.frame.rpc;

import java.util.Properties;

/**
 * 测试框架设置类
 * @author Ray
 *
 */
public class Configuration {
	
	//线程数量
	private int ThreadNum;
	
	//dubbo配置文件
	private String dubbo_path;

	public int getThreadNum() {
		return ThreadNum;
	}

	public void setThreadNum(int threadNum) {
		ThreadNum = threadNum;
	}

	public String getDubbo_path() {
		return dubbo_path;
	}

	public void setDubbo_path(String dubbo_path) {
		this.dubbo_path = dubbo_path;
	}
	
	public void load(Properties properties)
	{
		ThreadNum = (Integer) properties.get("ThreadNum");
		
	}
	
}
