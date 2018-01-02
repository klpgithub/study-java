package com.my.study.file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class MergeFile {

	public static void main(String[] args) throws IOException {

		List<String> dFileList = FileUtils.readLines(
				new File(
						"C:/tt/tt/非年报/企业一套表统计调查制度(2014)/工业[B]/520100000000_20170221155144/D5200000001311197200050000.csv"),
				"gbk");
		System.out.println(dFileList.size());
		HashSet<String> set = new HashSet<String>();
		Map<String, String> fileMap = new HashMap<String, String>();
		for (String string : dFileList) {
			String[] split = string.split(",", 2);
			fileMap.put(split[0], split[1]);
			if (!set.add(split[0])) {
				System.out.println(split[0]);
			}
		}
		System.out.println(fileMap.size());
		System.out.println(set.size());

	}

}
