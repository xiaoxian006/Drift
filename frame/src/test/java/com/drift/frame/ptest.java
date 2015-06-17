package com.drift.frame;

public class ptest {
	private static long max_qps = 0;
	public static void main(String[] args) {
		for(int i=20;i<100;i+=2){
			Configuration conf = new Configuration();
			conf.setSetting("ratio", "0.99");
			Executor ptestExecutor;
			ptestExecutor = new Executor(conf, i, 5) {
				@Override
				public TestModel setInvokeClass() {
					// TODO Auto-generated method stub
					return new ptestmodel();
				}

				@Override
				public String setExecutorName() {
					// TODO Auto-generated method stub
					return "Regular Test";
				}
			};
			ptestExecutor.run();
			if (max_qps < ptestExecutor.getQuota().getQps()) {
				max_qps = ptestExecutor.getQuota().getQps();
			} else if (max_qps > ptestExecutor.getQuota().getQps() * 1.1) {
				break;
			}
		}
		
	}
}
