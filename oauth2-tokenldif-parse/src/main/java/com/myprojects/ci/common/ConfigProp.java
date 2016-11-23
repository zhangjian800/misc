package com.myprojects.ci.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class ConfigProp {
	public static final String CONFIGFILE = "/cfg.properties";

	private static Properties prop = new Properties();
	static {
		try {
			String absoluteFilePath = System.getProperty("ci.parse.confFile");
			File confFile = null;
			if(StringUtils.isNotEmpty(absoluteFilePath)) {
				try {
					confFile = new File(absoluteFilePath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (confFile != null && confFile.exists() && confFile.canRead()) {
				System.out.println("Absolute file path: file:"+absoluteFilePath);
				// prop.clear();
				System.out.println("Read from absolute file"+ absoluteFilePath);
				prop.load(new FileInputStream(confFile));
			} else {
				System.out.println("Read from relative path " + CONFIGFILE);
				InputStream stream = ConfigProp.class
						.getResourceAsStream(CONFIGFILE);
				prop.load(stream);
				stream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String propertyName) {
		return prop.getProperty(propertyName);
	}
}
