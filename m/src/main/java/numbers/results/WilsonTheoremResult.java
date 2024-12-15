package numbers.results;

import java.util.ArrayList;


public class WilsonTheoremResult {

	public boolean completed=false;
	public long p;
	public long inverseOfOne;
	public long f;
	public long q;
	public long r;
	public boolean prime;
	public ArrayList<Pair> pairing = new ArrayList<>();

	public WilsonTheoremResult(Long p) {
		super();
		this.p=p;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public long getP() {
		return p;
	}

	public void setP(long p) {
		this.p = p;
	}

	public long getF() {
		return f;
	}

	public void setF(long f) {
		this.f = f;
	}

	public boolean isPrime() {
		return prime;
	}

	public void setPrime(boolean prime) {
		this.prime = prime;
	}

	public long getQ() {
		return q;
	}

	public void setQ(long q) {
		this.q = q;
	}

	public long getR() {
		return r;
	}

	public void setR(long r) {
		this.r = r;
	}

	public long getInverseOfOne() {
		return inverseOfOne;
	}

	public void setInverseOfOne(long inverseOfOne) {
		this.inverseOfOne = inverseOfOne;
	}

	public ArrayList<Pair> getPairing() {
		return pairing;
	}

	public void setPairing(ArrayList<Pair> pairing) {
		this.pairing = pairing;
	}


	
	
}
