package com.codegenerator;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneratorUtility {

	public static String now() {
		SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
		return dt1.format(new Date());

	}
	public static String firstToUpperCase(String input) {
		if(isNull(input)) return null;
		String post = input.substring(1, input.length());
		String first = ("" + input.charAt(0)).toUpperCase();
		return first + post;
	}

	public static String firstToLowerCase(String input) {
		if(isNull(input)) return null;
		String post = input.substring(1, input.length());
		String first = ("" + input.charAt(0)).toLowerCase();
		return first + post;
	}

	public static String replaceSpecial(String input) {
		if(isNull(input)) return null;
		input = Normalizer.normalize(input, Normalizer.Form.NFD);
		return input.replaceAll("[^\\p{L}\\p{Nd}]", "");
	}
	
	public static String replaceBarN(String input) {
		if(isNull(input)) return null;
		return  input.replace("\n", "").replace("\r", "");
	}

	public static boolean isNull(Object input) {
		if (input!=null) {
			return false;
		}
		return true;
	}
	
	public static boolean isNotNull(Object input) {
		return ! isNull(input);
	}
	
	public static String getSpecificType(String input, String language) {
		if(isNull(input)) return null;
		String typeSafe = null;
		if (input.toLowerCase().indexOf("string") > 0) {
			if (language.trim().equalsIgnoreCase("JAVA")) {
				typeSafe = "String";
			} else if (language.trim().equalsIgnoreCase("XSD")) {
				typeSafe = "string";
			} else if (language.trim().equalsIgnoreCase("CS")) {
				typeSafe = "string";				
			}
		} else if (input.toLowerCase().indexOf("date") > 0) {
			if (language.trim().equalsIgnoreCase("JAVA")) {
				typeSafe = "java.util.Date";
			} else if (language.trim().equalsIgnoreCase("XSD")) {
				typeSafe = "date";
			} else if (language.trim().equalsIgnoreCase("CS")) {
				typeSafe = "DateTime";
			}
		} else if (input.toLowerCase().indexOf("dateTime") > 0) {
			if (language.trim().equalsIgnoreCase("JAVA")) {
				typeSafe = "java.util.Date";
			} else if (language.trim().equalsIgnoreCase("XSD")) {
				typeSafe = "dateTime";
			} else if (language.trim().equalsIgnoreCase("CS")) {
				typeSafe = "DateTime";
			}
		} else if (input.toLowerCase().indexOf("decimal") > 0) {
			if (language.trim().equalsIgnoreCase("JAVA")) {
				typeSafe = "java.math.BigDecimal";
			} else if (language.trim().equalsIgnoreCase("XSD")) {
				typeSafe = "decimal";
			} else if (language.trim().equalsIgnoreCase("CS")) {
				typeSafe = "decimal";
			}
		} else if (input.toLowerCase().indexOf("integer") > 0) {
			if (language.trim().equalsIgnoreCase("JAVA")) {
				typeSafe = "Integer";
			} else if (language.trim().equalsIgnoreCase("XSD")) {
				typeSafe = "integer";
			} else if (language.trim().equalsIgnoreCase("CS")) {
				typeSafe = "int";
			}
		}
		return typeSafe;
	}

	public static void main(String[] args) {
//		 System.out.println(replaceSpecial("xs:Data_xs:  Hora_Úâãáàä]éèêë]_íìîï]_óòôõö]úùûü]_ç]_); ÂÃÁÀÄ]ÉÈÊË]_ÍÌÎÏ]_ÓÒÔÕÖ]ÚÙÛÜ]_Ç]_ltima_Modificação"));
		// System.out.println("xs:asdasd".replaceAll("xs:", ""));
		
//		System.out.println(getSpecificType("xs:string", "xsd"));
		
		

	}
}