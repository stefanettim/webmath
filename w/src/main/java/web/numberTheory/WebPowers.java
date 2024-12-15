package web.numberTheory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import numbers.Congruences;
import numbers.MException;
import numbers.results.PowerResult;

public class WebPowers {

	private static void solve(Long a, Long n, Long b, Long m, Boolean e, HttpServletRequest request) throws  MException {

		if(a==null||n==null||b==null||m==null||e==null) {
			return;
		}
		
		PowerResult powerResult;
		
		if(e) {
			 powerResult = Congruences.solvePowerForExponent(a, n, b, m);
		} else {
			powerResult = Congruences.solvePowerForBase(a, n, b, m);
		}
		request.setAttribute("powerResult", powerResult);
	}
	
	public static void clear(HttpServletRequest request, HttpServletResponse response) throws MException {
		request.setAttribute("powerResult", null);
	}

	public static void solve(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long a = WebUtils.readLong("a", request);
		Long n = WebUtils.readLong("n", request);
		Long b = WebUtils.readLong("b", request);
		Long m = WebUtils.readLong("m", request);
		Boolean e = WebUtils.readBoolean("e", request);
		solve(a,n,b,m,e,request);
	}

	public static void e1(HttpServletRequest request, HttpServletResponse response) throws MException {
		solve(3l,14l,2l,23l,false,request);
	}

	public static void e2(HttpServletRequest request, HttpServletResponse response) throws MException {
		solve(1l,3l,5l,17l,false,request);
	}

	public static void e3(HttpServletRequest request, HttpServletResponse response) throws MException {
		solve(1l,13l,5l,23l,true,request);
	}

	public static void e4(HttpServletRequest request, HttpServletResponse response) throws MException {
		solve(1l,5l,17l,19l,true,request);
	}
}
