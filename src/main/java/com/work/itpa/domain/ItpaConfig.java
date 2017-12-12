package com.work.itpa.domain;

import java.math.BigDecimal;
import java.util.Properties;

/**
 * Configuration for the system
 * 
 * @author Developer
 * 
 * Key value pairs are populated from the properties file and used in the rules.
 *
 */
public class ItpaConfig {
	
	Properties props;
	
	public static final String NA = "NA";
	
	/**
	 * Return string value of the key
	 * 
	 * Returns NA if no key is found
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		return props.getProperty(key,"NA");
	}

	/**
	 * Returns 0 if no key is found
	 * 
	 * @param key
	 * @return
	 */
	public BigDecimal getBigDecimal(String key) {
		return new BigDecimal(props.getProperty(key,"0"));
	}

	/**
	 * @return the props
	 */
	public Properties getProps() {
		return props;
	}

	/**
	 * @param props the props to set
	 */
	public void setProps(Properties props) {
		this.props = props;
	}
	
	
	
}
