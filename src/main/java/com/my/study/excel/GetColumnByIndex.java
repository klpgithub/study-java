package com.my.study.excel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ͨ����������ȡExcel���Ӧ�е���ĸ
 * @author KLP
 *
 */
public class GetColumnByIndex {

	public static String getExcelColumnLabel(int num) {
		String temp = "";
		double i = Math.floor(Math.log(25.0 * (num) / 26.0 + 1) / Math.log(26)) + 1;
		if (i > 1) {
			double sub = num - 26 * (Math.pow(26, i - 1) - 1) / 25;
			for (double j = i; j > 0; j--) {
				temp = temp + (char) (sub / Math.pow(26, j - 1) + 65);
				sub = sub % Math.pow(26, j - 1);
			}
		} else {
			temp = temp + (char) (num + 65);
		}
		return temp;
	}

	public static String getExcelColumnLabel2(int iCol) {
		String strCol = "";
		int baseCol = 65 + iCol;
		if (baseCol > 90) {
			// ʮλλ��
			int i2 = 0;
			if ((baseCol - 90) / 26 > 0) {
				i2 = 65 + ((baseCol - 90 - 1) / 26);
			} else {
				i2 = 65;
			}
			// ��λλ��
			int i1 = ((baseCol - 90 - 1) % 26);
			i1 = 65 + i1;

			strCol = String.valueOf((char) i2) + String.valueOf((char) i1);
		} else {
			strCol = String.valueOf((char) baseCol);
		}
		return strCol;
	}

	public static void main(String[] args) {
		System.out.println(new Date().getTime());
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			list.add(getExcelColumnLabel(i));
		}
		System.out.println(list);
		System.out.println(new Date().getTime());
		System.out.println(getExcelColumnLabel(98));
	}

}
