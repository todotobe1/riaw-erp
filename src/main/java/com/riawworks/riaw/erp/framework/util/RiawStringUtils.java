package com.riawworks.riaw.erp.framework.util;

public class RiawStringUtils {

	public static String emptyIfNull(Object object) {
		return object == null ? "" : object.toString();
	}

}
