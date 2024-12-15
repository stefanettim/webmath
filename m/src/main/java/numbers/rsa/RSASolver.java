package numbers.rsa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import numbers.MException;

public class RSASolver {

	public static BigInteger findFactor(BigInteger problem) throws MException {
		RSAAlghoritm alghoritm = RSAAlghoritmsFactory.getDefaultAlghortirm();
		return alghoritm.findFactor(problem);
	}
	
	public static BigInteger findFactor(BigInteger problem, String alghoritmLabel ) throws MException {
		RSAAlghoritm alghoritm = RSAAlghoritmsFactory.getAlghortirm(alghoritmLabel);
		return alghoritm.findFactor(problem);
	}

	public static List<BigInteger> factorize(BigInteger n ) throws MException {
		
		List<BigInteger> s = new ArrayList<>();
		RSAAlghoritm alghoritm = RSAAlghoritmsFactory.getDefaultAlghortirm();
		
		BigInteger otherFactors = n;
		while(true) {
			BigInteger f = alghoritm.findFactor(otherFactors);
			
			if(otherFactors.equals(BigInteger.ONE)) {
				break;
			}
			
			System.out.println("foctor found: "+f);
			s.add(f);
			otherFactors=otherFactors.divide(f);
		}
		
		return s;
	}

	public static boolean testcase(RSANumber nf) throws MException {
		RSAAlghoritm alghoritm = RSAAlghoritmsFactory.getDefaultAlghortirm();
		BigInteger s = alghoritm.findFactor(nf.getN());
		boolean t = nf.getF().contains(s);
		//System.out.println(s.factors);
		return t;
	}
	
	public static void main(String[] args) throws MException {
		System.out.println(RSASolver.testcase( RSANumbers.F6 ));
		System.out.println(RSASolver.testcase( RSANumbers.M67 ));
	}

	

}
