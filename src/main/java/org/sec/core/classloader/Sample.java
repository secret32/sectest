package org.sec.core.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Sample {
	
	public String getTime() {
		return "hello";
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		MyLoader cd = new MyLoader();
		MyLoader cl = new MyLoader(cd);
		Class<?> clazz = cl.loadClass("org.sec.classloader.Sample");
		if (clazz == null) 
			System.out.println("load error");
		else {
			try {
				Sample s = (Sample) clazz.newInstance();
				System.out.println(s instanceof Sample);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
			
	}
}

class MyLoader extends ClassLoader {
	private static final String DIR = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes" + File.separator;
	
	public MyLoader() {
		super();
	}
	
	public MyLoader(ClassLoader parent) {
		super(parent);
	}
	
	@Override
	public Class<?> findClass(String name) {
		try (InputStream is = new FileInputStream(DIR + name.replaceAll("\\.", "/") + ".class");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = is.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, len);
			}
			buf = baos.toByteArray();
			this.defineClass(name, buf, 0, buf.length); 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
}
