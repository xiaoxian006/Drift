package com.drift.kit.report;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


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

	
	public static void format() throws ParserConfigurationException, SAXException, IOException{
		//得到DOM解析器的工厂实例
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        //从DOM工厂中获得DOM解析器
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
        //把要解析的xml文档读入DOM解析器
        Document doc = dbBuilder.parse("/Users/Ray/Desktop/SummaryReport.html");
        //得到文档名称为Student的元素的节点列表
        Node perfnode = doc.getElementById("perf");
        System.out.println(perfnode.getTextContent());
	}
	public static void main(String[] args){
		try {
			format();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
