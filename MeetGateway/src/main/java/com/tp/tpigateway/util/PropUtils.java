package com.tp.tpigateway.util;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * properties 共用工具類
 */
public class PropUtils {

	private static final Logger log = LoggerFactory.getLogger(PropUtils.class);
	
	// 相關properties設定來源
	private static final String TPIGATEWAY_PROPERTIES = "tpigateway.properties";
	private static final String APPLICATION_PROPERTIES = "application.properties";
		
	private static Map<String, String> propMap = new ConcurrentHashMap<String, String>();
	
	/**
     * 取得一般參數
     * @param key
     * @return
     */
    public static String getProperty(String key) {
    	
    	if (propMap.isEmpty()) {
    		synchronized(propMap) {
    			if (propMap.isEmpty()) {
    				readProperties();
    			}
    		}
    	}

    	final String value = propMap.get(key);
    	log.debug("value: {}", value);
    	
    	return value;
    }
    
    /**
     * 讀取 properties
     */
    public static void readProperties() {
    	Properties prop = new Properties();
    	
    	try {
    		Map<String, String> tempMap = new ConcurrentHashMap<String, String>();
    		prop.load(PropUtils.class.getResourceAsStream("/" + TPIGATEWAY_PROPERTIES));
    		for (Entry<Object, Object> entry : prop.entrySet()) {
    			String key = (String) entry.getKey();
    			String value = (String) entry.getValue();
    			tempMap.put(key, value);
    		}
    		prop.load(PropUtils.class.getResourceAsStream("/" + APPLICATION_PROPERTIES));
    		for (Entry<Object, Object> entry : prop.entrySet()) {
    			String key = (String) entry.getKey();
    			String value = (String) entry.getValue();
    			tempMap.put(key, value);
    		}
    		
    		propMap.clear();
    		propMap.putAll(tempMap);
    	} catch (IOException ex) {
    		log.error(ex.getMessage());
    	}
    }
}
