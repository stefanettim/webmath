package numbers.results;

import java.util.ArrayList;

public class DivisorsResult {

	public long n;
	public long count=0l;
	public long sum=0l;
	public long aliquotSum=0l;
	public ArrayList<Long> divisors = new ArrayList<Long>();
	
	
	public boolean isPerfect() {
		return n>0 && aliquotSum==n;
	}

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getSum() {
		return sum;
	}

	public void setSum(long sum) {
		this.sum = sum;
	}

	public long getAliquotSum() {
		return aliquotSum;
	}

	public void setAliquotSum(long aliquotSum) {
		this.aliquotSum = aliquotSum;
	}

	public ArrayList<Long> getDivisors() {
		return divisors;
	}

	public void setDivisors(ArrayList<Long> divisors) {
		this.divisors = divisors;
	}


	
}
