package com.my.study.apache.commons.csv;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvFileReader {

	// CSV�ļ�ͷ
	private static final String[] FILE_HEADER = { "�û���", "����", "����", "����" };

	/**
	 * @param fileName
	 */
	public static void readCsvFile(String fileName) {
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		// ����CSVFormat��header mapping��
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
		try {
			// ��ʼ��FileReader object
			fileReader = new FileReader(fileName);
			// ��ʼ�� CSVParser object
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			// CSV�ļ�records
			Map<String, Integer> headerMap = csvFileParser.getHeaderMap();
			System.out.println(headerMap);
			List<CSVRecord> csvRecords = csvFileParser.getRecords();
			// CSV
			List<User> userList = new ArrayList<User>();
			//
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord record = csvRecords.get(i);
				// �����û�������������
				User user = new User(record.get("�û���"), record.get("����"), record.get("����"),
						Integer.parseInt(record.get("����")));
				userList.add(user);
			}
			// ������ӡ
			for (User user : userList) {
				System.out.println(user.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				csvFileParser.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		readCsvFile("d://users.csv");
	}

}
