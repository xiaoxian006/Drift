package com.kuaidi.performance.Report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.apache.log4j.Logger;

/**
 * 报告基类
 * @author Ray's
 *
 */
public abstract class Report {
		
		private Logger logger = Logger.getLogger(Report.class);
	
		//打印报告
		public abstract void publish();
		
		//载入报告模板
		public String load()
		{
			String templete = "";
			try {
				
				templete =  new String(Files.readAllBytes(Paths.get("./config/report/" + TempleteName + ".html")));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				logger.error(TempleteName + "报告模板文件不存在");
				
				logger.error(e.fillInStackTrace());
			}
			return templete;
		}

		private String ReportName;
		
		private String ObjectName;
		
		private String TempleteName;
		
		
		
		public String getTempleteName() {
			return TempleteName;
		}

		public void setTempleteName(String templeteName) {
			TempleteName = templeteName;
		}

		public String getReportName() 
		{
			return ReportName;
		}
		
		public void setReportName(String reportName) 
		{
			ReportName = reportName;
		}
		
		public String getObjectName() 
		{
			return ObjectName;
		}

		public void setObjectName(String objectName) 
		{
			ObjectName = objectName;
		}

		
		
		
		
}
