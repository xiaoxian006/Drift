package com.kuaidi.performance.Report;
/**
 * 报告基类
 * @author Ray's
 *
 */
public abstract class Report {
	
		//打印报告
		public abstract void publish();
		
		//格式化报告
		public abstract void format();

		private String ReportName;
		
		public String getReportName() {
			return ReportName;
		}
		
		public void setReportName(String reportName) {
			ReportName = reportName;
		}
		
		
}
