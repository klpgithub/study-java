package com.my.study.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringReplaceTest {

	public static void main(String[] args) {

		System.out.println(replaceBlank("	\r\njust do it        "));

	}

	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
			Matcher matcher = pattern.matcher(str);
			dest = matcher.replaceAll("");
		}
		return dest;
	}

}
