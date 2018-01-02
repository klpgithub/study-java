package com.my.study.security.des;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DesECBUtil {

	/**
	 * 加密数据(hex编码格式输出)
	 * 
	 * @param encryptString
	 *            注意：这里的数据长度只能为8的倍数(NoPadding模式下)
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
	 * 自定义一个key
	 * 
	 * @param string
	 */
	public static byte[] getKey(String keyRule) {
		Key key = null;
		byte[] keyByte = keyRule.getBytes();
		// 创建一个空的八位数组,默认情况下为0
		byte[] byteTemp = new byte[8];
		// 将用户指定的规则转换成八位数组
		for (int i = 0; i < byteTemp.length && i < keyByte.length; i++) {
			byteTemp[i] = keyByte[i];
		}
		key = new SecretKeySpec(byteTemp, "DES");
		return key.getEncoded();
	}

	/***
	 * 解密数据
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
		String clearText = "jsc_xy;45784745fe445ecb99faa0035471fc31;2017-07-02 23:40:09"; // 这里的数据长度必须为8的倍数
		String key = "pzsuccez";
		System.out.println("明文：" + clearText + "\n密钥：" + key);
		String encryptText = encryptDES(clearText, key);
		System.out.println("加密后：" + encryptText);
		String decryptText = decryptDES(encryptText, key);
		System.out.println("解密后：" + decryptText);
	}

}
