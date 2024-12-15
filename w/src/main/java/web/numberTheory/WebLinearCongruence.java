package web.numberTheory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import numbers.Congruences;
import numbers.MException;
import numbers.results.LinearCongruenceResult;

public class WebLinearCongruence {
	
	public static void solveLinearCongruence(Long a, Long b, Long c, HttpServletRequest request, HttpServletResponse response) throws MException {
		
		if(a==null||b==null||c==null) {
			return;
		}

		LinearCongruenceResult linearCongruenceResult = Congruences.solveLinearCongruence(a, b, c);
		request.setAttribute("linearCongruenceResult", linearCongruenceResult);
	}

	public static void solve(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long a = WebUtils.readLong("a",request);
		Long b = WebUtils.readLong("b",request);
		Long c = WebUtils.readLong("c",request);
		solveLinearCongruence(a, b, c, request, response);
	}

	public static void clear(HttpServletRequest request, HttpServletResponse response) throws MException {
		request.setAttribute("linearCongruenceResult", null);
	}

	public static void solveA5B3C8(HttpServletRequest request, HttpServletResponse response) throws MException {
		solveLinearCongruence(5l, 3l, 8l, request, response);
	}

	public static void solveA21B11C3(HttpServletRequest request, HttpServletResponse response) throws MException {
		solveLinearCongruence(21l, 11l, 3l, request, response);
	}

	public static void solveA15B9C12(HttpServletRequest request, HttpServletResponse response) throws MException {
		solveLinearCongruence(15l, 9l, 12l, request, response);
	}

	public static void solveA45B12C2(HttpServletRequest request, HttpServletResponse response) throws MException {
		solveLinearCongruence(4l, 12l, 2l, request, response);
	}

}
