package numbers;

import java.util.ArrayList;

import numbers.results.DivisorsResult;
import numbers.results.EulerDivisorSumResult;
import numbers.results.EulerPropertiesResult;
import numbers.results.GcdResult;
import numbers.results.PowersResult;
import numbers.results.QrmpResult;
import numbers.results.RelativePrimesResult;
import numbers.results.SumOfPositiveIntegersResult;
 

public class NumberTheory {

	public static long eulerTotientFunctionByRelativePrimes(long n) throws MException {
		RelativePrimesResult res = searchRelativePrimes(n);
		return res.count;
	}
	
	
	public static RelativePrimesResult searchRelativePrimes(long n) throws MException {
		RelativePrimesResult res = new RelativePrimesResult();
		res.n=n;
		
		if(n==1) {
			res.relativePrimes.add(1l);
			res.reducedResidueSystem.add(0l);
			res.count++;
			return res;
		}

		for (long rp = 1; rp < n; rp++) {
			GcdResult gcdr = gcd(n,rp);
			if(gcdr.gcd==1) {
				res.relativePrimes.add(rp);
				long r = rp%n;
				res.reducedResidueSystem.add(r);
				res.count++;
			}
		}

		res.completed=true;
		return res;
	}
	
	public static ArrayList<DivisorsResult> listDivisors(boolean onlyPerfect, long n) {
		ArrayList<DivisorsResult> divisorsList = new ArrayList<>();
		for(long l=1;l<=n;l++) {
			DivisorsResult divisors = NumberTheory.searchDivisors(l);
			if(!onlyPerfect || divisors.isPerfect()) {
			  divisorsList.add(divisors);
			}
		}
		return divisorsList;
	}

	public static QrmpResult searchQuadraticResidueModuloP(long p) {
		
		QrmpResult res = new QrmpResult();
		res.p=p;
		
		// QuadraticResidueModulo p
		// r | p!|r & p|m^2-r 1<=m<p 

		long last=p+1;
		for (long r = 1; r < p; r++) {
			for (long m = 1; m < p; m++) {
				if((m*m-r)%p==0) {
					res.qrm.add(r);
					if(r==last+1) {
						res.consecutives.add(r);
					}
					last=r;
					break;
				}
			}
		}
		
		return res;
	}

	public static GcdResult gcd(long a, long b) throws MException {
		
		if(b==0)
			throw new MException("b cannot be 0");

		
		long m = Math.abs(a);
		long n = Math.abs(b);

		if (n > m && m!=0) {
			m = Math.abs(b);
			n = Math.abs(a);
		}

		GcdResult res = new GcdResult(m,n);

		long q;
		long r = m;
		while (r > 0) {
			q = (long) (m / n);
			r = m - n * q;
			res.qrl.add(new Long[] { q, r, m });
			m = n;
			n = r;
		}

		res.gcd = m;
		res.completed=true;
		
		return res;
	}


	public static DivisorsResult searchDivisors(Long n) {

		DivisorsResult res = new DivisorsResult();
		res.n=n;
		
		for (long d = 1; d <= n; d++) {
			if (n % d == 0) {
				res.divisors.add(d);
				res.sum+=d;
				res.count++;
				if(d<n) {
					res.aliquotSum+=d;
				}
			}
		}

		return res;
	}

	public static ArrayList<ArrayList<Long>> sumPerfectSquares1or2(long n) {
		ArrayList<ArrayList<Long>> al = new ArrayList<>();

		long sqrt=(long) Math.sqrt(n);
		for (long a = 1; a <= sqrt; a++) {
			for (long b = 0; b <= a; b++) {
				if (a*a+b*b == n) {
					ArrayList<Long> sol = new ArrayList<>();
					sol.add(n);
					sol.add(a);
					sol.add(b);
					al.add(sol);
					if(al.size()>2) {
						return al;
					}
				}
			}
		}

		return al;
	}

