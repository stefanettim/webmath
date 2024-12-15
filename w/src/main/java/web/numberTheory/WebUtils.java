package web.numberTheory;

import jakarta.servlet.http.HttpServletRequest;

public class WebUtils {

	public static Long readLong(String parameter, Long defaultValue, Long maxValue, HttpServletRequest request) {
		Long l = readLong(parameter, request);
		if(l==null) {
			l=defaultValue;
		}
		if(l>maxValue) {
			l=maxValue;
		}
		return l;
	}
	
	public static Long readLong(String parameter, HttpServletRequest request) {
		String ps = request.getParameter(parameter);
		if (ps == null || "".equals(ps)) {
			return null;
		}

		Long l = Long.parseLong(ps);
		return l;
	}

	public static Integer readInteger(String parameter, HttpServletRequest request) {
		String ps = request.getParameter(parameter);
		if (ps == null || "".equals(ps)) {
			return null;
		}

		Integer l = Integer.parseInt(ps);
		return l;
	}

	public static Boolean readBoolean(String parameter, HttpServletRequest request) {
		String ps = request.getParameter(parameter);
		if (ps == null || "".equals(ps)|| "false".equals(ps)|| "f".equals(ps)|| "n".equals(ps)|| "0".equals(ps)) {
			return false;
		}
		
		return true;
	}

}