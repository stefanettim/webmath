package web.numberTheory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import numbers.MException;
import numbers.NumberTheory;
import numbers.results.GcdResult;

public class WebEuclideanAlgorithm {

	private static void solve(Long a, Long b, HttpServletRequest request) throws  MException {

		if(a==null||b==null) {
			return;
		}
		
		GcdResult gcdResult = NumberTheory.gcd(a, b);
		request.setAttribute("gcdResult", gcdResult);
	}
	
	public static void clear(HttpServletRequest request, HttpServletResponse response) throws MException {
		request.setAttribute("gcdResult", null);
	}

	public static void solve(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long a = WebUtils.readLong("a", request);
		Long b = WebUtils.readLong("b", request);
		solve(a,b,request);
	}

	public static void random(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long a = (long) (Math.random()*1000);
		Long b = (long) (Math.random()*1000);
		solve(a,b,request);
	}

	public static void exampleA527B341(HttpServletRequest request, HttpServletResponse response) throws MException {
		solve(527l, 341l, request);
	}
}
