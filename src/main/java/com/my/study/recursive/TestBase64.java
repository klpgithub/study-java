package com.my.study.recursive;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class TestBase64 {

	public static void main(String[] args) throws UnsupportedEncodingException {

		String string = "1";
		byte[] bs = Base64.getEncoder().encode(string.getBytes());
		System.out.println(new String(bs));

		byte[] decode = Base64.getDecoder().decode("ob7I2tDFob+y4srUtszQxb3Tv9o=");
		System.out.println(new String(decode, "gbk"));

		Map<Map<String, String>, String> map = new HashMap<>();

		Map<String, String> a = new HashMap<String, String>();
		a.put("A", "A");
		Map<String, String> b = new HashMap<String, String>();
		b.put("A", "A");
		Map<String, String> c = new HashMap<String, String>();
		c.put("A", "A");

		map.put(a, "lazyMap");
		map.put(b, "lazyMap");
		map.put(b, "lazyMap");

		System.out.println(map);
	}

}
