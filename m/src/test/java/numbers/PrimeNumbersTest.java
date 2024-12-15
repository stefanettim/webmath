package numbers;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import numbers.results.Pair;
import primes.IntPrimes;
import primes.IntPrimesFactory;

class PrimeNumbersTest {


	@Test
	void piTest() throws MException, InterruptedException, ClassNotFoundException, IOException {

		IntPrimes primes = IntPrimesFactory.load();
		ArrayList<Pair> pairs = PrimeNumbers.generatePiList(primes.primes, 20);
		
		for(Pair pair : pairs) {
			System.out.println("max "+pair.getA()+" primes "+pair.getB());
		}
	}

}
