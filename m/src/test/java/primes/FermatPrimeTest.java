package primes;

import org.junit.jupiter.api.Test;

// 2.300e+10       1008309544        22999999987     6417   6.36e-04

public class FermatPrimeTest {

	public static boolean testFermat(int l) throws Exception {
		double e = Math.pow((double) 2, (double) l);

		if (e > Integer.MAX_VALUE)
			throw new Exception("Too big : " + e);

		double r = e % l;
		return r == 2d;
	}

	@Test
	public void testFermatPrimeTest() throws Exception {
		int max = 31;
		IntPrimes primes = IntPrimesFactory.getCachedIntPrimes();
		
		int np = primes.getCount();
		
		assert(np>0);
		
		for (int prime: primes.getPrimes()) {
			if(prime>2 && prime<max) {
				System.out.println(prime);
				boolean fermatTest = testFermat(prime);
				assert(fermatTest);
			}
		}

	}


}