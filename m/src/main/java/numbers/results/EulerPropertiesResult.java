package numbers.results;


public class EulerPropertiesResult {

	public long n;
	public boolean prime;
	public RelativePrimesResult relativePrimesResult;
	public boolean phiDividesNMinusOne;
	public DivisorsResult divisorsResult;
	

	public EulerPropertiesResult(long n) {
		super();
		this.n = n;
	}

	public long getN() {
		return n;
	}

	public boolean isPrime() {
		return prime;
	}

	public RelativePrimesResult getRelativePrimesResult() {
		return relativePrimesResult;
	}

	public boolean isPhiDividesNMinusOne() {
		return phiDividesNMinusOne;
	}

	public DivisorsResult getDivisorsResult() {
		return divisorsResult;
	}
	
}
