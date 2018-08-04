package org.shujito.ec.util;

/**
 * @author shujito, 8/3/18
 */
public class NumberUtils {
	public static int parseInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return 0;
		}
	}
}
