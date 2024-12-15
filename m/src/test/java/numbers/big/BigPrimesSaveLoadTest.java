package numbers.big;

import java.io.IOException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import numbers.MException;

@TestMethodOrder(OrderAnnotation.class)
class BigPrimesSaveLoadTest {

	int LIMIT = 1000;

	static BigPrimes primes;

	@Test
	@Order(1)    
	void testGenerate() throws MException, IOException, ClassNotFoundException, InterruptedException {
		primes = BigPrimesFactory.generate(LIMIT);
	}
	
	@Test
	@Order(2)    
	void testSaveTestcase() throws MException, IOException, ClassNotFoundException, InterruptedException {
		BigPrimesFactory.saveTestcase(primes);
	}

	@Test
	@Order(3)    
	void testLoadTestcase() throws MException, IOException, ClassNotFoundException, InterruptedException {
		primes = BigPrimesFactory.loadTestcase();
		assert(primes.isPrime(997));
	}
	
	@Test
	@Order(4)    
	void testLoad1M() throws MException, IOException, ClassNotFoundException, InterruptedException {
		primes = BigPrimesFactory.load("1M");
		assert(primes.isPrime(999983));
	}

	@Test
	@Order(5)    
	void testLoadFromProperties() throws MException, IOException, ClassNotFoundException, InterruptedException {
		primes = BigPrimesFactory.load();
		assert(primes.isPrime(999983));
	}
}
