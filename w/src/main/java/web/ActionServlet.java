package web;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import utils.MProperties;

public abstract class ActionServlet extends DbServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doDbGet(HttpServletRequest request, HttpServletResponse response, MProperties properties, Connection connection) 
			throws Exception {
		
		StringBuffer sb = request.getRequestURL();
		
		StringTokenizer st = new StringTokenizer(sb.toString(),"/");
		
		String wpath="";
		String wmanager="";
		String last="";
		boolean subString=false;
		ArrayList<String> wpaths = new ArrayList<>();
		
		while(st.hasMoreElements())
		{
			String nt = st.nextToken();
									
			if(subString) {
			  wpath+=last+"/";
			  wpaths.add(nt);
			  last=nt;
			  wmanager=nt;
			}

			if("a".equals(nt)) {
				subString=true;
			}

		}
		
		if(wmanager==null||wmanager.isEmpty()||"a".equals(wmanager)) {
			wmanager="index";
		}
		
		/*
		Enumeration<String> e = request.getParameterNames();
		while(e.hasMoreElements()) {
			String s=e.nextElement();
			System.out.println(s+"="+request.getParameter(s));
		}
		*/
		
		String waction = request.getParameter("waction");
		String wfull = wpath+wmanager;

		request.setAttribute("wpath", wpath);
		request.setAttribute("wpaths", wpaths);
		request.setAttribute("wmanager", wmanager);
		request.setAttribute("waction", waction);
		request.setAttribute("wfull", wfull);
		
		setPage(request, wfull);
		doActionGet(request, response, properties, connection, wpath, wpaths, wmanager, waction, wfull);
		

	}

	public abstract void doActionGet(HttpServletRequest request, HttpServletResponse response, MProperties properties, Connection connection, String wpath, ArrayList<String> wpaths, String wmanager, String waction, String wfull) throws Exception;
}
