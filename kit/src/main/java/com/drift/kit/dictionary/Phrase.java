package com.drift.kit.dictionary;

import java.util.HashMap;
import java.util.StringTokenizer;

public class Phrase {
	
	HashMap<String, String> wordMap = new HashMap<String, String>();

//	public Phrase(String line,String delim){
//		StringTokenizer token = new StringTokenizer(line,delim);
//		while(token.hasMoreTokens()){
//			wordMap.put(key, token.nextToken());
//		}
//	}
	
	public String get(String key) {
		return wordMap.get(key);
	}
	
}
