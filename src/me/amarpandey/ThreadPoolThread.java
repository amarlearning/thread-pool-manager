package me.amarpandey;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

public class ThreadPoolThread extends Thread {

	private BlockingQueue<Runnable> taskQueue;
	private ThreadPool threadPool;

	public ThreadPoolThread() {

	}

	public ThreadPoolThread(BlockingQueue<Runnable> taskQueue, ThreadPool threadPool) {
		this.taskQueue = taskQueue;
		this.threadPool = threadPool;
	}

	@Override
	public synchronized void run() {

		try {
			while (true) {

				Runnable task = taskQueue.take();

				task.run();

				if (this.threadPool.isShutdownInitiated() || (this.taskQueue.size() == 0)) {
					this.interrupt();
					Thread.sleep(1);
				}

				System.out.println("THREAD STATUS : " + Arrays.toString(threadPool.threadPoolStatus()));
			}
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " has been stopped.");
		}

	}

}
