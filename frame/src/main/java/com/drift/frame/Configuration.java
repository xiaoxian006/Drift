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

	public String getSetting(String key) {
		return this.properties.getProperty(key);
	}

	public void setSetting(String key, String value) {
		properties.setProperty(key, value);
	}

}
