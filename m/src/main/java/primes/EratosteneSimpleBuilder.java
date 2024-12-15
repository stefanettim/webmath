package primes;

import numbers.MException;

public class EratosteneSimpleBuilder extends EratosteneFullBuilder {

	public EratosteneSimpleBuilder(int max) throws MException {
		super(max);
	}

	public void sieve() {
		int lastPrime = 2;
		for (int n = 2; n < nsqrt; n++) {
			if (!composites[n]) {
				for (int j = lastPrime * n; j < max; j += n) {
					composites[j] = true;
				}
				lastPrime = n;
			}
		}

	}

}