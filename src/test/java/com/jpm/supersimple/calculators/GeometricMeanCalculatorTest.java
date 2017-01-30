package com.jpm.supersimple.calculators;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jpm.supersimple.exceptions.ValidationException;

public class GeometricMeanCalculatorTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	List<BigDecimal> prices = new ArrayList<BigDecimal>();
	
	@Before
	public void initDate() {
		prices.add(new BigDecimal(4));
		prices.add(new BigDecimal(1));
		prices.add(new BigDecimal(1/32));
	}
	
	@Test
	public void testCanComputeGeometricMean() throws ValidationException {
		Calculator meanCalculator = new GeometricMeanCalculator(prices);
		assertCalculationIsCorrect(BigDecimal.valueOf(1/2), meanCalculator);
		
		prices.clear();
		meanCalculator = new GeometricMeanCalculator(prices);
		assertCalculationIsCorrect(BigDecimal.ZERO, meanCalculator);
	}
	
	@Test
	public void testNullValues() throws ValidationException {
		expectedEx.expect(ValidationException.class);
		prices.add(null);
		assertValidationException("price cannot be null", prices);
		
		assertValidationException("price cannot be null", null);
	}
	
	private void assertCalculationIsCorrect(BigDecimal expected, Calculator calculator) throws ValidationException {
		assertThat(expected, is(closeTo(calculator.calculate(), new BigDecimal(CalculatorConstants.MATHS_PRECISION))));
	}
	
	private void assertValidationException(String expectedErrorMessage, List<BigDecimal> prices) throws ValidationException {
		expectedEx.expect(ValidationException.class);
		expectedEx.expectMessage(expectedErrorMessage);
		new GeometricMeanCalculator(prices);
	}
}
