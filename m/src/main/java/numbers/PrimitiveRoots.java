package numbers;

import java.io.IOException;

import numbers.results.FactorizationResult;
import numbers.results.PowersResult;
import numbers.results.PrimitiveRootsResult;
import primes.IntPrimes;
import primes.IntPrimesFactory;

public class PrimitiveRoots {
	
	public static boolean expectedPrimitiveRoots(int n, IntPrimes ip) throws MException {
		
		if(n==2) {
			return true;
		}

		if(n==4) {
			return true;
		}

		FactorizationResult fr = ip.factorize((int) n);

		if(fr.factors.size()==1) {
			if(fr.factors.getFirst().factor>2) {
				return true;
			}
		}

		if(fr.factors.size()==2) {
			if(fr.factors.getFirst().factor==2 && fr.factors.getFirst().power==1) {
				if(fr.factors.get(1).factor>2) {
					return true;
				}
			}
		}

		return false;
	}

	public static PrimitiveRootsResult searchPrimitiveRoots(int n) throws MException, ClassNotFoundException, IOException, InterruptedException {
		return searchPrimitiveRoots(n, IntPrimesFactory.getCachedIntPrimes() );
	}

	public static PrimitiveRootsResult searchPrimitiveRoots(int n, IntPrimes ip) throws MException, ClassNotFoundException, IOException {
		
		PrimitiveRootsResult r = new PrimitiveRootsResult(n);
		
		
		boolean expected = expectedPrimitiveRoots(n, ip);
		r.setExpectedRoots(expected);
	
		int et = (int) NumberTheory.eulerTotientFunctionByRelativePrimes(n);
		
		r.setPhiOfN(et);
		
		int phiOfPhi = (int) NumberTheory.eulerTotientFunctionByRelativePrimes(et);
		r.setPhiOfPhi(phiOfPhi);
		
		for(int l=1; l<=n-1; l++) {

			int p=1;
			int i=1;
			for(i=1; i<n; i++) {
				p=(p*l)%n;
				if(p==1) {
					break;
				}
			}
			
			if(p==1l && i==et) {
				r.getRoots().add(l);
			}
		

		}
		
		for(int i=1; i<=n-1; i++) {
			PowersResult powers = NumberTheory.powers(i, n);
			r.getPowersResult().add(powers);
		}
		
		
		r.setCompleted(true);
		return r;
		
	}


}
