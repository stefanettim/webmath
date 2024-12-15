package web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import utils.MProperties;

public abstract class WServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, ServletException {
		doGet(request, response);
	}

	public static MProperties readMProperties(HttpServletRequest request) throws IOException {
		
		String reload = request.getParameter("reloadProperties");
		if("true".equals(reload)) {
			request.getServletContext().setAttribute("mProperties", null);
		}
		
		MProperties properties = (MProperties) request.getServletContext().getAttribute("mProperties");
		if(properties==null) {
			String wcl = request.getContextPath().substring(1);
			properties = MProperties.load(wcl);
			request.getServletContext().setAttribute("mProperties", properties);
		}
		return properties;
	}


	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, ServletException {
		
		Date startTime = new Date();
		request.setAttribute("startTime", startTime);
		
		
		MProperties properties = readMProperties(request);
		if(!properties.isLoaded())
		{
			properties = MProperties.noPropertyfile();
		}

		try {
				doWebGet(request, response, properties);
		}
		catch(Exception e)
		{
			setErrorMessage(request, "Action Error", e);
			setPage(request, "error");
		}

		Date endTime = new Date();
		request.setAttribute("endTime", endTime);
		
		long milli = endTime.getTime()-startTime.getTime();
		request.setAttribute("elapsedTime", milli+" ms");
				
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/main.jsp").include(request, response);

	}
	
	protected void setErrorMessage(HttpServletRequest request, String errorMessage, Exception e)
	{
		request.setAttribute("errorMessage", errorMessage);
		request.setAttribute("exception", e);
		
	}
	
	
	protected void setPage(HttpServletRequest request, String page)
	{
		request.setAttribute("page", page);
	}

	
	public abstract void doWebGet(HttpServletRequest request, HttpServletResponse response, MProperties properties) throws Exception;
}
