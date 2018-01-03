package com.my.study.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import com.my.study.security.MD5Util;

public class BasicFile {

	/**
	 * ����·�������ļ�
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

	public static void main(String[] args) throws FileNotFoundException {

		// File[] fileFilter = fileFilter(new File("D:/github"));
		// for (File file : fileFilter) {
		// System.out.println(file.getName());
		// }

//		File[] fileNameFilter = fileNameFilter(
//				new File("C:/tt/tt/���걨/��ҵһ�ױ�ͳ�Ƶ����ƶ�(2016)/����[U]/520100000000_20170222142402/D5200000001511233201070000.csv"));
//		
//		
//		for (File file : fileNameFilter) {
//			System.out.println(file.getName());
//		}
		
	}

}
