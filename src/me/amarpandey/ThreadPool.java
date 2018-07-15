package me.amarpandey;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

	private int nThreads;
	static int requestProcessed;
	private BlockingQueue<Runnable> taskQueue;
	private ThreadPoolThread[] threadPoolThread;
	private boolean poolShutdownInitiated = false;

	public ThreadPool() {

	}

	public ThreadPool(int nThreads) {

		this.nThreads = nThreads;
		threadPoolThread = new ThreadPoolThread[nThreads];
		taskQueue = new LinkedBlockingQueue<Runnable>(nThreads);

		for (int count = 0; count < nThreads; count++) {
			threadPoolThread[count] = new ThreadPoolThread(taskQueue, this);
			threadPoolThread[count].start();
		}
	}

	public boolean[] threadPoolStatus() {

		boolean[] threadStatus = new boolean[this.nThreads];

		for (int count = 0; count < nThreads; count++) {
			threadStatus[count] = threadPoolThread[count].isAlive();
		}
		return threadStatus;
	}

	public synchronized void execute(Runnable task) throws Exception {

		if (this.isShutdownInitiated())
			throw new Exception("ThreadPool SHUTDOWN has been initiated, no task can be added now.");

		this.taskQueue.put(task);
	}

	public static synchronized int incrementRequestProcessed() {
		return ThreadPool.requestProcessed++;
	}

	public boolean isShutdownInitiated() {
		return poolShutdownInitiated;
	}

	public synchronized void shutdown() {
		this.poolShutdownInitiated = true;
		System.out.println("ThreadPool SHUTDOWN Initiated.");
	}

}
