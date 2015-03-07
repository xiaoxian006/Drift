package com.kuaidi.performance.Report;
/**
 * 输出性能报告的工厂类
 * @author Ray
 */

public class ReportFactory {
		
		private static ReportFactory instance = null;
		
		//私有化构造方法，确保单例,未考虑多线程情况
		private ReportFactory(){}
		
		public  static ReportFactory getInstance()
		{
			if( null == instance )
				{
					instance = new ReportFactory();
				}
				return instance;
		}
		
		public Report getSummaryReport()
		{
			return new SummaryReport();
		}
		
		public Report getTimeReport()
		{
			return new TimeReport();
		}
		
		public Report getFrequencyReport()
		{
			return new FrequencyReport();
		}
		
		public Report getABReport()
		{
			return new ABReport();
		}
		
}
