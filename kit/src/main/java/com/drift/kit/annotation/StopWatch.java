package com.drift.kit.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 记录当前方法调用的耗时
 * @author Ray
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface StopWatch {
	
	
}
