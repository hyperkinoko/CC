package data_input_common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validator {
	public static boolean validateRequired(String str) {
		if(str == null || str.equals("")) {
			return false;
		}
		return true;
	}

	public static boolean validateStringSize(String str, int maxSize) {
		if(str.getBytes().length > maxSize) {
			return false;
		}
		return true;
	}

	public static boolean validateNumber(String str) {
		if(!str.matches("[0-9]+")) {
			return false;
		}
		if(str.matches("0+[0-9]+")) {
			return false;
		}
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean validateNumberAllowsBlank(String str) {
		if(str.equals("")) {
			return true;
		}
		if(!str.matches("[0-9]+")) {
			return false;
		}
		if(str.matches("0+[0-9]+")) {
			return false;
		}
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean validateNumberWithRange(String str, int min, int max) {
		if(!str.matches("[0-9]+")) {
			return false;
		}
		if(str.matches("0+[0-9]+")) {
			return false;
		}
		try {
			int inputInt = Integer.parseInt(str);
			if(inputInt > max || inputInt < min) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean validateZip(String str) {
		if(str.matches("[0-9]{7}")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validateZipAllowsBlank(String str) {
		if(str.equals("")) {
			return true;
		}
		if(str.matches("[0-9]{7}")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validateDate(String str) {
		if(!str.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
			return false;
		}
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	    format.setLenient(false);
		try {
		    format.parse(str);
		    return true;
		} catch (ParseException e) {
		    return false;
		}
	}

	public static boolean validateDateAllowsBlank(String str) {
		if(str.equals("")) {
			return true;
		}
		if(!str.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
			return false;
		}
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	    format.setLenient(false);
		try {
		    format.parse(str);
		    return true;
		} catch (ParseException e) {
		    return false;
		}
	}

	public static boolean validateRegex(String str, String regex) {
		if(str.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validateRegexAllowsBlank(String str, String regex) {
		if(str.equals("")) {
			return true;
		}
		if(str.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validateFileSize(long size, long max) {
		if(size > max) {
			return false;
		} else {
			return true;
		}
	}
}
