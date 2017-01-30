package com.jpm.supersimple.validation;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.jpm.supersimple.exceptions.ValidationException;

public class Assertions {
	
	private static final Logger LOGGER = Logger.getLogger(Assertions.class.getName());
	
	Assertions() {}
	
	public static void assertNotNegative(BigDecimal value, String fieldname) throws ValidationException {
		assertNotNull(value, fieldname);
		if (Predicates.isLessThan(BigDecimal.ZERO).test(value)) {
			final String msg = fieldname + " cannot be negative";
			LOGGER.error(msg);
			throw new ValidationException(msg);
		}
	}
	
	public static void assertNotNegative(Long value, String fieldname) throws ValidationException {
		assertNotNull(value, fieldname);
		assertNotNegative(BigDecimal.valueOf(value), fieldname);
	}
	
	public static void assertGreaterThanZero(BigDecimal value, String fieldname) throws ValidationException {
		assertNotNull(value, fieldname);
		if (Predicates.isLessThanOrEqualTo(BigDecimal.ZERO).test(value)) {
			final String msg = fieldname + " cannot be less than or equal to zero";
			LOGGER.error(msg);
			throw new ValidationException(msg);
		}
	}
	
	public static void assertGreaterThanZero(Long value, String fieldname) throws ValidationException {
		assertNotNull(value, fieldname);
		assertGreaterThanZero(BigDecimal.valueOf(value), fieldname);
	}
		
	public static void assertNotEmpty(String str, String fieldName) throws ValidationException {
		assertNotNull(str, fieldName);
		if (Predicates.isEmptyString.test(str)) {
			final String msg = fieldName + " cannot be empty";
			LOGGER.error(msg);
			throw new ValidationException(msg);
		}
	}

	public static void assertNotNull(Object obj, String fieldName) throws ValidationException {
		if (Predicates.isNull.test(obj)) {
			final String msg = fieldName + " cannot be null";
			LOGGER.error(msg);
			throw new ValidationException(msg);
		}
	}
}
