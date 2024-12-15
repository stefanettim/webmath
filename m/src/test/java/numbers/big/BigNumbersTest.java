package numbers.big;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import numbers.MException;

class BigNumbersTest {
	
	private int repeat=100000;
	protected BigInteger r;

	@Test
	void testPower() throws MException {	
			BigInteger r = BigInteger.TWO.pow(64);
			System.out.println("2^64="+r);
	}

	@Test
	void test0Power() throws MException {	
			BigInteger r = BigInteger.TWO.pow(0);
			assert(BigInteger.ONE.equals(r));
	}

	@Test
	void testMyPowerSpeed() throws MException {	
		for(int i=0;i<repeat;i++) {
			r = BigNumbers.myPower(BigInteger.TWO,BigInteger.valueOf(64l));
		}	
	}

	@Test
	void testPowSpeed() throws MException {	
		for(int i=0;i<repeat;i++) {
			r = BigInteger.TWO.pow(64);
		}	
	}
	
}
