package numbers.results;

import java.util.ArrayList;

public class LinearCongruenceResult {

	public boolean completed=false;
	public long a;
	public long b;
	public long c;
	public long d;
	public boolean solvable;
	public ArrayList<Long> incongruentSolutions=new ArrayList<>();
	
	public LinearCongruenceResult(Long a, Long b, Long c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
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

	public void setA(long a) {
		this.a = a;
	}

	public long getB() {
		return b;
	}

	public void setB(long b) {
		this.b = b;
	}

	public long getC() {
		return c;
	}

	public void setC(long c) {
		this.c = c;
	}

	public long getD() {
		return d;
	}

	public void setD(long d) {
		this.d = d;
	}

	public boolean isSolvable() {
		return solvable;
	}

	public void setSolvable(boolean solvable) {
		this.solvable = solvable;
	}

	public ArrayList<Long> getIncongruentSolutions() {
		return incongruentSolutions;
	}

	public void setIncongruentSolutions(ArrayList<Long> incongruentSolutions) {
		this.incongruentSolutions = incongruentSolutions;
	}

	
	
}
