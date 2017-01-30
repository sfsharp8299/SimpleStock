package com.jpm.supersimple.calculators;

import java.math.BigDecimal;

import com.jpm.supersimple.exceptions.ValidationException;
import com.jpm.supersimple.validation.Assertions;

public class PreferedDividendYieldCalculator implements Calculator {

	private final BigDecimal fixedDividend;
	private final BigDecimal parValue; 
	private final BigDecimal price;
	
	public PreferedDividendYieldCalculator(BigDecimal fixedDividend, BigDecimal parValue, BigDecimal price) throws ValidationException {
		Assertions.assertNotNegative(fixedDividend, "fixed dividend");
		Assertions.assertGreaterThanZero(parValue, "par value");
		Assertions.assertGreaterThanZero(price, "price");
		
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
		this.price = price;
	} 

	
	@Override
	public BigDecimal calculate() {
		return fixedDividend.multiply(parValue).divide(price,CalculatorConstants.MATHS_PRECISION, CalculatorConstants.ROUNDING_MODE);
	}

}
