package primes;

public class EratosteneParallelBuilder extends EratosteneFullBuilder {
	private Thread[] threads;
	private int maxThreads = 1;

	private int activeThreads = 0;
	private int lastPrime = 1;

	public EratosteneParallelBuilder(int max, int nthreads) {
		super(max);
		this.maxThreads = nthreads;
	}

	synchronized public int increment() {
		return ++lastPrime;
	}

	public void sieve() throws InterruptedException {
		threads = new Thread[maxThreads];
		for (int i = 0; i < maxThreads; i++) {
			EratosteneParallelThread w = new EratosteneParallelThread(this);
			threads[i] = w;
			threads[i].start();
			activeThreads++;
		}

		while (activeThreads > 0) {
			Thread.sleep(1);
		}

	}

	public int getLastPrime() {
		return lastPrime;
	}

	public int nextPrime() {
		int n = 0;
		while (n < nsqrt) {
			n = increment();
			if (!composites[n])
				return n;
		}

		return -1;
	}

	synchronized public void incrementThreads() {
		activeThreads++;
	}

	synchronized public void decrementThreads() {
		activeThreads--;
	}
	/*
	 * public boolean running() { boolean r = false;
	 * 
	 * for (int i = 0; i < maxThreads; i++) { if( threads[i].isAlive() ) r=true; }
	 * 
	 * return r; }
	 */

}