package com.jpm.supersimple.calculators;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jpm.supersimple.exceptions.ValidationException;

public class CommonDividendYieldCalculatorTest {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	private final double VERY_SMALL_NUMBER = 0.000000000001d;

	@Test
	public void testCalculate() throws ValidationException {
		assertCalculationIsCorrect(BigDecimal.valueOf(2L), BigDecimal.valueOf(4L), BigDecimal.valueOf(2L));
		assertCalculationIsCorrect(BigDecimal.valueOf(0.5), BigDecimal.valueOf(1), BigDecimal.valueOf(2));
		assertCalculationIsCorrect(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(2));
		assertCalculationIsCorrect(BigDecimal.ONE, BigDecimal.valueOf(VERY_SMALL_NUMBER), BigDecimal.valueOf(VERY_SMALL_NUMBER));
	}
	
	@Test
	public void testNullValues() throws ValidationException {
		expectedEx.expect(ValidationException.class);
		assertCalculationIsCorrect(BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(0));
		assertValidationException("price cannot be less than or equal to zero", BigDecimal.valueOf(1), BigDecimal.valueOf(-11), BigDecimal.valueOf(1));
	}
	
	@Test
	public void testNegativeDividend() throws ValidationException {
		assertValidationException("last dividend cannot be negative", BigDecimal.valueOf(1), BigDecimal.valueOf(-11), BigDecimal.valueOf(1));
	}

	private void assertCalculationIsCorrect(BigDecimal expected, BigDecimal lastDividend, BigDecimal price) throws ValidationException {
		CommonDividendYieldCalculator calculator = new CommonDividendYieldCalculator(lastDividend, price);
		assertEquals(expected, calculator.calculate());
	}
	
	private void assertValidationException(String expectedErrorMessage, BigDecimal expected, BigDecimal lastDividend, BigDecimal price) throws ValidationException {
		expectedEx.expect(ValidationException.class);
		expectedEx.expectMessage(expectedErrorMessage);
		new CommonDividendYieldCalculator(lastDividend, price);
	}
}
