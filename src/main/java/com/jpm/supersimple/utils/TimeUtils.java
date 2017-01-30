package com.jpm.supersimple.utils;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
	
	private TimeUtils(){}
	
	public static Date getNow() {
		return new Date();
	}

	public static Date getTimeMinutesAgo(int minutes) {
		return getTimeOffset(Calendar.MINUTE, -1 * minutes);
	}

	public static Date getTimeSecondsAgo(int seconds) {
		return getTimeOffset(Calendar.SECOND, -1 * seconds);
	}

	private static Date getTimeOffset(int field, int offset) {
		Calendar cal = Calendar.getInstance();
		cal.add(field, offset);
		return cal.getTime();
	}
}
