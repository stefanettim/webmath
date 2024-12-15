package web.groups;

import java.util.HashSet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import groups.Engine;
import groups.Factory;
import groups.Group;

public class WebSubgroups {

	public static void Q4(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Group g = Factory.getQ4Group();
		request.setAttribute("group", g);
		HashSet<Group> hs = Engine.subgroups(g);
		request.setAttribute("subgroups", hs);

	}

}
