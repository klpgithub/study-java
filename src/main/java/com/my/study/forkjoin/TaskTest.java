package com.my.study.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class TaskTest {

	public static void main(String[] args) {
		long[] array = new long[400];
		// 生成随机数组
		for (int i = 0; i < 400; i++) {
			array[i] = (long) (Math.random() * 100);
		}

		ForkJoinPool fjp = new ForkJoinPool(4);
		ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
		long startTime = System.currentTimeMillis();
		Long result = fjp.invoke(task);
		long endTime = System.currentTimeMillis();
		System.out.println("fork/join sum : " + result + " in " + (endTime - startTime) + "ms");

	}

}
