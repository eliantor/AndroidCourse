package corso.samples.concurrency;

public class Threads {

	
	public static void sleepMillis(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
