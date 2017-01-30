package com.jpm.supersimple.validation;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jpm.supersimple.exceptions.ValidationException;

public class AssertionsTest {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	
	@Test
	public void testAssertNotNegative() throws ValidationException {
		Assertions.assertNotNegative(BigDecimal.ZERO, null);
		Assertions.assertNotNegative(Long.valueOf(1l), null);
	}

	@Test
	public void testAssertGreaterThanZero() throws ValidationException {
		Assertions.assertGreaterThanZero(BigDecimal.ONE, null);
		Assertions.assertGreaterThanZero(Long.valueOf(100l), null);
	}

	@Test
	public void testAssertNotNull() throws ValidationException {
		Assertions.assertNotNull("", null);
		Assertions.assertNotNull(new Integer(1), null);
	}
		
	@Test
	public void testAssertNotEmpty() throws ValidationException {
		Assertions.assertNotEmpty("not empty", null);
	}
	
	@Test
	public void testAssertNotNegativeThrowsException() throws ValidationException {
		expectedEx.expect(ValidationException.class);
	    expectedEx.expectMessage("field cannot be negative");
		Assertions.assertNotNegative(new BigDecimal(-1), "field");
	}
	
	
	@Test
	public void testAssertGreaterThanZeroThrowsException() throws ValidationException {
		expectedEx.expect(ValidationException.class);
	    expectedEx.expectMessage("field cannot be less than or equal to zero");
	    Assertions.assertGreaterThanZero(BigDecimal.ZERO, "field");
	}

	@Test
	public void testAssertNotNegativeThrowsNullPointerException() throws ValidationException {
		expectedEx.expect(ValidationException.class);
	    expectedEx.expectMessage("field");		
		Assertions.assertNotNegative((BigDecimal)null, "field cannot be null");
		
		Assertions.assertNotNegative(new BigDecimal(-1), "field cannot be negative");
	}
	

	@Test
	public void testAssertNotNullThrowsException() throws ValidationException {
		expectedEx.expect(ValidationException.class);
	    expectedEx.expectMessage("field");
		Assertions.assertNotNull(null, "field cannot be null");
	}
	
	@Test
	public void testAssertNotEmptyThrowsException() throws ValidationException {
		expectedEx.expect(ValidationException.class);
	    expectedEx.expectMessage("field");
		Assertions.assertNotEmpty("", "field");
		Assertions.assertNotNull(null, "field cannot be empty");
	}
	
}
