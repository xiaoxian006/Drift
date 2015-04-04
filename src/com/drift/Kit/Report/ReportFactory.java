package com.drift.Kit.Report;


/**
 * 输出性能报告的工厂类
 * @author Ray
 */

public class ReportFactory {
		
		public static Report getSummaryReport()
		{
			return new SummaryReport();
		}
		
		public static Report getTimeReport()
		{
			return new TimeReport();
		}
		
		public static Report getFrequencyReport()
		{
			return new FrequencyReport();
		}
		
		public static Report getABReport()
		{
			return new ABReport();
		}
		
}
