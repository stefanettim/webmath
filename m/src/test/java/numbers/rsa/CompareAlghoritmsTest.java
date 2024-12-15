package numbers.rsa;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;

import numbers.MException;

class CompareAlghoritmsTest {
	
	RSANumber nf = RSANumbers.M67; // 1.5 wheel 0.032 rho
	//RSANumber nf = RSANumbers.F6; // easy
	//RSANumber nf = RSANumbers.F7; // 800s rho
	//RSANumber nf = RSANumbers.F8; // 56s rho
	//RSANumber nf = RSANumbers.F11; //easy
	//RSANumber nf = RSANumbers.F12; //easy

	//@Test
	@RepeatedTest(1)
	@Order(1) 
	void jump1Testcase() throws MException {
		RSAAlghoritmsFactory.setDefault(RSAAlghoritmsFactory.RHO);
		boolean b = RSASolver.testcase( nf ); 
		assert(b);
	}

	//@Test
	@RepeatedTest(1)
	@Order(2) 
	void jump2Testcase() throws MException {
		RSAAlghoritmsFactory.setDefault(RSAAlghoritmsFactory.WHEEL);
		boolean b = RSASolver.testcase( nf ); 
		assert(b);
	}

}
