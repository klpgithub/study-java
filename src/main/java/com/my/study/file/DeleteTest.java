package com.my.study.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * ͨ���ļ����ص�������InputStream����δ�رգ����ļ��޷�ɾ��
 * @author KLP
 *
 */
public class DeleteTest {
	
	public static void main(String[] args) {
		try {
			File file = new File("C://SangforServiceClient_20171016.log");
			FileInputStream inputStream = new FileInputStream(file);
			//inputStream.close();
			boolean b = file.delete();
			System.out.println(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
