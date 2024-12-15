package numbers;

import java.io.IOException;
import java.util.ArrayList;

import numbers.results.ChineseReminderTheoremResult;
import numbers.results.EulerTheoremResult;
import numbers.results.GcdResult;
import numbers.results.LinearCongruenceResult;
import numbers.results.Pair;
import numbers.results.PolynomialCongruenceResult;
import numbers.results.PowerResult;
import numbers.results.WilsonTheoremResult;
import primes.IntPrimes;

public class Congruences {

	// Linear Congruence
	// a*n = b (mod c)
	public static LinearCongruenceResult solveLinearCongruence(long a, long b, long c ) throws MException {
		
		LinearCongruenceResult res = new LinearCongruenceResult(a, b, c);
		
		res.d = NumberTheory.gcd(a, c).gcd;
		
		if(b%res.d!=0) {
			res.solvable=false;
			res.completed=true;
			return res;
		} else {
			res.solvable=true;
		}
		
		long ar=a/res.d;
		long br=b/res.d;
		long cr=c/res.d;
		
		long nr=0;
		for(long q=0;q<cr;q++) {
			long r=(ar*q-br)%cr;
			if(r==0) {
				nr=q;
				break;
			}
		}
		
		for(int i=0;i<res.d;i++) {
			
			long n=nr+i*cr;
			long r=(a*n-b)%c;
			
			//sanity
			if(r!=0) {
				throw new MException("error solving linear congruence, wrong result");
			}
			
			res.incongruentSolutions.add(n);
		}
		
		if(res.incongruentSolutions.size()!=res.d) {
			throw new MException("error solving linear congruence, wrong number of solutions");
		}
		
		res.completed=true;
		return res;
		
	}
	
	public static WilsonTheoremResult solveWilsonTheorem(int p, IntPrimes ip) throws MException, InterruptedException, ClassNotFoundException, IOException {

		WilsonTheoremResult res = new WilsonTheoremResult((long) p);
		
		res.prime = ip.isPrime(p);
		
		if(!res.prime) {
			res.completed=false;
			return res;
		}

		if(p<=20) {
			res.f = NumberTheory.factorial(p-1);
			res.q=res.f / p;
			res.r = res.f - res.q * res.p;
			if(res.r!=p-1) {
				throw new MException("wrong algorithm");
			}
		} else {
			res.f=0;
			res.q=0;
			res.r=0;
		}
		
		ArrayList<Long> reducedResidueSystem = new ArrayList<>();
		for(int i=2; i<p;i++) {
			reducedResidueSystem.add((long) i);
		}
		
		while(reducedResidueSystem.size()>0) {
			long i =	reducedResidueSystem.remove(0);
			for(long j:reducedResidueSystem) {
				if(congruent(i*j,1,p)) {
					Pair pair = new Pair(i,j);
					res.pairing.add(pair);
				}
			}
		}
		
		res.inverseOfOne=p-1;

		res.completed=true;
		return res;
	}

	private static boolean congruent(long a, long c, long b) throws MException {
		return a%b==c%b;
	}
	
	public static EulerTheoremResult solveEulerTheorem(int a, int m, IntPrimes ip) throws MException, InterruptedException, ClassNotFoundException, IOException {
		EulerTheoremResult EulerTheoremResult = new EulerTheoremResult(a, m);
		
		GcdResult gcdResult = NumberTheory.gcd(a,m);
		EulerTheoremResult.gcd = gcdResult.gcd;
		
		EulerTheoremResult.primeModule = ip.isPrime(m);
		
		if(EulerTheoremResult.gcd!=1) {
			EulerTheoremResult.solvable=false;
			EulerTheoremResult.completed=true;
			return EulerTheoremResult;
		}

		EulerTheoremResult.solvable=true;
		EulerTheoremResult.relativePrimesResult = NumberTheory.searchRelativePrimes(m);
		
		for(long residue : EulerTheoremResult.relativePrimesResult.reducedResidueSystem) {
			long alt = residue * a;
			EulerTheoremResult.alternatedResidueSystem.add(alt);
			long r = alt % m;
			EulerTheoremResult.pairing.add(r);
		}
		
		
		EulerTheoremResult.completed=true;
		return EulerTheoremResult;
	}

