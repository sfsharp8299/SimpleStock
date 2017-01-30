package com.jpm.supersimple.calculators;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jpm.supersimple.exceptions.ValidationException;

public class VolumeWeightedPriceCalculatorTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	List<PriceQuantity> priceQuantities = new ArrayList<PriceQuantity>();
	
	@Before
	public void init() {
		priceQuantities.add(new PriceQuantity(BigDecimal.ONE, 2l));
		priceQuantities.add(new PriceQuantity(BigDecimal.TEN, 10l));
	}
	
	@Test
	public void testCanComputeVolumeWeightedPrice() throws ValidationException {
		VolumeWeightedPriceCalculator vwpCalculator = new VolumeWeightedPriceCalculator(priceQuantities);
		assertCalculationIsCorrect(BigDecimal.valueOf(8.5), vwpCalculator);
	}
	
	@Test
	public void testCorrectlyValidatesEmptyList() throws ValidationException {		
		assertValidationException("quantity cannot be less than or equal to zero", new ArrayList<PriceQuantity>());
	}
	
	@Test
	public void testCorrectlyValidatesNullList() throws ValidationException {	
		assertValidationException("prices cannot be null", null);
	}
	
	@Test
	public void testCorrectlyValidatesListWithNullPrice() throws ValidationException {	
		assertValidationException("price cannot be null", Arrays.asList(new PriceQuantity(null, 1l)));
	}
	
	@Test
	public void testCorrectlyValidatesListWithNullQuantity() throws ValidationException {	
		assertValidationException("quantity cannot be null", Arrays.asList(new PriceQuantity(BigDecimal.valueOf(1l), null)));
	}
	
	private void assertCalculationIsCorrect(BigDecimal expected, Calculator calculator) throws ValidationException {
		assertThat(expected, is(closeTo(calculator.calculate(), new BigDecimal(CalculatorConstants.MATHS_PRECISION))));
	}

	private void assertValidationException(String expectedErrorMessage, List<PriceQuantity> priceQuantities) throws ValidationException {
		expectedEx.expect(ValidationException.class);
		expectedEx.expectMessage(expectedErrorMessage);
		new VolumeWeightedPriceCalculator(priceQuantities);
	}
}
