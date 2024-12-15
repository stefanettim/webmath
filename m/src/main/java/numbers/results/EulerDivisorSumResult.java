package numbers.results;

import java.util.ArrayList;

public class EulerDivisorSumResult {

	public boolean completed=false;
	public long n;
	public long d;
	public boolean divisor;
	public RelativePrimesResult relativePrimesResult;
	public ArrayList<Long> td = new ArrayList<Long>();
	public long tdCount;
	public long nOverD;
	public RelativePrimesResult relativePrimesResultNOverD;

	public EulerDivisorSumResult(long d) {
		super();
		this.d = d;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public long getD() {
		return d;
	}

	public boolean isDivisor() {
		return divisor;
	}

	public RelativePrimesResult getRelativePrimesResult() {
		return relativePrimesResult;
	}

	public ArrayList<Long> getTd() {
		return td;
	}

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}

	public long getnOverD() {
		return nOverD;
	}

	public void setnOverD(long nOverD) {
		this.nOverD = nOverD;
	}


	public long getTdCount() {
		return tdCount;
	}

	public RelativePrimesResult getRelativePrimesResultNOverD() {
		return relativePrimesResultNOverD;
	}
	
	
}
