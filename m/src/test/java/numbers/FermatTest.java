package numbers;

import org.junit.jupiter.api.Test;

import primes.EratosteneParallelBuilder;
import primes.IntPrimesBuilder;

class FermatTest {


	@Test
	void testFermatNumbers() throws MException, InterruptedException {
		
		int max = 1000 * 1000;
		IntPrimesBuilder primesFactory = new EratosteneParallelBuilder(max, 8);
		primesFactory.build();
		
		for(int n=1;n<=4;n++) {
			long r=NumberTheory.generateFermatNumber(n);
			//System.out.println("Fermat number ("+n+") = "+r);
			boolean prime=primesFactory.getPrimesList().isPrime(r);
			assert(prime);
		}
		
	}

	@Test
	void test5FermatNumber() throws MException, InterruptedException {	
			long n=5l;
			long r=NumberTheory.generateFermatNumber(5);
			System.out.println("Fermat number ("+n+") = "+r);

			assert(r>Integer.MAX_VALUE);
	}

	@Test
	void test6FermatNumber() throws MException, InterruptedException {	
			long n=6l;
			try {
			long r=NumberTheory.generateFermatNumber(n);
			System.out.println("Fermat number ("+n+") = "+r);
			}catch(MException e) {
				System.out.println("Exception : "+e.getMessage());
			}
	}

}