	public static ArrayList<ArrayList<Long>> sumPerfectSquares1or2or3(long n) {
		ArrayList<ArrayList<Long>> al = new ArrayList<>();

		long sqrt=(long) Math.sqrt(n);
		for (long a = 1; a <= sqrt; a++) {
			for (long b = 0; b <= a; b++) {
				for (long c = 0; c <= b; c++) {
					if (a*a+b*b+c*c == n) {
						ArrayList<Long> sol = new ArrayList<>();
						sol.add(n);
						sol.add(a);
						sol.add(b);
						sol.add(c);
						al.add(sol);
						if(al.size()>2) {
							return al;
						}
					}
				}
			}
		}

		return al;
	}

	public static ArrayList<ArrayList<Long>> sumPerfectSquares1or2or3or4(long n) {
		ArrayList<ArrayList<Long>> al = new ArrayList<>();

		long sqrt=(long) Math.sqrt(n);
		for (long a = 1; a <= sqrt; a++) {
			for (long b = 0; b <= a; b++) {
				for (long c = 0; c <= b; c++) {
					for (long d = 0; d <= c; d++) {
						if (a*a+b*b+c*c+d*d == n) {
							ArrayList<Long> sol = new ArrayList<>();
							sol.add(n);
							sol.add(a);
							sol.add(b);
							sol.add(c);
							sol.add(d);
							al.add(sol);
							if(al.size()>2) {
								return al;
							}
						}
					}
				}
			}
		}

		return al;
	}

	public static SumOfPositiveIntegersResult sumOfPositiveIntegers(long n, String kind) throws MException {
		
		SumOfPositiveIntegersResult res = new SumOfPositiveIntegersResult(n);
		long maxWeight=n;
		
		ArrayList<ArrayList<Long>> ways = new ArrayList<>();
		
		if("DISTINCT".equals(kind)) {
			maxWeight=1l;
			for(long i=n;i>0;i--) {
				res.values.add(i);
			}
		}else if("ODD".equals(kind)) {
			long start=n;
			if(n%2==0) {
				start++;
			}
			for(long i=start;i>0;i=i-2) {
				res.values.add(i);
			}
		}else if("POWER2".equals(kind)) {
			for(long i=n-1;i>=0;i=i-1) {
				long p=power(2,i);
				res.values.add(p);
			}
		}else if("NOTMUL3".equals(kind)) {
			for(long i=n;i>0;i--) {
				if(i%3!=0) {
				  res.values.add(i);
				}
			}
		}else if("MAX2".equals(kind)) {
			maxWeight=2l;
			for(long i=n;i>0;i--) {
				res.values.add(i);
			}
		}else {
			throw new MException("Unknown kind : "+kind);
		}
		
		//System.out.println("Values "+res.values);
		ways=sumOfPositiveIntegersRecursive(n,0l,res.values,null,maxWeight,ways);	

		res.ways=ways;
		res.solutions=ways.size();
		
		return res;
	}

	private static ArrayList<ArrayList<Long>> sumOfPositiveIntegersRecursive(long n, long partial, ArrayList<Long> values, ArrayList<Long> weights, long maxWeights, ArrayList<ArrayList<Long>> ways) {
		
		if(partial>n) {
			return ways;
		}

	    if(partial==n) {
		       //System.out.println(weights+" "+partial+" GOOD");
			   ways.add(weights);
			   return ways;
	    }

		if(weights==null) {
	    	ArrayList<Long> weights0 = new ArrayList<Long>();
	    	weights0.add(0l);
	        ways=sumOfPositiveIntegersRecursive(n, 0l, values, weights0, maxWeights, ways);
	        return ways;
	    }
		    
	    //System.out.println(weights+" "+partial+" WIP");

	    int index = weights.size()-1;
	    Long last = weights.get(index);	
	    if( last<maxWeights )	{
	    	ArrayList<Long> next=new ArrayList<Long>(weights);
		    Long lastnext = next.get(index);	
		    next.remove(index);
		    next.add(++lastnext);
		    long newpartial=partial+values.get(index);
		    ways=sumOfPositiveIntegersRecursive(n,newpartial,values,next,maxWeights,ways);
	    }

		if (weights.size()<values.size()) {
		        ArrayList<Long> newweight=new ArrayList<Long>(weights);
		        newweight.add(0l);
		        ways=sumOfPositiveIntegersRecursive(n,partial,values,newweight,maxWeights,ways);
		}
		
		return ways;    			
		
		
	}
	
