package com.my.study.forkjoin;

import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class SumTask extends RecursiveTask<Long> {

	static final int THRESHOLD = 100;

	long[] array;
	int start;
	int end;

	SumTask(long[] array, int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		if (end - start <= THRESHOLD) {
			// 如果任务足够小直接计算
			long sum = 0;
			for (int i = start; i < end; i++) {
				sum += array[i];
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(String.format("compute %d ~ %d = %d ", start, end, sum));
			return sum;
		}
		int middle = (end + start) / 2;
		System.out
				.println(String.format("split %d ~ %d ==> %d ~ %d , %d ~ %d ", start, end, start, middle, middle, end));
		SumTask subTask1 = new SumTask(this.array, start, middle);
		SumTask subTask2 = new SumTask(this.array, middle, end);
		invokeAll(subTask1,subTask2);
		Long subresult1 = subTask1.join();
		Long subresult2 = subTask2.join();
		Long result = subresult1 + subresult2;
		System.out.println("result = "+subresult1 + " + "+subresult2 + "== > "+result);
		return result;
	}
	
}
