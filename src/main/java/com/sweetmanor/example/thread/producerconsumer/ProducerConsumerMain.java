package com.sweetmanor.example.thread.producerconsumer;

public class ProducerConsumerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Buffer buffer = new Buffer();
		new Producer(buffer, 101).start();
		new Consumer(buffer, 308).start();
		new Consumer(buffer, 309).start();
	}

}
