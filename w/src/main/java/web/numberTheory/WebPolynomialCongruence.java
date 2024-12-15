package web.numberTheory;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import numbers.Congruences;
import numbers.MException;
import numbers.results.PolynomialCongruenceResult;

public class WebPolynomialCongruence {
	
	public static PolynomialCongruenceResult read(HttpServletRequest request) throws MException {
	
		ArrayList<Long> al = new ArrayList<Long>();
		for(int i=0;i<10;i++) {
			Long ai = WebUtils.readLong("a"+i,request);
			if(ai==null) {
				break;
			}
			al.add(ai);
		}
		Long[] a = new Long[al.size()];
		a = al.toArray(a);
		
		Long m = WebUtils.readLong("m",request);
		
		if(a.length<2||a[0]==0||m==null) {
			return null;
		}
		
		
		PolynomialCongruenceResult polynomialCongruenceResult = new PolynomialCongruenceResult(a, m);
		
		return polynomialCongruenceResult;
		
	}

	public static void init(HttpServletRequest request, HttpServletResponse response) throws MException {
	}

	public static void solve(PolynomialCongruenceResult r, HttpServletRequest request) throws MException, ClassNotFoundException, InterruptedException, IOException {
		r=Congruences.solvePolynomialCongruence(r.getA(), r.getM(),WebPrimes.getCachedIntPrimes(request));
		request.setAttribute("polynomialCongruenceResult", r);
	}

	public static void solve(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, InterruptedException, IOException {
		PolynomialCongruenceResult r = read(request);
		if(r!=null) {
			solve(r, request);
		}
	}

	public static void clear(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, InterruptedException, IOException {
		request.setAttribute("polynomialCongruenceResult", null);
	}

	public static void example0(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, InterruptedException, IOException {
		solve(new PolynomialCongruenceResult(new Long[]{1l,1l}, 3l), request);
	}

	public static void example1(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, InterruptedException, IOException {
		solve(new PolynomialCongruenceResult(new Long[]{1l,0l,2l}, 3l), request);
	}

	public static void example2(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, InterruptedException, IOException {
		solve(new PolynomialCongruenceResult(new Long[]{1l,1l,1l,1l,1l,1l,1l,1l,1l,1l,21l}, 31l), request);
	}

	public static void exampleGT1a(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, InterruptedException, IOException {
		solve(new PolynomialCongruenceResult(new Long[]{1l,1l,1l}, 11l), request);
	}

	public static void exampleGT1b(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, InterruptedException, IOException {
		solve(new PolynomialCongruenceResult(new Long[] {1l,0l,1l,1l}, 13), request);
	}

	public static void exampleGT1c(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, InterruptedException, IOException {
		solve(new PolynomialCongruenceResult(new Long[] {1l,1l,0l,0l,2l}, 7), request);
	}

	public static void euler10(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, InterruptedException, IOException {
		solve(new PolynomialCongruenceResult(new Long[] {1l,0l,0l,0l,9l}, 10), request);
	}

	public static void wilson5(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, InterruptedException, IOException {
		solve(new PolynomialCongruenceResult(new Long[] {1l,0l,0l,0l,4l}, 5), request);
	}
}
