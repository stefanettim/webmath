package numbers.results;

import java.util.ArrayList;

public class EulerTheoremResult {

	public boolean completed=false;
	public int a;
	public int m;
	public long gcd;
	public boolean solvable;
	public RelativePrimesResult relativePrimesResult;
	public ArrayList<Long> alternatedResidueSystem = new ArrayList<Long>();
	public ArrayList<Long> pairing = new ArrayList<Long>();
	public boolean primeModule;

	public EulerTheoremResult(int a, int m) {
		super();
		this.a = a;
		this.m = m;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public long getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public long getGcd() {
		return gcd;
	}

	public void setGcd(long gcd) {
		this.gcd = gcd;
	}

	public boolean isSolvable() {
		return solvable;
	}

	public void setSolvable(boolean solvable) {
		this.solvable = solvable;
	}

	public RelativePrimesResult getRelativePrimesResult() {
		return relativePrimesResult;
	}

	public void setRelativePrimesResult(RelativePrimesResult relativePrimesResult) {
		this.relativePrimesResult = relativePrimesResult;
	}

	public ArrayList<Long> getAlternatedResidueSystem() {
		return alternatedResidueSystem;
	}

	public void setAlternatedResidueSystem(ArrayList<Long> alternatedResidueSystem) {
		this.alternatedResidueSystem = alternatedResidueSystem;
	}

	public ArrayList<Long> getPairing() {
		return pairing;
	}

	public void setPairing(ArrayList<Long> pairing) {
		this.pairing = pairing;
	}

	public boolean isPrimeModule() {
		return primeModule;
	}

	public void setPrimeModule(boolean primeModule) {
		this.primeModule = primeModule;
	}
	
	
}
