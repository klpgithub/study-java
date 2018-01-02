package com.my.study.apache.commons.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CsvFileWriter {

	// CSV�ļ��ָ���
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV�ļ�ͷ
	private static final Object[] FILE_HEADER = { "�û���", "����", "����", "����" };

	/**
	 * дCSV�ļ�
	 * 
	 * @param fileName
	 */
	public static void writeCsvFile(String fileName) {
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		// ���� CSVFormat
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
		try {
			// ��ʼ��FileWriter
			fileWriter = new FileWriter(fileName);
			// ��ʼ�� CSVPrinter
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			// ����CSV�ļ�ͷ
			csvFilePrinter.printRecord(FILE_HEADER);

			// �û��������List
			List<User> userList = new ArrayList<User>();
			userList.add(new User("zhangsan", "123456", "����", 25));
			userList.add(new User("lisi", "123", "����", 23));
			userList.add(new User("wangwu", "456", "����", 24));
			userList.add(new User("zhaoliu", "zhaoliu", "����", 20));

			// ����Listд��CSV
			for (User user : userList) {
				List<String> userDataRecord = new ArrayList<String>();
				userDataRecord.add(user.getUsername());
				userDataRecord.add(user.getPassword());
				userDataRecord.add(user.getName());
				userDataRecord.add(String.valueOf(user.getAge()));
				csvFilePrinter.printRecord(userDataRecord);
			}
			System.out.println("CSV�ļ������ɹ�~~~");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		writeCsvFile("d://users.csv");
	}

}
