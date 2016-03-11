package com.scnuweb.domain;

import java.util.concurrent.TimeUnit;

public class ThreadTest {
	private static boolean stopRequested = false;
	
	public static void main(String arg[]) throws InterruptedException{
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i=0;
				while(!stopRequested) {
					i++;
				}
				System.out.println(i);
			}
		});
		thread.start();
		System.out.println(2);
		TimeUnit.SECONDS.sleep(1);
		System.out.println(3);
		stopRequested = true;
		System.out.println(4);
	}
}
