package com.riawworks.riaw.erp.framework.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RiawDateUtils {

	public static Integer thisYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	public static Integer thisMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static Integer lastMonth() {
		return previousNMonth(1);
	}

	public static Integer previousNMonth(Integer n) {
		return thisMonth() - (n % 12) == 0 ? 12 : thisMonth() - (n % 12);
	}

	public static String formatDate(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	public static String formatNow(String pattern) {
		return formatDate(new Date(), pattern);
	}

	public static Date backClock(Integer field, Integer amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(field, amount);
		return calendar.getTime();
	}

}