	public static ChineseReminderTheoremResult solveChineseReminderTheorem(Long[] a, Long[] b, Long[] m) throws MException {
		
		ChineseReminderTheoremResult r = new ChineseReminderTheoremResult(a,b,m);

		r.completed=true;

		for(int k=0;k<r.l;k++) {
			if(m[k]<=0) {
				r.correct=false;
				r.incorrectMessage="m must be positive a integer, m="+m[k];
				return r;
			}
		}

		for(int k=0;k<r.l;k++) {
			long gcd = NumberTheory.gcd(a[k], m[k]).gcd;
			if(gcd!=1) {
				r.correct=false;
				r.incorrectMessage="gcd("+a[k]+","+m[k]+")="+gcd+", must be 1";
				return r;
			}
		}

		for(int k=0;k<r.l;k++) {
			for(int j=0;j<r.l;j++) {
				if(k!=j) {
					long gcd = NumberTheory.gcd(m[k], m[j]).gcd;
					if(gcd!=1) {
						r.correct=false;
						r.incorrectMessage="gcd("+m[k]+","+m[j]+")="+gcd+", must be 1";
						return r;
					}
				}
			}
		}

		for(int k=0;k<r.l;k++) {
			long c = solveLinearCongruence(a[k], b[k], m[k]).incongruentSolutions.get(0);
			r.c[k]=c;
		}
		
		r.product=1;
		for(int k=0;k<r.l;k++) {
			r.product=r.product*r.m[k];
		}

		for(int k=0;k<r.l;k++) {
			r.n[k]=r.product/m[k];
		}
		
		for(int k=0;k<r.l;k++) {
			r.i[k]=solveLinearCongruence(r.n[k], 1, r.m[k]).incongruentSolutions.get(0);
		}

		r.x0=0;
		for(int k=0;k<r.l;k++) {
			r.x0+=r.c[k]*r.n[k]*r.i[k];
		}
		
		r.y=r.x0%r.product;
		
		return r;
	}
	
	public static PolynomialCongruenceResult solvePolynomialCongruence(Long[] a, long m, IntPrimes ip ) throws MException {
		
		PolynomialCongruenceResult r = new PolynomialCongruenceResult(a, m);
		
		if(r.degree<1) {
			throw new MException("degree too small");
		}
		
		r.prime=ip.isPrime(m);
			
		r.mdividesa0=false;
		if(a[0]%m==0) {
			r.mdividesa0=true;
		}
		
		r.lagrange=false;
		if(r.prime && !r.mdividesa0) {
			r.lagrange=true;
		}
		
		for(long x=0;x<m;x++) {
			long f=0;
			for(int n=0;n<r.degree+1;n++) {
				long exp=r.degree-n;
				long xexp=1;
				if(exp>0) {
					xexp=NumberTheory.power(x, exp);
				}
				f+=a[n]*xexp;
			}
			if(f%m==0) {
				r.incongruentSolutions.add(x);
			}
		}
		
		r.completed=true;
		return r;
	}


	// n^p (mod m)
	public static long modPower(long n, long p, long m) {
		
		long r=1;
		
		long i=0;
		while(i<p) {
			r=(r*n)%m;
			i++;
		}

		return r;
	}

	
	// a x^n = b (mod m)
	public static PowerResult solvePowerForBase(long a, long n, long b, long m){
		
		PowerResult r = new PowerResult(a, n, b, m, false);
		
		long x=1;
		while(x<m) {
			
			long p=modPower(x, n, m);
			
			if((a*p-b)%m==0) {
				r.getSolutions().add(x);
			}
			
			x++;
		} 
		
		r.setCompleted(true);
		return r;
	}

	// a n^x = b (mod m)
	public static PowerResult solvePowerForExponent(long a, long n, long b, long m){
		
		PowerResult r = new PowerResult(a, n, b, m, true);
		
		int x=1;
		while(x<m) {
			
			long p=modPower(n, x, m);
			
			if((a*p-b)%m==0) {
				r.getSolutions().add((long) x);
			}
			
			x++;
		} 
		
		r.setCompleted(true);
		return r;
	}
}
