package web.neuralNetworks;

import java.io.FileNotFoundException;
import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import neural.geometry.GeometryMachine;
import neural.geometry.GeometrySample;

public class WebGeometry {
	
	private final static int MAX_SIDES=4;
	
	public static synchronized GeometryMachine loadMachine(HttpServletRequest request) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		HttpSession s = request.getSession();
		GeometryMachine m = (GeometryMachine) s.getAttribute("geometryMachine");
		
		if(m==null)
		{
			m = (GeometryMachine) new GeometryMachine(MAX_SIDES).loadClassResource("Geometry");
			
			s.setAttribute("geometryMachine", m);
		}
		
		return m;
	}
	
	
	public static synchronized void guess(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException
	{
		
		GeometryMachine m = loadMachine(request);
		
		int SAMPLES=100;
		int correct=0;
		int wrong=0;
		GeometrySample[] ss = new GeometrySample[SAMPLES]; 
		for(int i=0;i<SAMPLES;i++) {
			GeometrySample s = GeometrySample.generateSample(MAX_SIDES);
			int g = m.feedFarward(s.getImage());
			s.setGuessed(g);
			s.setId(i);
			ss[i]=s;
			if(s.isCorrect()) {
				correct++;
			} else {
				wrong++;
			}
		}
		

		request.getSession().setAttribute("geometrySamples", ss);
		request.getSession().setAttribute("geometrySamplesTot", SAMPLES);
		request.getSession().setAttribute("geometrySamplesCorrect", correct);
		request.getSession().setAttribute("geometrySamplesWrong", wrong);
	}
	
	
}
