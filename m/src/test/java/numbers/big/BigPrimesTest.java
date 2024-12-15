package numbers.big;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import numbers.MException;

class BigPrimesTest {

	@Test
	void testPrimesBuild() throws MException {
		BigPrimes primes = BigPrimesFactory.generate(1000l);
		assert(primes.isPrime(997l));
	}

	@Test
	void testFactorize() throws MException {
		BigPrimes primes = BigPrimesFactory.generate(100000l);
		BigFactorizationResult r= primes.factorize(BigInteger.valueOf(13195));
		assert(r.factors.getLast().getFactor().equals(BigInteger.valueOf(29)));
	}
}
