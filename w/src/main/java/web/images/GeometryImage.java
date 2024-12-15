package web.images;

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
import neural.geometry.GeometrySample;

@WebServlet(name = "GeometryImage", urlPatterns = {"/i/geometryImage"})
public class GeometryImage extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = -753889109037835491L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
		
		String sString = request.getParameter("sample");
		int sInteger = Integer.parseInt(sString);

		GeometrySample[] ss = (GeometrySample[]) request.getSession().getAttribute("geometrySamples");

		GeometrySample sample = ss[sInteger];
		
		    ByteArrayOutputStream ios = new ByteArrayOutputStream();
		    BufferedImage bi = sample.getImage();
		    ImageIO.write(bi, "png", ios);   
		    InputStream is = new ByteArrayInputStream(ios.toByteArray());
		

            OutputStream os = response.getOutputStream();

                response.setContentType("image/png");

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = is.read(buffer)) != -1) 
                    os.write(buffer, 0, bytesRead);
    }
}