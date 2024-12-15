package primes;

public class EratosteneBlockParallelBuilder extends IntPrimesBuilder {
	private Thread[] threads;
	private int maxThreads = 1;
	private int activeThreads = 0;
	private int mDelay = 100;

	private int blockSize = 0;

	private int maxAssigned = 0;
	private int maxDone = 0;

	// synchronized
	public long getMaxDone() {
		return maxDone;
	}

	synchronized public void setMaxDone(int maxDone) {
		this.maxDone = maxDone;
		notifyAll();
	}

	// POI non synchronized, funziona comunque molto pi� veloce
	public void newPrime(int l) {
		nPrimes++;
		primes[nPrimes] = l;
	}

	synchronized public void checkCompleted(long l) throws InterruptedException {

		while (getMaxDone() < l) {
			wait();
		}

	}

	public EratosteneBlockParallelBuilder(int max, int nthreads, int kBlockSize, int mDelay) {
		super(max);

		blockSize = kBlockSize * 1024;

		this.maxThreads = nthreads;
		this.mDelay = mDelay;

		primes[0] = 0;
		primes[1] = 2;
		nPrimes = 1;

	}

	synchronized public int nextStart() {
		if (maxAssigned >= max || primes[nPrimes] >= max)
			return 0;

		int nextStart = maxAssigned + 1;
		if (nextStart < 3) {
			nextStart = 3;
			maxDone = 2;
		}

		int nextSize = blockSize - 1;
		if (nextStart + nextSize > max)
			nextSize = (int) (max - nextStart);

		if (nextSize == 0)
			nextSize = 1;

		maxAssigned = nextStart + nextSize;

		// System.out.println("## block : "+nextStart+".."+(nextStart+nextSize)+" --> "
		// + nPrimes + "^ prime : "+primes[nPrimes]);
		return nextStart;
	}

	synchronized public void incrementThreads() {
		activeThreads++;
	}

	synchronized public void decrementThreads() {
		activeThreads--;
	}

	@Override
	public void build() throws InterruptedException {
		threads = new Thread[maxThreads];
		for (int i = 0; i < maxThreads; i++) {
			EratosteneBlockThread w = new EratosteneBlockThread(this, "" + i, blockSize);
			threads[i] = w;
			threads[i].start();
			activeThreads++;
			//System.out.println("Thread " + i + " started. Primes : " + nPrimes);
			Thread.sleep(mDelay);
		}

		waitFinish();

	}

	private void waitFinish() throws InterruptedException {

		while (activeThreads > 0) {
			Thread.sleep(10);
		}

		/*
		 * synchronized(this) { while(getMaxDone()<max) { try { wait(); } catch
		 * (InterruptedException e) { e.printStackTrace(); } } }
		 */

	}

	public int getBlockSize() {
		return blockSize;
	}

	public long getMaxAssigned() {
		return maxAssigned;
	}

}