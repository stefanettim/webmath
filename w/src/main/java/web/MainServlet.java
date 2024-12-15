package web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import utils.MProperties;

public class MainServlet extends ActionServlet {

	private static final long serialVersionUID = 8868733958611521594L;

	@Override
	public void doActionGet(HttpServletRequest request, HttpServletResponse response, MProperties properties,
			Connection con, String wpath, ArrayList<String> wpaths, String wmanager, String waction, String wfull) throws Exception {

			
		String actionManagerCap = new String(""+wmanager.toCharArray()[0]).toUpperCase() + wmanager.substring(1);
		String clazz = "web"+wpath.replace('/', '.')+"Web"+actionManagerCap;
		//System.out.println("Looking for "+clazz);
		Class<?> webActionManager;
		try{
			webActionManager = Class.forName(clazz);
			//System.out.println("Found "+clazz);
		}catch (ClassNotFoundException e) {
			webActionManager=null;
		}

		if(webActionManager!=null) {

			try {
				Method methodInit = webActionManager.getMethod("init",HttpServletRequest.class,HttpServletResponse.class);
				methodInit.invoke(null, request, response);
				request.setAttribute("winit", "init");
			} catch (Exception e) {
			}
			
			if(waction!=null) {
				Method method = webActionManager.getMethod(waction,HttpServletRequest.class,HttpServletResponse.class);
				try {	
					method.invoke(null, request, response);
					return;
				}catch (InvocationTargetException ie) {
						throw new WException(ie.getTargetException());
				}
			}
		}
		
		if ("/fakeError".equals(wfull)) {
			int x = 1 / 0;
			System.out.println(x);
			return;
		} 

		//request.setAttribute("actionError", "Unknown Action");

	}
}
