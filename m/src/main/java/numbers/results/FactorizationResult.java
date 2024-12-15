package numbers.results;

import java.util.ArrayList;

public class FactorizationResult {

	public int n;
	public ArrayList<FactorResult> factors = new ArrayList<FactorResult>();
	public int factorsCount;
	public int maxPower;
	
	
	
	public FactorizationResult(int n) {
		super();
		this.n = n;
	}



	public ArrayList<FactorResult> getFactors() {
		return factors;
	}



	public int getFactorsCount() {
		return factorsCount;
	}



	public int getN() {
		return n;
	}



	public int getMaxPower() {
		return maxPower;
	}


	
}
