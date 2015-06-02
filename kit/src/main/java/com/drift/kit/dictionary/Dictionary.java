package com.drift.kit.dictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.drift.kit.Random;

public class Dictionary {

	private Logger logger = Logger.getLogger(Dictionary.class);

	private ArrayList<String> descList = new ArrayList<String>();

	private ArrayList<HashMap<String, String>> words = new ArrayList<HashMap<String, String>>();
	// 查询计数器
	private int count = 0;

	public Dictionary(String... strings) {
		if (strings.length == 0) {
			logger.error("Dictionary's desc can't be null");
			throw new NullPointerException();
		}
		for (int i = 0; i < strings.length; i++) {
			descList.add(strings[i]);
		}
	}

	/**
	 * 生成字典
	 * 
	 * @param filepath
	 *            字典文件路径
	 * @throws FileNotFoundException
	 *             文件不存在
	 */
	public void load(String filepath) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(line, ",");
				HashMap<String, String> wordMap = new HashMap<String, String>();
				int i = 0;
				while (token.hasMoreTokens()) {
					wordMap.put(descList.get(i), token.nextToken());
					i++;
				}
				words.add(wordMap);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void lazyLoad(String filepath) {

	}

	/**
	 * 从词表中查询单词
	 * 
	 * @param type
	 *            0 : 顺序读取 1 : 随机读取
	 * @return
	 */
	public HashMap<String, String> lookup(int type) {
		if (words.size() == 0) {
			logger.error("dictionary is null");
			throw new NullPointerException();
		}

		switch (type) {
		case 0: {
			// 若计数器达到词表上限，清零计数器
			if (count == words.size()) {
				count = 0;
			}
			count++;
			return words.get(count - 1);
		}
		case 1:
			return words.get(Random.randomInt(0, words.size()));
		default:
			return words.get(Random.randomInt(0, words.size()));
		}
	}

//	public HashMap<String, String> lookup(){
//		
//	}
}