	public static PowersResult powers(long a, long n) {
		PowersResult r = new PowersResult(a,n);
		long power=1;
		for(long i=0;i<n;i++) {
			power=(power*a)%n;
			r.powers.add(power);
			if(power==1l) {
				r.ones++;
			}
		}
		r.setCompleted(true);
		return r;
	}

	public static long power(long base, long exp) throws MException {
		return power(base, exp, Long.MAX_VALUE);
	}

	static long power(long base, long exp, long l) throws MException {

		if(exp==0 && base==0) {
			throw new MException("undefined 0^0");
		}

		if(exp==0) {
			return 1;
		}

		if(base==0) {
			return 0;
		}

		long r=1;
		for(long i=0;i<exp;i++) {
			if(r>l/base) {
				throw new MException("power too big... "+base+"^"+i+"="+r+" expected ^"+exp);
			}
			r*=base;
		}
        return r;
	}

	public static long factorial(long l) throws MException {
		long r =1l;
		for(long i=2;i<l+1l;i++) {
			if(r>Long.MAX_VALUE/i) {
				throw new MException("factorial too big");
			}
			r=r*i;
		}
		return r;
	}

	public static long generateFermatNumber(long n) throws MException {
		long r = power(2, n);
		r = power(2,r);
		r = r+1;
		return r;
	}

	public static ArrayList<EulerDivisorSumResult> listEulerDivisorSum(Long n) throws MException {
		ArrayList<EulerDivisorSumResult> list = new ArrayList<EulerDivisorSumResult>();
		DivisorsResult divisors = NumberTheory.searchDivisors(n);
		for(int i=1;i<=n;i++) {
			EulerDivisorSumResult row = new EulerDivisorSumResult(i);
			row.n=n;
			row.divisor = divisors.getDivisors().contains((long)i);
			if(row.divisor) {
				row.relativePrimesResult = searchRelativePrimes(i);
				row.nOverD=n/i;
				row.td = NumberTheory.searchTd(i,n);
				row.tdCount=row.td.size();
				row.relativePrimesResultNOverD=NumberTheory.searchRelativePrimes(row.nOverD);
			} 
			list.add(row);
		}
		return list;
	}

	private static ArrayList<Long> searchTd(long d, long n) throws MException {
		ArrayList<Long> r = new ArrayList<>();
		for(int i=1;i<=n;i++) {
			if(gcd((long)i, n).gcd==d) {
				r.add((long) i);
			}
		}
		return r;
	}
	
	public static ArrayList<EulerPropertiesResult> listEulerProperties(long max) throws MException{
		ArrayList<EulerPropertiesResult> r = new ArrayList<>();
		for(int i=1;i<=max;i++) {
			EulerPropertiesResult p = searchEulerProperties(i);
			r.add(p);
		}
		return r;
	}

	public static EulerPropertiesResult searchEulerProperties(long i) throws MException {
		EulerPropertiesResult p = new EulerPropertiesResult(i);
		p.relativePrimesResult = NumberTheory.searchRelativePrimes(i);
		p.divisorsResult = NumberTheory.searchDivisors((long) i);
		p.prime = ( p.divisorsResult.count == 2 );
		p.phiDividesNMinusOne = ( (i-1)%p.relativePrimesResult.count == 0 );
		return p;
	}


}
