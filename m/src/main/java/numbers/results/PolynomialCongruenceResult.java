package numbers.results;

import java.util.ArrayList;

public class PolynomialCongruenceResult {

	public boolean completed=false;
	public Long[] a;
	public long m;
	public ArrayList<Long> incongruentSolutions=new ArrayList<>();
	public int degree;
	public boolean prime;
	public boolean mdividesa0;
	public boolean lagrange;
	
	public int getSolutionsCount() {
		return incongruentSolutions.size();
	}
	
	public PolynomialCongruenceResult(Long[] a, long m) {
		super();
		this.a = a;
		this.m = m;
		
		for(int i=0;i<a.length;i++) {
			if(a[i]==null||a[i]<0) {
				break;
			}
			degree=i;
		}
		
		while(degree>0 && a[degree]==0) {
			degree--;
		}
		
	}

	public boolean isCompleted() {
		return completed;
	}

	public Long[] getA() {
		return a;
	}

	

	public long getM() {
		return m;
	}

	public ArrayList<Long> getIncongruentSolutions() {
		return incongruentSolutions;
	}

	public int getDegree() {
		return degree;
	}

	public boolean isPrime() {
		return prime;
	}

	public boolean isMdividesa0() {
		return mdividesa0;
	}

	public boolean isLagrange() {
		return lagrange;
	}

	
	
}
