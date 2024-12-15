package numbers.big;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import numbers.MException;
import numbers.results.BigFermatResult;

class BigFermatTest {

	@Test
	void testSmallFermatNumbers() throws MException {
		for(int n=1;n<=8;n++) {
			BigInteger r = BigNumbers.generateFermatNumber(n);
			System.out.println("Fermat number " + n + " = " + r + " bitLength:" + r.bitLength());
		}
	}

	@Test
	void test5FermatNumber() throws MException, InterruptedException, ClassNotFoundException, IOException {
		int n = 5;
		BigInteger r = BigNumbers.generateFermatNumber(n);

		assert (r.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0);
		
		BigPrimes bigPrimes = BigPrimesFactory.loadTestcase();
		
		for(BigInteger p:bigPrimes.primes) {
			if(r.mod(p)==BigInteger.ZERO) {
				System.out.println(p+"|"+r);
			}
		}
	}

	@Test
	void test6FermatNumber() throws MException, InterruptedException, ClassNotFoundException, IOException {
		int n = 6;
		BigInteger r = BigNumbers.generateFermatNumber(n);
		assert (r.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0);
		
		BigPrimes bigPrimes = BigPrimesFactory.loadTestcase();
		
		for(BigInteger p:bigPrimes.primes) {
			if(r.mod(p).equals(BigInteger.ZERO)) {
				System.out.println(p+"|"+r);
			}
		}
	}

	@Test
	void testAnalyzeFermatNumber() throws MException, InterruptedException, ClassNotFoundException, IOException {
		
		BigPrimes bigPrimes = BigPrimesFactory.load();
		
		for(int n=1;n<=6;n++) {
			BigFermatResult r = BigNumbers.analyzeFermatNumber(n, bigPrimes);
			System.out.println("Analyze Fermat number " + r.getOrder() + "=" + r.getFermatNumber() + " prime:" + r.getPrime()+" verified:"+r.getVerified()+" "+r.getDivisors());
		}
	}

	@Test
	void test11FermatNumber() throws MException, InterruptedException, ClassNotFoundException, IOException {
		BigPrimes bigPrimes = BigPrimesFactory.load();
		BigFermatResult r = BigNumbers.analyzeFermatNumber(11, bigPrimes);
		System.out.println("Analyze Fermat number " + r.getOrder() + "=" + r.getFermatNumber());
		System.out.println(" prime:" + r.getPrime()+" verified:"+r.getVerified()+" "+r.getDivisors());
	}
}
