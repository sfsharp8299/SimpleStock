package com.jpm.supersimple.validation;

import java.util.Date;
import java.util.function.Predicate;

public class Predicates {
	
	private Predicates() {}

	public static Predicate<Object> isNull = s -> s == null;
	public static Predicate<Object> isNotNull = s -> s != null;
	public static Predicate<String> isEmptyString = s -> s.trim().length() == 0;
		
	public static <T> Predicate<Comparable<T>> isLessThan(T to) {
		return p -> p.compareTo(to) < 0;
	}

	public static <T> Predicate<Comparable<T>> isLessThanOrEqualTo(T to) {
		return p -> p.compareTo(to) <= 0;
	}
	
	public static <T> Predicate<Comparable<T>> isGreaterThan(T to) {
		return p -> p.compareTo(to) > 0;
	}
	
	public static <T> Predicate<Comparable<T>> isWithinRange(T min, T max) {
        return p -> p.compareTo(min) >= 0 && p.compareTo(max) <= 0;
    }
	
	
	public static Predicate<Date> isMoreRecent(Date cutoffdate) {
        return p -> p.compareTo(cutoffdate) >= 0;
    }
	
}
