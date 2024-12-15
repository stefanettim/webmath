package primes;

import java.io.IOException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import numbers.MException;

@TestMethodOrder(OrderAnnotation.class)
class PrimesSaveLoadTest {

	//int MAIN_LIMIT = 2*1000*1000*1000;
	int MAIN_LIMIT = 2*1000*1000;
	// MAX_INT 2 147 483 647
	static IntPrimes primes;

	@Test
	@Order(1)    
	void testGenerate() throws MException, IOException, ClassNotFoundException, InterruptedException {
		primes = IntPrimesFactory.generate(MAIN_LIMIT);
	}
	
	@Test
	@Order(2)    
	void testSaveTestcase() throws MException, IOException, ClassNotFoundException, InterruptedException {
		IntPrimesFactory.saveTestcase(primes);
	}

	@Test
	@Order(3)    
	void testLoadTestcase() throws MException, IOException, ClassNotFoundException, InterruptedException {
		primes = IntPrimesFactory.loadTestcase();
		assert(primes.isPrime(997));
	}
	
	@Test
	@Order(4)    
	void testLoad1M() throws MException, IOException, ClassNotFoundException, InterruptedException {
		primes = IntPrimesFactory.load("1M");
		assert(primes.isPrime(999983));
	}

	@Test
	@Order(5)    
	void testLoadFromProperties() throws MException, IOException, ClassNotFoundException, InterruptedException {
		primes = IntPrimesFactory.load();
		assert(primes.isPrime(999983));
	}
	
	@Test
	void testSaveLoadTestMain() throws MException, IOException, ClassNotFoundException, InterruptedException {
		IntPrimes primes;
		//primes = PrimesFactory.generate(10*1000*1000);
		//PrimesFactory.saveMain(primes);
		primes = IntPrimesFactory.loadMain();
		assert(primes.isPrime(997l));
	}

}
