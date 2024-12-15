package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MProperties extends Properties {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5635326831314765144L;

	public String context;
	
	private boolean loaded=false;
	
	public boolean is(String property) {
		String v = getProperty(property);
		boolean b = false;

		if ("true".equalsIgnoreCase(v))
			b = true;

		if ("yes".equalsIgnoreCase(v))
			b = true;

		return b;
	}
	
	public static MProperties load() throws IOException {
		return load("m");
	}
	

	public static MProperties load(String context) {
		MProperties mproperties = new MProperties();
		mproperties.context=context;
		for (String s : new String[] { "/etc/m/", "d:\\java\\m\\" }) {
			String p = s + context + ".properties";
			if (new File(p).exists()) {
				try {
				InputStream is = new FileInputStream(p);
				mproperties.load(is);
				is.close();
				mproperties.loaded=true;
				} catch (Exception e) {
				}
			} 

		}
		
		if(mproperties.loaded==false) {
			mproperties = noPropertyfile();
		}
		
		return mproperties;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public static MProperties noPropertyfile() {
		MProperties mproperties = new MProperties();
		mproperties.put("sessionInfo", "false");
		mproperties.put("dbConnection", "false");
		mproperties.put("bigPrimes", "x");
		mproperties.put("intPrimes", "x");
		mproperties.put("pageTitle", "M");
		mproperties.put("info", "noProperties");

		if(new File("/tmp").isDirectory()) {
			mproperties.put("primesFactory.dir", "/tmp");
			mproperties.put("tmp", "/tmp");
		}else if(new File("d:\\temp").isDirectory()) {
			mproperties.put("primesFactory.dir", "d:\\\\temp");
			mproperties.put("tmp", "d:\\\\temp");
		}

		return mproperties;
	}

	
}
