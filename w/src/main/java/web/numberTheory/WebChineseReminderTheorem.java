package web.numberTheory;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import numbers.Congruences;
import numbers.MException;
import numbers.results.ChineseReminderTheoremResult;

public class WebChineseReminderTheorem {
	
	public static void init(HttpServletRequest request, HttpServletResponse response) throws MException {
	}
	
	private static Long[] readArray(String suffix, HttpServletRequest request) {
		ArrayList<Long> al = new ArrayList<Long>();
		for(int i=1;i<6;i++) {
			Long ai = WebUtils.readLong(suffix+i,request);
			if(ai==null) {
				break;
			}
			al.add(ai);
		}
		Long[] a = new Long[al.size()];
		a = al.toArray(a);
		return a;
	}

	public static ChineseReminderTheoremResult read(HttpServletRequest request) throws MException {
		
		Long[] a = readArray("a", request);
		Long[] b = readArray("b", request);
		Long[] m = readArray("m", request);
		
		if(a.length==0) {
			return null;
		}
		
		ChineseReminderTheoremResult chineseReminderTheoremResult = new ChineseReminderTheoremResult(a, b, m);
		
		return chineseReminderTheoremResult;
		
	}

	public static void clear(HttpServletRequest request, HttpServletResponse response) throws MException, ClassNotFoundException, InterruptedException, IOException {
		request.setAttribute("chineseReminderTheoremResult", null);
	}

	public static void solve(ChineseReminderTheoremResult r, HttpServletRequest request) throws MException {
		r=Congruences.solveChineseReminderTheorem(r.a,r.b,r.m);
		request.setAttribute("chineseReminderTheoremResult", r);
	}

	public static void solve(HttpServletRequest request, HttpServletResponse response) throws MException {
		ChineseReminderTheoremResult r = read(request);
		if(r==null) {
			return;
		}
		solve(r, request);
	}

	public static void example1(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long[] a = new Long[]{1l,1l,1l};
		Long[] b = new Long[]{2l,3l,2l};
		Long[] m = new Long[]{3l,5l,7l};
		ChineseReminderTheoremResult r = new ChineseReminderTheoremResult(a, b, m);
		solve(r, request);
	}

	public static void example2(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long[] a = new Long[]{3l,3l,3l};
		Long[] b = new Long[]{11l,11l,11l};
		Long[] m = new Long[]{25l,7l,13l};
		ChineseReminderTheoremResult r = new ChineseReminderTheoremResult(a, b, m);
		solve(r, request);
	}

	public static void bad1(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long[] a = new Long[]{6l,1l,1l};
		Long[] b = new Long[]{2l,3l,2l};
		Long[] m = new Long[]{3l,5l,7l};
		ChineseReminderTheoremResult r = new ChineseReminderTheoremResult(a, b, m);
		solve(r, request);
	}

	public static void bad2(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long[] a = new Long[]{2l,3l,4l,5l,7l};
		Long[] b = new Long[]{1l,2l,3l,4l,5l};
		Long[] m = new Long[]{11l,13l,33l,14l,15l};
		ChineseReminderTheoremResult r = new ChineseReminderTheoremResult(a, b, m);
		solve(r, request);
	}

	public static void example5(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long[] a = new Long[]{1l,1l,1l,1l,1l};
		Long[] b = new Long[]{1l,2l,3l,4l,5l};
		Long[] m = new Long[]{2l,3l,5l,7l,11l};
		ChineseReminderTheoremResult r = new ChineseReminderTheoremResult(a, b, m);
		solve(r, request);
	}

	public static void example6(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long[] a = new Long[]{1l,1l,1l};
		Long[] b = new Long[]{1l,3l,5l};
		Long[] m = new Long[]{5l,7l,9l};
		ChineseReminderTheoremResult r = new ChineseReminderTheoremResult(a, b, m);
		solve(r, request);
	}

	public static void examplea(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long[] a = new Long[]{1l,1l,1l};
		Long[] b = new Long[]{1l,2l,3l};
		Long[] m = new Long[]{2l,3l,5l};
		ChineseReminderTheoremResult r = new ChineseReminderTheoremResult(a, b, m);
		solve(r, request);
	}

	public static void exampleb(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long[] a = new Long[]{1l,1l,1l};
		Long[] b = new Long[]{1l,3l,5l};
		Long[] m = new Long[]{3l,5l,7l};
		ChineseReminderTheoremResult r = new ChineseReminderTheoremResult(a, b, m);
		solve(r, request);
	}

	public static void examplec(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long[] a = new Long[]{3l,4l,5l};
		Long[] b = new Long[]{1l,6l,11l};
		Long[] m = new Long[]{5l,14l,3l};
		ChineseReminderTheoremResult r = new ChineseReminderTheoremResult(a, b, m);
		solve(r, request);
	}
	public static void exampled(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long[] a = new Long[]{4l,3l,2l};
		Long[] b = new Long[]{2l,5l,4l};
		Long[] m = new Long[]{6l,7l,11l};
		ChineseReminderTheoremResult r = new ChineseReminderTheoremResult(a, b, m);
		solve(r, request);
	}
	public static void examplee(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long[] a = new Long[]{1l,1l,1l,1l};
		Long[] b = new Long[]{1l,2l,3l,4l};
		Long[] m = new Long[]{3l,4l,7l,11l};
		ChineseReminderTheoremResult r = new ChineseReminderTheoremResult(a, b, m);
		solve(r, request);
	}
	public static void examplef(HttpServletRequest request, HttpServletResponse response) throws MException {
		Long[] a = new Long[]{2l,3l,4l,5l};
		Long[] b = new Long[]{1l,9l,1l,9l};
		Long[] m = new Long[]{5l,6l,7l,11l};
		ChineseReminderTheoremResult r = new ChineseReminderTheoremResult(a, b, m);
		solve(r, request);
	}
}
