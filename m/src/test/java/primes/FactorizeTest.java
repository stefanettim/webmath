package primes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import numbers.MException;
import numbers.results.FactorizationResult;

class FactorizeTest {

	@Test
	void testFactors() throws MException, ClassNotFoundException, IOException, InterruptedException {
		IntPrimes primes = IntPrimesFactory.loadMain();
		
		// 97 = 97
		FactorizationResult fr97 = primes.factorize(97);
		assertEquals(1, fr97.factorsCount);
		assertEquals(1, fr97.maxPower);

		// 98 = 2*7^2
		FactorizationResult fr98 = primes.factorize(98);
		assertEquals(2, fr98.factorsCount);
		assertEquals(2, fr98.maxPower);

		// 210 = 2*3*5*7
		FactorizationResult fr210 = primes.factorize(210);
		assertEquals(4, fr210.factorsCount);
		assertEquals(1, fr210.maxPower);
		
		// 64=2^6
		FactorizationResult fr64 = primes.factorize(64);
		assertEquals(1, fr64.factorsCount);
		assertEquals(6, fr64.maxPower);

	}

	
}
