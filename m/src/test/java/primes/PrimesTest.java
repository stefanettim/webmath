package primes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import numbers.MException;

class PrimesTest {

	/*
	private int max=10 * 1000;
	private int expected=1229;

	private int max=1000 * 1000;
	private int expected=78498;

	private int max=10 *1000 *1000;
	private int expected=664579;

	private int max=100 *1000 *1000;
	private int expected=5761455;

	private int max=1000 *1000 *1000;
	private int expected=50847534;

	private int max=10 *1000 *1000 *1000; //TOO BIG
	private int expected=455052511;
	*/
	
	private int max=10 * 1000;
	private int expected=1229;

	private int threads=8;
	private int kBlockSize=256;
	private int mDelay=1;

	void testEratosteneBuilder(IntPrimesBuilder b) throws InterruptedException, MException {
		b.build();
		int pi = b.getNPrimes();
		//System.out.println(max+" "+pi);
		assertEquals(expected, pi);
		assert(b.getPrimesList().isPrime(7));
	}

	@Test
	void testEratosteneBlockParallelBuilder() throws InterruptedException, MException {
		IntPrimesBuilder b = new EratosteneBlockParallelBuilder(max, threads, kBlockSize, mDelay);
		testEratosteneBuilder(b);
	}

	@Test
	void testEratosteneBlockBuilder() throws InterruptedException, MException {
		IntPrimesBuilder b = new EratosteneBlockBuilder(max, kBlockSize);
		testEratosteneBuilder(b);
	}

	@Test
	void testEratosteneParallelBuilder() throws InterruptedException, MException {
		IntPrimesBuilder b = new EratosteneParallelBuilder(max, threads);
		testEratosteneBuilder(b);
	}

	@Test
	void testEratosteneSimpleBuilder() throws InterruptedException, MException {
		IntPrimesBuilder b = new EratosteneSimpleBuilder(max);
		testEratosteneBuilder(b);
	}

	
}
