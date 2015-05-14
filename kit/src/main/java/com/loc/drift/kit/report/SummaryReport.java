package com.loc.drift.kit.report;


public class SummaryReport extends Report{

	@Override
	public void publish() {
		// TODO Auto-generated method stub
		
		//载入模板文件
		this.setTempleteName("SummaryReport");
		String Report = load();
		
		//格式化报告
		setReportName("SummaryReport");
		Report = Report.replaceAll("##标题##" , getReportName());
		
		
	}

	
}
