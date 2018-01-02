package com.my.study.security.des;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DesECBUtil {

	/**
	 * ��������(hex�����ʽ���)
	 * 
	 * @param encryptString
	 *            ע�⣺��������ݳ���ֻ��Ϊ8�ı���(NoPaddingģʽ��)
	 * @param encryptKey
	 * @return
	 * @throws Exception
	 */
	public static String encryptDES(String encryptString, String encryptKey) throws Exception {
		SecretKeySpec key = new SecretKeySpec(getKey(encryptKey), "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
		return ConvertUtil.bytesToHexString(encryptedData);
	}

	/**
	 * �Զ���һ��key
	 * 
	 * @param string
	 */
	public static byte[] getKey(String keyRule) {
		Key key = null;
		byte[] keyByte = keyRule.getBytes();
		// ����һ���յİ�λ����,Ĭ�������Ϊ0
		byte[] byteTemp = new byte[8];
		// ���û�ָ���Ĺ���ת���ɰ�λ����
		for (int i = 0; i < byteTemp.length && i < keyByte.length; i++) {
			byteTemp[i] = keyByte[i];
		}
		key = new SecretKeySpec(byteTemp, "DES");
		return key.getEncoded();
	}

	/***
	 * ��������
	 * 
	 * @param decryptString
	 * @param decryptKey
	 * @return
	 * @throws Exception
	 */
	public static String decryptDES(String decryptString, String decryptKey) throws Exception {
		SecretKeySpec key = new SecretKeySpec(getKey(decryptKey), "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte decryptedData[] = cipher.doFinal(ConvertUtil.hexStringToByte(decryptString));
		return new String(decryptedData);
	}

	public static void main(String[] args) throws Exception {
		String clearText = "jsc_xy;45784745fe445ecb99faa0035471fc31;2017-07-02 23:40:09"; // ��������ݳ��ȱ���Ϊ8�ı���
		String key = "pzsuccez";
		System.out.println("���ģ�" + clearText + "\n��Կ��" + key);
		String encryptText = encryptDES(clearText, key);
		System.out.println("���ܺ�" + encryptText);
		String decryptText = decryptDES(encryptText, key);
		System.out.println("���ܺ�" + decryptText);
	}

}
