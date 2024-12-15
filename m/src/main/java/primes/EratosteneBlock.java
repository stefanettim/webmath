package primes;

public class EratosteneBlock {
	private int start;
	private int size;
	private boolean[] composites;

	public EratosteneBlock(int start, int size) {
		super();
		this.start = start;
		this.size = size;

		composites = new boolean[size];

	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return start + size;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isComposite(int n) {
		return composites[n];
	}

	public void setComposite(int n) {
		if (!composites[n])
			composites[n] = true;
	}

	public boolean isPrime(int n) {
		return !composites[n];
	}

	public void setPrime(int n) {
		if (composites[n])
			composites[n] = false;
	}

	public boolean[] getComposites() {
		return composites;
	}

}
