package primes;

public class EratosteneParallelThread extends Thread {
	private EratosteneParallelBuilder builder;
	private boolean[] composites;
	private long max;

	public EratosteneParallelThread(EratosteneParallelBuilder builder) {
		this.builder = builder;
		composites = builder.getComposites();
		max = builder.getMax();
	}

	public void run() {
		int previousPrime = builder.getLastPrime();

		int n = builder.nextPrime();

		if (n < 0) {
			builder.decrementThreads();
			return;
		}

		previousPrime++;

		if (previousPrime < 2)
			previousPrime = 2;

		for (int j = previousPrime * n; j < max; j += n) {
			if (!composites[j])
				composites[j] = true;
		}

		run();

	}

}
