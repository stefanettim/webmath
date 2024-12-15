package primes;

public abstract class IntPrimesBuilder {

	protected int max = 100;
	protected int[] primes;
	protected int nPrimes = 0;
	protected IntPrimes primesList;

	public IntPrimesBuilder(int max) {
		this.max = max;

		double dm = (double) max;
		double np = 1 + dm / (Math.log(dm) - 1) + dm / 100;
		int snp = (int) np;
		if(snp<1) {
			snp=1;
		}
		//System.out.println("stima       : " + snp);
		primes = new int[snp];

	}

	public IntPrimesBuilder() {
		super();
	}

	public int getMax() {
		return max;
	}

	public int getMaxPrime() {
		return primes[nPrimes];
	}

	public int getNPrimes() {
		return nPrimes;
	}

	public int[] getPrimes() {
		return primes;
	}

	public abstract void build() throws InterruptedException;

	public void test() throws InterruptedException {
		long start = System.currentTimeMillis();
		System.out.print("Computing.. ");
		build();
		System.out.print(" ..done");
		long stop = System.currentTimeMillis();
		System.out.println("  elapsed        : " + (double) (stop - start) / 1000);
		System.out.println("primes         : " + getNPrimes()+" max prime      : " + getMaxPrime());

		//System.out.println(Arrays.toString(primes));
	}

	public IntPrimes getPrimesList() {
		
		if(primesList==null) {
			primesList = new IntPrimes();
			primesList.limit=max;
			for(int p:primes) {
				if(p>0) {
					primesList.primes.add(p);
				}
			}
		}
		return primesList;
	}

	
}