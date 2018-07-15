package me.amarpandey;

public class Task implements Runnable {

	@Override
	public void run() {

		try {
			Thread.sleep(2000);

			System.out.println(Thread.currentThread().getName() + " is executing task.");
			System.out.println("REQUEST PROCESSED : " + ThreadPool.incrementRequestProcessed());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
