package primes;

public class EratosteneBlockBuilder extends IntPrimesBuilder {
	private int blockSize = 0;
	private int maxAssigned = 0;

	public EratosteneBlockBuilder(int max, int kBlockSize) {
		super(max);

		blockSize = kBlockSize * 1024;

		primes[0] = 0;
		primes[1] = 2;
		nPrimes = 1;

	}

	synchronized public EratosteneBlock nextBlock() {
		if (maxAssigned >= max || primes[nPrimes] >= max)
			return null;

		// if(maxAssigned>0)
		// System.out.println("< " + maxAssigned + " , primes " + nPrimes +
		// " , maxPrime " + primes[nPrimes]);

		int nextStart = maxAssigned + 1;
		if (nextStart < 3)
			nextStart = 3;

		int nextSize = blockSize - 1;
		if (nextStart + nextSize > max)
			nextSize = (int) (max - nextStart);

		if (nextSize == 0)
			nextSize = 1;

		EratosteneBlock eb = new EratosteneBlock(nextStart, nextSize);
		maxAssigned = nextStart + nextSize;

		// System.out.println("## block : "+nextStart+".."+(nextStart+nextSize)+" --> "
		// + nPrimes + "^ prime : "+primes[nPrimes]);
		return eb;
	}

	@Override
	public void build() {
		boolean running = true;
		while (running) {
			running = run();
		}
	}

	public int getBlockSize() {
		return blockSize;
	}

	public long getMaxAssigned() {
		return maxAssigned;
	}

	public void newPrime(int l) {
		nPrimes++;
		primes[nPrimes] = l;
	}

	public boolean run() {

		EratosteneBlock block = nextBlock();

		if (block == null) {
			return false;
		}

		int start = block.getStart();
		int limit = block.getSize();

		if (start + limit > getMax())
			limit = (int) (getMax() - start);

		int nPrimes = getNPrimes();
		long sqrtEnd = (long) Math.sqrt((double) (block.getEnd()));

		// scorro i primi noti
		for (int nprime = 1; nprime <= nPrimes; nprime++) {
			long prime = getPrimes()[nprime];

			if (prime > sqrtEnd)
				break;

			long offset = (start / prime) * prime - start;

			while (offset < 0)
				offset += prime;

			for (long j = offset; j < limit; j += prime) {
				block.setComposite((int) j);
			}
		}

		// scorro nuovi primi nel blocco
		for (int i = 0; i < limit; i++) {
			if (block.isPrime(i)) {
				int newPrime = start + i;
				if(newPrime>1) {
					newPrime(newPrime);
				}
				
				if (newPrime < (long) block.getSize()) {
					long npnp = newPrime * 2l - (long) start;
					for (long j = npnp; j < limit; j += newPrime) {
						block.setComposite((int) j);
					}
				}
			}
		}

		return true;

	}

}