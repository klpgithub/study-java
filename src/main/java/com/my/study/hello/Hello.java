package com.my.study.hello;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

public class Hello {

	public static void main(String[] args) throws UnsupportedEncodingException {

		System.out.println("hello world ! ");
		
		System.out.println(new Date(1500622768741L));
		
		System.out.println("20170006".substring(0, 4));
		
		
		System.out.println(new String(" ".getBytes(), "UTF-8"));
		
	}

}
