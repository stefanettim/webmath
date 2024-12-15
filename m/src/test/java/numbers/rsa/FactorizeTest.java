package numbers.rsa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import numbers.MException;

class FactorizeTest {
	
	@BeforeAll
	static void before() {
//		RSAAlghoritmsFactory.setDefaultTrial();
//		RSAAlghoritmsFactory.setDefaultJumpArraySkip();
		RSAAlghoritmsFactory.setDefaultWheel();
//		RSAAlghoritmsFactory.setDefaultRho(); // BAD
	}


	@Test
	void primeTest() throws MException {
		List<BigInteger> l = RSASolver.factorize(new BigInteger("3"));
		assert(l.contains(new BigInteger("3")));
		assertEquals(1,l.size());
	}

	@Test
	void tenTest() throws MException {
		List<BigInteger> l = RSASolver.factorize(new BigInteger("10"));
		assert(l.contains(new BigInteger("2")));
		assert(l.contains(new BigInteger("5")));
		assertEquals(2,l.size());
	}

	@Test
	void f8Test() throws MException {
		List<BigInteger> l = RSASolver.factorize(RSANumbers.F6.getN());
		assertEquals(2,l.size());
	}

}
