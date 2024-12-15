package web;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import utils.MProperties;

public abstract class DbServlet extends WServlet {
	
	private static final long serialVersionUID = 1L;

	protected final String sessql="select to_char(sysdate,'yyyy-mm-dd_hh24:mi:ss')" + 
			"||'_'||SYS_CONTEXT ('USERENV', 'DB_NAME')" + 
			"||'_'||SYS_CONTEXT ('USERENV', 'CURRENT_USER')" + 
			"||'@'||SYS_CONTEXT ('USERENV', 'INSTANCE_NAME')" + 
			"||'_'||SYS_CONTEXT ('USERENV', 'SID')" + 
			"||','||s SES" + 
			"  from (select serial# s FROM v$session where sid = SYS_CONTEXT ('USERENV', 'SID')) s" 
			;
	
	
	@Override
	public void doWebGet(HttpServletRequest request, HttpServletResponse response, MProperties properties) 
			throws Exception {
		
		Context ctx = null;
		Connection con = null;
		try{

			if(properties.is("dbConnection"))
			{
				ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/wdb");
				con = ds.getConnection();
			}
			
			if(properties.is("sessionInfo"))
			{
			Statement stmt = null;
			ResultSet rs = null;
			stmt = con.createStatement();
			rs = stmt.executeQuery(sessql);
			rs.next();
			String sessinfo = rs.getString("SES");
			System.out.println("connection "+sessinfo);
			rs.close();
			stmt.close();
			request.setAttribute("sessionInfo", sessinfo);
			}
			else
				request.setAttribute("sessionInfo", "");
				
			
			doDbGet(request, response, properties, con);

		}
		finally{
			try {
				if(con!=null)
					con.close();
				if(ctx!=null)
				    ctx.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Exception in closing DB resources");
			} 
		
		}
		
	}
	

	public abstract void doDbGet(HttpServletRequest request, HttpServletResponse response, MProperties properties, Connection con) throws Exception;
}
