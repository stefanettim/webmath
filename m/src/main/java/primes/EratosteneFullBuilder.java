package primes;

import numbers.MException;

public abstract class EratosteneFullBuilder extends IntPrimesBuilder {

	protected boolean[] composites;
	protected long nsqrt;

	public EratosteneFullBuilder(long max) throws MException {
		this((int) max);
		if(max>Integer.MAX_VALUE) {
			throw new MException("too big, cannot cast to int");
		}
	}

	public EratosteneFullBuilder(int max) {
		super(max);
		nsqrt = (long) Math.sqrt((double) max) + 1;
	}

	public void init() {
		composites = new boolean[(int) max];

		composites[0] = true;
		composites[1] = true;

	}

	public void fillPrimes() {
		nPrimes = 0;
		for (int i = 2; i < max; i++) {
			if (!composites[i]) {
				nPrimes++;
				primes[nPrimes] = i;
			}
		}
	}

	public boolean[] getComposites() {
		return composites;
	}

	public void build() throws InterruptedException {
		init();
		sieve();
		fillPrimes();
	}

	protected abstract void sieve() throws InterruptedException;

}