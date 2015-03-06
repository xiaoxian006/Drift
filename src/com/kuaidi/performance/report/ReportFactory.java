package com.kuaidi.performance.Report;
/**
 * 输出性能报告的工厂类
 * @author Ray
 */

public class ReportFactory {
		
		private static ReportFactory instance = null;
		
		//私有化构造方法，确保单例
		private ReportFactory(){}
		
		public  static ReportFactory getInstance()
		{
			if( null == instance )
				{
					instance = new ReportFactory();
				}
				return instance;
		}
		
		
		
}
