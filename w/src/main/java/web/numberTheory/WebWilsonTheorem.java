package web.numberTheory;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import numbers.Congruences;
import numbers.MException;
import numbers.results.WilsonTheoremResult;
import primes.IntPrimes;

public class WebWilsonTheorem {

	public static void init(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		WebPrimes.preparePrimes(request, 1000);
	}
	
	public static void solve(Integer p, HttpServletRequest request) throws MException, InterruptedException, ClassNotFoundException, IOException {
		if(p==null) {
			return;
		}

		if(p>1000) {
			p=1000;
		}
		
		IntPrimes ip = WebPrimes.getCachedIntPrimes(request);
		WilsonTheoremResult wilsonTheoremResult = Congruences.solveWilsonTheorem(p,ip);
		request.setAttribute("wilsonTheoremResult", wilsonTheoremResult);
	}
	
	public static void solve(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		Integer p = WebUtils.readInteger("p", request);
		solve(p, request);
	}

	public static void clear(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		request.setAttribute("wilsonTheoremResult", null);
	}

	public static void solveP11(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		solve(11, request);
	}

	public static void solveP13(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		solve(13, request);
	}

	public static void examples(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		
		IntPrimes ip = WebPrimes.getCachedIntPrimes(request);

		ArrayList<WilsonTheoremResult> results = new ArrayList<>();
		
		for(int l=1;l<=20;l++) {
			WilsonTheoremResult r = Congruences.solveWilsonTheorem(l, ip);
			results.add(r);
		}
		request.setAttribute("results", results);
	}
}
