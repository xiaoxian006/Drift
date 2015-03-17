package com.kuaidi.performance.Report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

/**
 * 报告基类
 * @author Ray's
 *
 */
public abstract class Report {
		
		private Logger logger = Logger.getLogger(Report.class);
	
		//格式化报告
		public abstract void publish();
		
		//打印报告
		public void write(String path,String report)
		{
			try 
			{
				File file = new File(path);
				if(!file.exists())
				{
					file.createNewFile();
				}
				else 
				{
					file.delete();
					file.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(report.getBytes());
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("文件输出异常");
				logger.error(e.getMessage());
			}
		}
		
		//载入报告模板
		public String load()
		{
			String templete = "";
			try {
				
				templete =  new String(Files.readAllBytes(Paths.get("./config/report/" + TempleteName + ".html")));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				logger.error(TempleteName + "报告模板文件不存在");
				
				logger.error(e.getMessage());
			}
			return templete;
		}

		private String ReportName;
		
		private String ObjectName;
		
		private String TempleteName;
		
		
		
		public String getTempleteName() 
		{
			return TempleteName;
		}

		public void setTempleteName(String templeteName) 
		{
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
