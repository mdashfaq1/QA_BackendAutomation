package com.qa.api.challenge.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import lombok.extern.log4j.Log4j;

@Log4j
public class ConfigUtil {

	private static ConfigUtil configUtil;
	private static Properties configProperties;
	private static FileInputStream  configFile;
	  
	private ConfigUtil() {}
	
	public static ConfigUtil getInstance() throws Exception{
		
		if(Objects.isNull(configUtil)){
			configUtil = new ConfigUtil();
			configProperties =  new Properties();
			try {
				configFile =  new FileInputStream("src/main/resources/config.properties");
				configProperties.load(configFile);
				configFile.close();
			} catch (IOException e) {
				log.info("Unable to load configuration: "+e.getMessage());
				throw new IOException(e.getMessage());
			}
		}
		return configUtil;
	}
	
	public String getConfigValue(String key) throws Exception{
		if(configProperties.containsKey(key))
			return configProperties.getProperty(key);
		else
			throw new Exception(key + " property is not available in config file");
	}
	
	public String getHomeDirectory(){
		return System.getProperty("user.dir");
	}
	
}
