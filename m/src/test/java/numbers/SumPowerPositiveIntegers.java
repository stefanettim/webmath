package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import numbers.results.SumOfPositiveIntegersResult;

class SumPositiveIntegers {

	@Test
	void testSumPowerOf2PositiveIntegers() throws MException {
		SumOfPositiveIntegersResult al;
		
		al = NumberTheory.sumOfPositiveIntegers(4, "POWER2");
		assertEquals(4, al.solutions);		

		al = NumberTheory.sumOfPositiveIntegers(40, "POWER2");
		assertEquals(390, al.solutions);		
	}

	@Test
	void testSumOddPositiveIntegers() throws MException {
		SumOfPositiveIntegersResult al = NumberTheory.sumOfPositiveIntegers(5, "ODD");
		//System.out.println("--- Testcase");
		//for(ArrayList<Integer> a:al) {  System.out.println(a);	}
		assertEquals(3, al.solutions);		
	}

	@Test
	void testSumDistinctPositiveIntegers() throws MException {
		SumOfPositiveIntegersResult al;
		
		al = NumberTheory.sumOfPositiveIntegers(5, "DISTINCT");
		assertEquals(3, al.solutions);		

		al = NumberTheory.sumOfPositiveIntegers(30, "DISTINCT");
		// https://math.stackexchange.com/questions/2077769/how-many-different-ways-can-a-number-n-be-expressed-as-a-sum-of-k-different-posi
		// 1,1,2,2,3,4,5,6,8,10,12,15,18,22,27,32,38,46,54,64,76,89,104,122,142,165,192,222,256,296,...
		assertEquals(296, al.solutions);		
	}

	@Test
	void testSumOfMax2DistinctPositiveIntegers() throws MException {
		SumOfPositiveIntegersResult al = NumberTheory.sumOfPositiveIntegers(4, "MAX2");
		assertEquals(4, al.solutions);		
	}

	@Test
	void testSumOfNotMultipleOf3PositiveIntegers() throws MException {
		SumOfPositiveIntegersResult al = NumberTheory.sumOfPositiveIntegers(4, "NOTMUL3");
		assertEquals(4, al.solutions);		
	}


}
