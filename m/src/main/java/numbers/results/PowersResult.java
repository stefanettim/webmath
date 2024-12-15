package numbers.results;

import java.util.ArrayList;
import java.util.List;


// a^x (mod p) 1<=x<=p-1
public class PowersResult {

	public long a;
	public long n;
	public List<Long> powers = new ArrayList<Long>();
	public long ones;
	
	boolean completed=false;


	public PowersResult(long a, long n) {
		super();
		this.a = a;
		this.n = n;
	}

	public long getA() {
		return a;
	}

	public void setA(long a) {
		this.a = a;
	}

	public List<Long> getPowers() {
		return powers;
	}

	public void setPowers(List<Long> powers) {
		this.powers = powers;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public long getOnes() {
		return ones;
	}

	public void setOnes(long ones) {
		this.ones = ones;
	}

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}
	



	
}
