package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import numbers.results.DivisorsResult;
import numbers.results.EulerPropertiesResult;
import numbers.results.GcdResult;
import numbers.results.QrmpResult;
import numbers.results.RelativePrimesResult;

class NumberTheoryTest {

	@Test
	void testRelativePrimes() throws MException {
		RelativePrimesResult rpr = NumberTheory.searchRelativePrimes(10);
		assertEquals(4, rpr.count);
	}

	@Test
	void testPower() throws MException {
		long r = NumberTheory.power(2,3,1000);
		assertEquals(8, r);
	}


	// long
	// MAX   9223372036854775807
	// 20! = 2432902008176640000
	@Test
	void testFactorial() throws MException {
		
		Long r = NumberTheory.factorial(20l);
		assertEquals(2432902008176640000l, r);
		
		for(int i=1;i<=20;i++) {
			NumberTheory.factorial((long) i);
		}
	}

	@Test
	void testQuadraticResidueModuloP() {
		long p = 7;
		QrmpResult qrmpResult = NumberTheory.searchQuadraticResidueModuloP(p);
		
		assertEquals(3, qrmpResult.qrm.size());
		assertTrue(qrmpResult.qrm.contains(1l));
		assertTrue(qrmpResult.qrm.contains(2l));
		assertTrue(qrmpResult.qrm.contains(4l));
		assertEquals(1,qrmpResult.consecutives.size());
	}

	@Test
	void testPerfectNumber() {
		DivisorsResult pn = new DivisorsResult();
		
		pn=NumberTheory.searchDivisors(2l);
		assertFalse(pn.isPerfect());
		
		pn=NumberTheory.searchDivisors(6l);
		assertTrue(pn.isPerfect());

		pn=NumberTheory.searchDivisors(496l);
		assertTrue(pn.isPerfect());

	}

	@Test
	void testDivisors() {
		DivisorsResult r;
		
		r=NumberTheory.searchDivisors(0l);
		assertEquals(0, r.count);
	
		r=NumberTheory.searchDivisors(1l);
		assertEquals(1, r.count);

		r=NumberTheory.searchDivisors(6l);
		assertEquals(4, r.count);

		r=NumberTheory.searchDivisors(16l);
		assertEquals(5, r.count);

	}

	@Test
	void testGcd() throws MException {

		GcdResult res;
		
		res = NumberTheory.gcd(34,10);
		//System.out.println(trace.toString());
		assertEquals(2,res.gcd);
		
		res = NumberTheory.gcd(6, 17);
		//System.out.println(trace.toString());
		assertEquals(1,res.gcd);

		res = NumberTheory.gcd(6, 15);
		//System.out.println(trace.toString());
		assertEquals(3,res.gcd);

		res = NumberTheory.gcd(739397, 909090909090909091l);
		//System.out.println(trace.toString());
		assertEquals(1,res.gcd);

		res = NumberTheory.gcd(26, 13);
		//System.out.println(trace.toString());
		assertEquals(13,res.gcd);
	}

	//@Test
	void testPhiDividesNMinusOne() throws MException {
		for(int i=2;i<1000000;i++) {
			if(i%10000==0) {
				System.out.println(" progress ... "+i);
			}
			EulerPropertiesResult p = NumberTheory.searchEulerProperties(i);
			if( !p.prime && 
					 p.phiDividesNMinusOne) {
				System.out.println("testPhiDividesNMinusOne:"+i);
			}
		}
	}

}
