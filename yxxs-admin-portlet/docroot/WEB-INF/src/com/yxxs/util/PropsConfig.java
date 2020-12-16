package com.yxxs.util;

import java.io.IOException;
import java.util.Properties;

import com.yxxs.common.handler.CommonErrorHandler;
import com.yxxs.common.util.BasePropsConfig;

public class PropsConfig extends BasePropsConfig {

	private static Properties confs = new Properties();
	private static Properties props = new Properties();

	static{
		try {
			
			confs.load(PropsConfig.class.getClassLoader().getResourceAsStream("properties/conf.properties"));
			
			String path = "properties/config"+CONFIGMODE+".properties";
			props.load(PropsConfig.class.getClassLoader().getResourceAsStream(path));
			System.out.println("load props ["+path+"] ============" + props.toString());
		} catch (IOException e) {
			//e.printStackTrace();
			CommonErrorHandler.printException(e);
		}
	}
	public static Long TIME_TS = STARTUP_TS;
	public static final String SERVER_HOST = getProp("config.server.host");

	public static final String STATIC_SERVER_HOST = getProp("config.static.server.host");
	
	private static String getProp(String key){
		String k = props.getProperty(key);
		return props.contains(key) && !"".equals(k)?k:getPortalExtProp(key);
	}

}
