package me.amarpandey;

public class ThreadPoolTest {

	public static void main(String[] args) throws Exception {

		int taskCount = 20;
		Runnable task = new Task();
		ThreadPool threadPool = new ThreadPool(2);

		for (int i = 1; i <= taskCount; i++) {
			threadPool.execute(task);
		}

		threadPool.shutdown();
	}

}
