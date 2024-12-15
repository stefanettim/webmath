package numbers.rsa;

import org.junit.jupiter.api.RepeatedTest;

import numbers.MException;

class Mersenne67Test {
	
	
	@RepeatedTest(3)
	void m67Testcase() throws MException {
		//RSAAlghoritmsFactory.setDefaultRho();
		boolean b = RSASolver.testcase( RSANumbers.M67 ); 
		assert(b);
	}

}
