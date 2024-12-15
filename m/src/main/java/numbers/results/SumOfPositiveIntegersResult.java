package numbers.results;

import java.util.ArrayList;

public class SumOfPositiveIntegersResult {

	boolean completed=false;
	public long n;
	public ArrayList<Long> values=new ArrayList<>();
	public ArrayList<Long> weights=new ArrayList<>();
	public ArrayList<ArrayList<Long>> ways = new ArrayList<>();
	public long solutions;
	
	public SumOfPositiveIntegersResult(long n) {
		super();
		this.n = n;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}

	public ArrayList<Long> getValues() {
		return values;
	}

	public void setValues(ArrayList<Long> values) {
		this.values = values;
	}

	public ArrayList<Long> getWeights() {
		return weights;
	}

	public void setWeights(ArrayList<Long> weights) {
		this.weights = weights;
	}

	public ArrayList<ArrayList<Long>> getWays() {
		return ways;
	}

	public void setWays(ArrayList<ArrayList<Long>> ways) {
		this.ways = ways;
	}

	public long getSolutions() {
		return solutions;
	}

	public void setSolutions(long solutions) {
		this.solutions = solutions;
	}

	
}
