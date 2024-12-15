package numbers.results;

import java.util.ArrayList;
import java.util.List;

public class PowerResult {

	public long a;
	public long n;
	public long b;
	public long m;
	public List<Long> solutions = new ArrayList<Long>();
	
	boolean exponent= false;
	boolean completed=false;
	
	public PowerResult(long a, long n, long b, long m, boolean exponent) {
		super();
		this.a = a;
		this.n = n;
		this.b = b;
		this.m = m;
		this.exponent=exponent;
	}
	public List<Long> getSolutions() {
		return solutions;
	}
	public void setSolutions(List<Long> solutions) {
		this.solutions = solutions;
	}
	public long getA() {
		return a;
	}
	public long getB() {
		return b;
	}
	public long getN() {
		return n;
	}
	public long getM() {
		return m;
	}
	public boolean isExponent() {
		return exponent;
	}
	public void setExponent(boolean exponent) {
		this.exponent = exponent;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	



	
}
