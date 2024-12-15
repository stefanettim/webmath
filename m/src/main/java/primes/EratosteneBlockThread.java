package primes;

public class EratosteneBlockThread extends Thread {
	private EratosteneBlockParallelBuilder builder;
	private int size;
	private int start;
	private boolean[] composites;
	public int[] primes;

	public EratosteneBlockThread(EratosteneBlockParallelBuilder builder, String name, int size) {
		super("EratosteneBlockThread " + name);
		this.builder = builder;
		this.size = size;
		composites = new boolean[size];
		primes = builder.getPrimes();
	}

	public void run() {
		setPriority(Thread.MAX_PRIORITY);

		boolean running = true;

		while (running) {
			start = builder.nextStart();

			if (start == 0) {
				running = false;
			} else
				try {
					resetBlockNew();
					buildBlock();
				} catch (InterruptedException e) {
					e.printStackTrace();
					running = false;
				}
		}

		builder.decrementThreads();
		//System.out.println("Thread " + getName() + " completed");
		return;
	}

	protected void resetBlockNew() {
		composites = new boolean[size];
	}

	protected void resetBlockFor() {

		for (int i = 0; i < size; i++)
			if (composites[i])
				composites[i] = false;

	}

	synchronized public boolean buildBlock() throws InterruptedException {

		// setPriority(Thread.NORM_PRIORITY);

		int end = start + size;
		int limit = size;
		int max = builder.getMax();

		if (start + limit > max)
			limit = (int) (max - start);

		int nPrimes = builder.getNPrimes();
		long sqrtEnd = (int) Math.sqrt((double) (end));

		// scorro i primi noti
		for (int nprime = 1; nprime <= nPrimes; nprime++) {
			long prime = primes[nprime];

			if (prime > sqrtEnd)
				break;

			long offset = (start / prime) * prime - start;

			while (offset < 0)
				offset += prime;

			for (long j = offset; j < limit; j += prime) {
				int n = (int) j;
				if (!composites[n])
					composites[n] = true;
			}
			/*
			 * if(offset<limit) { int ioffset = (int) offset; for (int j = ioffset; j <
			 * limit; j += prime) { if(!composites[j]) composites[j]=true; } }
			 */

		}

		// setPriority(Thread.MIN_PRIORITY);

		builder.checkCompleted(start - 1);

		setPriority(Thread.MAX_PRIORITY);

		// rieseguo con nuovi primi trovati da altri thread
		int newNPrimes = builder.getNPrimes();
		for (int nprime = nPrimes + 1; nprime <= newNPrimes; nprime++) {
			long prime = primes[nprime];

			if (prime > sqrtEnd)
				break;

			long set = prime;
			if(prime==0) {
				set=1;
			}
			long offset = (start / set) * prime - start;

			while (offset < 0)
				offset += prime;

			for (long j = offset; j < limit; j += prime) {
				int n = (int) j;
				if (!composites[n])
					composites[n] = true;
			}

			/*
			 * if(offset<limit) { int ioffset = (int) offset; for (int j = ioffset; j <
			 * limit; j += prime) { if(!composites[j]) composites[j]=true; } }
			 */

		}

		// scorro nuovi primi nel blocco, nuovi primi trovati da questo thread
		for (int i = 0; i < limit; i++) {
			if (!composites[i]) {
				int newPrime = start + i;
				builder.newPrime(newPrime);

				if (newPrime < (long) size) {
					long npnp = newPrime * 2l - (long) start;
					for (long j = npnp; j < limit; j += newPrime) {
						int n = (int) j;
						if (!composites[n])
							composites[n] = true;
					}
				}
			}
		}

		builder.setMaxDone(end);

		return true;

	}

}
