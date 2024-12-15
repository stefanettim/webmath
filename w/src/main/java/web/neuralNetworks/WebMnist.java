package web.neuralNetworks;

import java.io.FileNotFoundException;
import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import neural.reader.ImageChain;
import neural.reader.MnistImageUtils;
import neural.reader.ReadMachine;
import neural.reader.ReadResult;

public class WebMnist {
	
	public static synchronized ReadMachine loadMAchine(HttpServletRequest request) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		HttpSession s = request.getSession();
		ReadMachine rm = (ReadMachine) s.getAttribute("readMachine");
		
		if(rm==null)
		{
			rm = (ReadMachine) new ReadMachine().loadClassResource("Read");
			
			s.setAttribute("readMachine", rm);
		}
		
		return rm;
	}
	
	
	public static boolean read(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException
	{
		String data = request.getParameter("hidden_data");
		
		if(data==null)
			return false;
		
		request.setAttribute("data", data);
		
		ReadMachine rm = loadMAchine(request);
		
		ImageChain ic = MnistImageUtils.mnistTransform(data);
		
		if(ic.smallSize<50) {
			request.setAttribute("message", "too small");
			return false;
		}
		
		
		ReadResult rr = rm.process(ic.mnist);
		
		rr.load(ic);

		request.setAttribute("readResult", rr);
		request.setAttribute("page", "/neuralNetworks/mnist_output");
		return true;
	}
	
	
}
