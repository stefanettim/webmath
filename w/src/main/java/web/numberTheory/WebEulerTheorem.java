package web.numberTheory;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import numbers.Congruences;
import numbers.MException;
import numbers.results.EulerTheoremResult;
import primes.IntPrimes;

public class WebEulerTheorem {

	public static void solveEulerTheorem(Integer a, Integer m, HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		if(a>1000) {
			a=1000;
		}
		if(m>1000) {
			m=1000;
		}
		IntPrimes ip = WebPrimes.getCachedIntPrimes(request);
		EulerTheoremResult eulerTheoremResult = Congruences.solveEulerTheorem(a, m, ip);
		request.setAttribute("eulerTheoremResult", eulerTheoremResult);
	}

	public static void clear(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		request.setAttribute("eulerTheoremResult", null);
	}

	public static void solve(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {

		Integer a = WebUtils.readInteger("a", request);
		Integer m = WebUtils.readInteger("m", request);

		if(a==null||m==null){
			return;
		}		
		
		solveEulerTheorem(a, m, request, response);
	}

	public static void solveA3M13(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		solveEulerTheorem(3, 13, request, response);
	}

	public static void solveA7M10(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		solveEulerTheorem(7, 10, request, response);
	}
	
	public static void solveA21M7(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		solveEulerTheorem(21, 7, request, response);
	}
	
	public static void solveA12M31(HttpServletRequest request, HttpServletResponse response) throws MException, InterruptedException, ClassNotFoundException, IOException {
		solveEulerTheorem(12, 31, request, response);
	}
}
