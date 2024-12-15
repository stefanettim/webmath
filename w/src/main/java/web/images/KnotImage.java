package web.images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import math.NodoWeb;

@WebServlet(name = "KnotImage", urlPatterns = {"/i/knotImage"})
public class KnotImage extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6718268808227270195L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
		
		String rpS = request.getParameter("rp");
		int rp= Integer.parseInt(rpS);

		int s=500;
		
		   NodoWeb d = new NodoWeb(rp);
//		   d.setSize(s,s);
		   
		    BufferedImage bi = new BufferedImage(s, s, BufferedImage.TYPE_INT_RGB);  
		    Graphics2D g = bi.createGraphics();
		    g.setPaint(Color.WHITE);
		    g.fillRect(0, 0, s, s);
		    d.disegna_nodo(g);
		    //d.paint(g);  
		    g.dispose();  

		    ByteArrayOutputStream ios = new ByteArrayOutputStream();
		    ImageIO.write(bi, "png", ios);                          // Passing: ​(RenderedImage im, String formatName, OutputStream output)
		    InputStream is = new ByteArrayInputStream(ios.toByteArray());
		

            OutputStream os = response.getOutputStream();

                response.setContentType("image/png");

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = is.read(buffer)) != -1) 
                    os.write(buffer, 0, bytesRead);
    }
}