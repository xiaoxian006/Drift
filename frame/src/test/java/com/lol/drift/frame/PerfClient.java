package com.lol.drift.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

public class PerfClient implements Runnable {

	Logger logger = Logger.getLogger(PerfClient.class);

	private long max_time = 0;
	private int eight_percent_time = 0;
	private long invoke_times = 0;
	private long total = 0;
	private int totaltimes = 0;
	private int retCode = 0;
	public void setTime(int time) {
		this.time = time;
	}

	public int getRetCode() {
		return retCode;
	}

	public int getRetAcq() {
		return retAcq;
	}

	private int retAcq = 0;
	
	
	private int time = 10;
	private long Total_Time = 0;

	private static ApplicationContext context = new ClassPathXmlApplicationContext(
			"price-dubbo.xml");

	private static IntelligentPricingRemoteService ds = (IntelligentPricingRemoteService) context
			.getBean("IntelligentPricingRemoteService");

	ArrayList<RpcResult<PriceResult>> resList = new ArrayList<RpcResult<PriceResult>>();

	public int getTotaltimes() {
		return totaltimes;
	}

	public long getTotal_Time() {
		return Total_Time;
	}

	public void run() {
		invokeMethod(totaltimes);
	}

	public void invokeMethod(int times) {
		
		long begin = System.currentTimeMillis();

		long elsetime = 0;

		int Max_time_method = 0;

		List<Integer> time_cost_list = new ArrayList<Integer>();

		while (System.currentTimeMillis() - begin < time * 1000) {

			try {
				// 构造压力因子
				OrderParam orderParam = new OrderParam();
				orderParam.setPassengerId(112);
				orderParam.setCityId(310100);
				String[] prods = { "8006", "8005" };
				orderParam.setsProdId(prods);
				orderParam
						.setStartLoc("{\"addr\":\"财富时代大厦\",\"addr2\":\"普陀区陕西北路长寿路口\",\"lat\":31.242935,\"lng\":121.442136,\"district\":\"普陀区\"}");
				orderParam
						.setDestLoc("{\"addr\":\"世纪公园\",\"addr2\":\"锦绣路1001号行政文化中心\",\"lat\":31.215617,\"lng\":121.552552,\"district\":\"浦东新区\"}");
				orderParam.setOrderType(1);
				orderParam.setOrderTime(1431010715);
				orderParam.setTimeUsage("-1");
				orderParam
						.setCurrentLoc("{\"addr\":\"上海市普陀区陕西北路靠近财富时代广场\",\"lat\":31.242935,\"lng\":121.442136}");
				orderParam.setCouponId(524296);
				orderParam.setFlightCode("MU5102");
				orderParam.setDepartCode("PEK");
				orderParam.setArriveCode("SHA");
				orderParam.setDestCityId(1);
				orderParam.setCusRequirement(0);
				orderParam.setCorpId(42285201);
				orderParam.setApiName("estFee");
				orderParam.setAbtest(true);

				// 调用接口
				long start = System.currentTimeMillis();
				RpcResult<PriceResult> result = ds
						.getIntelligentPrice(orderParam);
				long end = System.currentTimeMillis();

				if (!(result == null)) {
					resList.add(result);
				}

				System.out.println(result.getCode());
				// logger.error(result2.getData().getCityId());
				// 存出结果
				time_cost_list.add((int) (end - start));

				Total_Time += end - start;
				if (max_time < end - start) {
					max_time = end - start;
				}
				times++;
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}

		if (resList.size() != 0) {
			for (RpcResult<PriceResult> tmp : resList) {
				if (tmp.getCode() != 0) {
					retCode++;
				}
				;
				if (tmp.getData().getPrice().size() != 0) {
					retAcq++;
				}
			}
		}

		Collections.sort(time_cost_list);
		eight_percent_time = time_cost_list
				.get((int) (time_cost_list.size() * 0.9));

		invoke_times = times;
		total = (Total_Time - elsetime);

		logger.error("Total time : " + (Total_Time - elsetime) + " , "
				+ "Total Invoke Times : " + times + " , "
				+ "Single Intf Costs : " + (Total_Time - elsetime) / times
				+ "ms , " + "Max Time : " + max_time
				+ "ms , and this medhod is : " + Max_time_method + " , "
				+ " 90% Request returns in : " + eight_percent_time + " ms , "
				+ "retCodeRight Num:" + retCode + " , " + "retAcqRight Num : "
				+ retAcq);

	}

	public long getMax_time() {
		return max_time;
	}

	public int getEight_percent_time() {
		return eight_percent_time;
	}

	public long getTotal() {
		return total;
	}

	public long getInvoke_times() {
		return invoke_times;
	}
}
