package numbers.results;

import java.util.ArrayList;
import java.util.List;

public class PrimitiveRootsResult {

	public int n;
	public int phiOfN;
	public int phiOfPhi;
	boolean expectedRoots=false;
	public List<Integer> roots = new ArrayList<Integer>();
	public List<PowersResult> powersResult = new ArrayList<>();
	boolean completed=false;
	
	public PrimitiveRootsResult(int n) {
		super();
		this.n = n;
	}
	
	public boolean isPrimitiveRoot(int r) {
		return roots.contains(r);
	}

	public List<Integer> getRoots() {
		return roots;
	}

	public long getN() {
		return n;
	}
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public long getPhiOfN() {
		return phiOfN;
	}

	public void setPhiOfN(int phiOfN) {
		this.phiOfN = phiOfN;
	}

	public long getPhiOfPhi() {
		return phiOfPhi;
	}

	public void setPhiOfPhi(int phiOfPhi) {
		this.phiOfPhi = phiOfPhi;
	}

	public boolean isExpectedRoots() {
		return expectedRoots;
	}

	public void setExpectedRoots(boolean expectedRoots) {
		this.expectedRoots = expectedRoots;
	}

	public List<PowersResult> getPowersResult() {
		return powersResult;
	}

	public void setPowersResults(List<PowersResult> powersResult) {
		this.powersResult = powersResult;
	}
	



	
}
