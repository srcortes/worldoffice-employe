package com.worldoffice.test.util;
import java.security.SecureRandom;
import java.util.Random;
/**
 * 
 * @author srcortes
 *
 */
public class IntegrationUtil {
	public static String generateKey() {
	       StringBuffer strB = new StringBuffer("");
	       strB.append(String.valueOf(System.currentTimeMillis()).substring(7));
	       SecureRandom secRandom = new SecureRandom();
	       int bValue = secRandom.nextInt(Integer.MAX_VALUE);
	       bValue = (int) bValue/100000;
	       strB.append(Math.abs(bValue));
	       return strB.toString();
	}
}
