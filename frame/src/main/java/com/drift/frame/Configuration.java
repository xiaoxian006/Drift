package com.drift.frame;

import java.util.Properties;

/**
 * 测试框架设置
 * 
 * @author Ray
 *
 */
public class Configuration {
	// 用户自定义配置
	private Properties properties = new Properties();

	public void load(Properties properties) {
		this.properties = properties;
	}
	/**
	 * 
	 * 目前支持的设置如下：
	 * @param ratio 需要获取的多少百分比的请求返回最大时间，小数表示
	 * @param needprint 是否需要在屏幕打印结果
	 */
	public String getSetting(String key) {
		return this.properties.getProperty(key);
	}
	/**
	 * 
	 * 目前支持的设置如下：
	 * @param ratio 需要获取的多少百分比的请求返回最大时间，小数表示
	 * @param needprint 是否需要在屏幕打印结果
	 */
	public void setSetting(String key, String value) {
		properties.setProperty(key, value);
	}

}
