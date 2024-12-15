package numbers.rsa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import numbers.MException;

class BasicTest {
	
	@BeforeAll
	static void before() {
//		RSAAlghoritmsFactory.setDefaultTrial();
//		RSAAlghoritmsFactory.setDefaultJumpArraySkip();
//		RSAAlghoritmsFactory.setDefaultWheel();
		RSAAlghoritmsFactory.setDefaultRho();
	}

	@Test
	void sqrt10Test() throws MException {
		BigInteger n = BigInteger.valueOf(10l);
		BigInteger sqrt = n.sqrt();
		assertEquals(3, sqrt.intValue());
	}

	@Test
	void sqrt121Test() throws MException {
		BigInteger n = BigInteger.valueOf(121l);
		BigInteger sqrt = n.sqrt();
		assertEquals(11, sqrt.intValue());
	}

	@Test
	void tenTest() throws MException {
		BigInteger s = RSAAlghoritmsFactory.getDefaultAlghortirm().findFactor(BigInteger.valueOf(10));	
		assertEquals(2l, s.longValue());
		//System.out.println(s.getFactors());
	}

	@Test
	void n10Testcase() throws MException {
		assert( RSASolver.testcase( new RSANumber("10", new String[]{"2","5"}) ));
	}

	@Test
	void n121Testcase() throws MException {
		assert( RSASolver.testcase( new RSANumber("121", new String[]{"11"}) ) );
	}

	@Test
	void f5Testcase() throws MException {
		assert( RSASolver.testcase( RSANumbers.F5 ) );
	}

	@Test
	void f6Testcase() throws MException {
		boolean b = RSASolver.testcase( RSANumbers.F6 ); 
		assert(b);
	}

}
