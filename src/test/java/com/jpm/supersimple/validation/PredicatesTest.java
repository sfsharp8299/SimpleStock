package com.jpm.supersimple.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

public class PredicatesTest {

	@Test
	public void testIsNull() {
		assertFalse(Predicates.isNull.test(""));
		assertFalse(Predicates.isNull.test(BigDecimal.ZERO));

		assertTrue(Predicates.isNull.test(null));
	}

	@Test
	public void testIsEmptyString() {
		assertFalse(Predicates.isEmptyString.test("not_empty"));
		assertTrue(Predicates.isEmptyString.test(""));
		assertTrue(Predicates.isEmptyString.test("  "));
	}
	
	@Test
	public void testIsLessThan() {
		assertTrue(Predicates.isLessThan(BigDecimal.ZERO).test(new BigDecimal(-1)));

		assertFalse(Predicates.isLessThan(BigDecimal.ZERO).test(BigDecimal.ZERO));
		assertFalse(Predicates.isLessThan(BigDecimal.ZERO).test(new BigDecimal(1)));
	}
	
	@Test(expected=NullPointerException.class)
	public void testIsLessThanZeroWithNull() {
		assertFalse(Predicates.isLessThan(BigDecimal.ZERO).test(null));
	}
	

	@Test
	public void testIsLessThanOrEqualToZero() {
		assertTrue(Predicates.isLessThanOrEqualTo(Long.MAX_VALUE).test(Long.MIN_VALUE));
		assertTrue(Predicates.isLessThanOrEqualTo(new Long(0L)).test(new Long(-1L)));
		
		assertFalse(Predicates.isLessThanOrEqualTo(BigDecimal.ZERO).test(new BigDecimal(1)));
	}
	
	@Test(expected=NullPointerException.class)
	public void testIsLessThanOrEqualToZeroWithNull() {
		assertFalse(Predicates.isLessThanOrEqualTo(BigDecimal.ZERO).test(null));
	}
	
	@Test
	public void testIsWithinRange() {
		assertTrue(Predicates.isWithinRange(BigDecimal.ZERO, BigDecimal.ONE).test(new BigDecimal(0.5)));
		assertTrue(Predicates.isWithinRange(BigDecimal.ZERO, BigDecimal.ONE).test(BigDecimal.ONE));
		assertTrue(Predicates.isWithinRange(BigDecimal.ZERO, BigDecimal.ONE).test(BigDecimal.ZERO));

		assertFalse(Predicates.isWithinRange(BigDecimal.ZERO, BigDecimal.ONE).test(new BigDecimal(1.1)));
		assertFalse(Predicates.isWithinRange(BigDecimal.ZERO, BigDecimal.ONE).test(new BigDecimal(-0.1)));
	}

	@Test(expected=NullPointerException.class)
	public void testIsWithinRangeWithNullParams() {
		assertFalse(Predicates.isWithinRange(null, null).test(null));
	}
	
	@Test(expected=NullPointerException.class)
	public void testIsWithinRangeWithNullRange() {
		assertFalse(Predicates.isWithinRange((BigDecimal)null, (BigDecimal)null).test(BigDecimal.ZERO));
	}
	
	@Test(expected=NullPointerException.class)
	public void testIsWithinRangeWithNullValue() {
		assertFalse(Predicates.isWithinRange(BigDecimal.ZERO, BigDecimal.ZERO).test(null));
	}
	
	@Test(expected=NullPointerException.class)
	public void testIsWithinRangeWithNullMin() {
		assertFalse(Predicates.isWithinRange(null, BigDecimal.ZERO).test(BigDecimal.ZERO));
	}
	
	@Test(expected=NullPointerException.class)
	public void testIsWithinRangeWithNullMax() {
		assertFalse(Predicates.isWithinRange(BigDecimal.ZERO, null).test(BigDecimal.ZERO));
	}
}
