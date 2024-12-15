package numbers.big;

import java.math.BigInteger;
import java.util.ArrayList;

public class BigFactorizationResult {

	public BigInteger n;
	public ArrayList<BigFactorResult> factors = new ArrayList<BigFactorResult>();
	public int factorsCount;
	public int maxPower;
	
	
	
	public BigFactorizationResult(BigInteger n) {
		super();
		this.n = n;
	}



	public ArrayList<BigFactorResult> getFactors() {
		return factors;
	}



	public int getFactorsCount() {
		return factorsCount;
	}



	public BigInteger getN() {
		return n;
	}



	public int getMaxPower() {
		return maxPower;
	}


	
}
