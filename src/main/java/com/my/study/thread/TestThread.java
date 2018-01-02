package com.my.study.thread;

public class TestThread {
	
	public static void main(String[] args) {
		
		Thread thread = new Thread(){
			@Override
			public void run() {
				pong();
			}
		};
		thread.run();
		System.out.println("ping");
		
	}
	
	static void pong(){
		System.out.println("pong");
	}
	
}
