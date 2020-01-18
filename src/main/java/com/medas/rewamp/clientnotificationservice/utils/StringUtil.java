package com.medas.rewamp.clientnotificationservice.utils;

/**
 * String formatting
 * 
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 18, 2020
 *
 */
public class StringUtil {

	private StringUtil() {
	}

	/**
	 * Setting data to base template
	 * 
	 * @param birthdayTemplate
	 * @param data
	 * @return
	 */
	public static String getString(String content, Object... datas) {
		String formatted = content;
		String dataHolder = "\\{\\}";
		if (datas != null) {
			for (Object data : datas) {
				formatted = formatted.replaceFirst(dataHolder, data.toString());
			}
		}
		return formatted;
	}
}
