package com.my.study.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class BasicFile {

	/**
	 * 根据路径过滤文件
	 * 
	 * @author : KLP
	 * @param file
	 * @return
	 */
	public static File[] fileFilter(File file) {
		File[] files = file.listFiles(new FileFilter() {

			public boolean accept(File pathname) {
				if (pathname.isDirectory()) {
					return true;
				}
				return false;
			}
		});
		return files;
	}

	public static File[] fileNameFilter(File file) {
		File[] files = file.getParentFile().listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if (name.startsWith("D5200000") && name.endsWith(".csv")) {
					return true;
				}
				return false;
			}
		});
		return files;
	}

	public static void main(String[] args) {

		// File[] fileFilter = fileFilter(new File("D:/github"));
		// for (File file : fileFilter) {
		// System.out.println(file.getName());
		// }

		File[] fileNameFilter = fileNameFilter(
				new File("C:/tt/tt/非年报/企业一套表统计调查制度(2016)/其他[U]/520100000000_20170222142402/D5200000001511233201070000.csv"));
		
		
		for (File file : fileNameFilter) {
			System.out.println(file.getName());
		}

	}

}
