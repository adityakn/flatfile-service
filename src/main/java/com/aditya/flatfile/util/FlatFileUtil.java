package com.aditya.flatfile.util;
/**
 * Utility class.
 * @author aditya
 */
public class FlatFileUtil {
	public static boolean isPhone(String phone) {
		phone = phone.replaceAll("\\D+","");
		if(phone.length() == 10){
			return true;
		}
		return false;
	}
	
	public static boolean isValidLine(String line) {
		if (line != null) {
			String[] strList = line.split(",");
			if (strList.length == 5 || strList.length == 4) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
