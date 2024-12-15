package numbers.results;

import java.util.ArrayList;

public class RelativePrimesResult {

	public boolean completed=false;
	public long n;
	public long count=0l;
	public ArrayList<Long> relativePrimes = new ArrayList<Long>();
	public ArrayList<Long> reducedResidueSystem = new ArrayList<Long>();

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
	public ArrayList<Long> getRelativePrimes() {
		return relativePrimes;
	}
	public void setRelativePrimes(ArrayList<Long> relativePrimes) {
		this.relativePrimes = relativePrimes;
	}
	public ArrayList<Long> getReducedResidueSystem() {
		return reducedResidueSystem;
	}
	public void setReducedResidueSystem(ArrayList<Long> reducedResidueSystem) {
		this.reducedResidueSystem = reducedResidueSystem;
	}
	public boolean isCompleted() {
		return completed;
	}

	
	
	
}
