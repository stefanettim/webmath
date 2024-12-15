package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import numbers.results.PrimitiveRootsResult;

class PrimitiveRootsTest {

	@Test
	void primitiveRoots5() throws MException, ClassNotFoundException, IOException, InterruptedException {
		
		int n=5;
		
		PrimitiveRootsResult r = PrimitiveRoots.searchPrimitiveRoots(n);
		assert(r.getRoots().contains(2));
		assert(r.getRoots().contains(3));
		
		long roots=r.getRoots().size();
		assertEquals(2,roots);
		
		long totient = NumberTheory.eulerTotientFunctionByRelativePrimes(n);
		long totientTotient = NumberTheory.eulerTotientFunctionByRelativePrimes(totient);
		assertEquals(totientTotient,roots);
		
		assert(r.isExpectedRoots());
		
	}

	@Test
	void primitiveRoots() throws MException, ClassNotFoundException, IOException, InterruptedException {
		for(int n : new int[] {2,3,4,7,9,18,54}) {
			PrimitiveRootsResult r = PrimitiveRoots.searchPrimitiveRoots(n);
			assert(r.isExpectedRoots());
			assert(!r.getRoots().isEmpty());
		}
	}
	
	@Test
	void primitiveRoots9() throws MException, ClassNotFoundException, IOException, InterruptedException {
		PrimitiveRootsResult r = PrimitiveRoots.searchPrimitiveRoots(9);
		assert(r.getRoots().contains(2));
		assert(r.getRoots().contains(5));
		assertEquals(2, r.getRoots().size());
		assert(r.isExpectedRoots());
	}
	
	@Test
	void primitiveRoots11() throws MException, ClassNotFoundException, IOException, InterruptedException {
		PrimitiveRootsResult r = PrimitiveRoots.searchPrimitiveRoots(11);
		assert(r.getRoots().contains(2));
		assert(r.getRoots().contains(6));
		assert(r.getRoots().contains(7));
		assert(r.getRoots().contains(8));
		assertEquals(4, r.getRoots().size());
		assert(r.isExpectedRoots());
	}
	
	@Test
	void primitiveRoots13() throws MException, ClassNotFoundException, IOException, InterruptedException {
		PrimitiveRootsResult r = PrimitiveRoots.searchPrimitiveRoots(13);
		assert(r.getRoots().contains(2));
		assert(r.getRoots().contains(6));
		assert(r.getRoots().contains(7));
		assert(r.getRoots().contains(11));
		assertEquals(4, r.getRoots().size());
		assert(r.isExpectedRoots());
	}
	
	@Test
	void primitiveRoots15() throws MException, ClassNotFoundException, IOException, InterruptedException {
		PrimitiveRootsResult r = PrimitiveRoots.searchPrimitiveRoots(15);
		assertEquals(0, r.getRoots().size());
		assert(!r.isExpectedRoots());
	}
	

}
