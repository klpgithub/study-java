package com.my.study.runtime;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class RuntimeTest {

	@Test
	public void testRuntime() {
		System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024 / 1024);
		List[] lists = new List[10];
		Map[] maps = new Map[2];
	}

}
