package com.jpm.supersimple.calculators;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jpm.supersimple.exceptions.ValidationException;


public class PreferedDividendYieldCalculatorTest {
	
	private final double VERY_SMALL_NUMBER = 0.000000000001d;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testCalculate() throws ValidationException {
		assertCalculationIsCorrect(BigDecimal.valueOf(5L), BigDecimal.valueOf(0.10d), BigDecimal.valueOf(100L), BigDecimal.valueOf(2L));
		assertCalculationIsCorrect(BigDecimal.valueOf(5.4d), BigDecimal.valueOf(0.65d), BigDecimal.valueOf(50L), BigDecimal.valueOf(60L));
		assertCalculationIsCorrect(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(100L), BigDecimal.valueOf(2L));		
		assertCalculationIsCorrect(BigDecimal.ONE, BigDecimal.valueOf(VERY_SMALL_NUMBER), BigDecimal.valueOf(VERY_SMALL_NUMBER), BigDecimal.valueOf(VERY_SMALL_NUMBER));
	}

	@Test
	public void testNulls() throws ValidationException {
		assertValidationException("fixed dividend cannot be null", null, BigDecimal.ONE, BigDecimal.ONE);
		assertValidationException("par value cannot be null", BigDecimal.ONE, null, BigDecimal.ONE);
		assertValidationException("price cannot be null", BigDecimal.ONE, BigDecimal.ONE, null);
	}
	
	@Test
	public void testNegatives() throws ValidationException {
		assertValidationException("fixed dividend cannot be negative", BigDecimal.valueOf(-1l), BigDecimal.ONE, BigDecimal.ONE);
		assertValidationException("par value cannot be less than or equal to zero", BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ONE);
		assertValidationException("price cannot be less than or equal to zero", BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ZERO);
	}

	private void assertCalculationIsCorrect(BigDecimal expected, BigDecimal fixedDividend, BigDecimal parValue, BigDecimal price) throws ValidationException {
		Calculator calculator = new PreferedDividendYieldCalculator(fixedDividend, parValue, price);
		assertThat(expected, is(closeTo(calculator.calculate(), new BigDecimal(CalculatorConstants.MATHS_PRECISION))));
	}

	private void assertValidationException(String expectedErrorMessage, BigDecimal fixedDividend, BigDecimal parValue, BigDecimal price) throws ValidationException {
		expectedEx.expect(ValidationException.class);
		expectedEx.expectMessage(expectedErrorMessage);
		new PreferedDividendYieldCalculator(fixedDividend, parValue, price);
	}
}
