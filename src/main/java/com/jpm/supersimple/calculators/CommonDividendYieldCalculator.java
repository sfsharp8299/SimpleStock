package com.jpm.supersimple.calculators;

import java.math.BigDecimal;

import com.jpm.supersimple.exceptions.ValidationException;
import com.jpm.supersimple.validation.Assertions;

public class CommonDividendYieldCalculator implements Calculator {

	private BigDecimal lastDividend;
	private BigDecimal price;
	
	public CommonDividendYieldCalculator(BigDecimal lastDividend, BigDecimal price) throws ValidationException {
		Assertions.assertNotNegative(lastDividend, "last dividend");
		Assertions.assertGreaterThanZero(price, "price");
		
		this.lastDividend = lastDividend;
		this.price = price;
	}
	
	@Override
	public BigDecimal calculate() {
		return lastDividend.divide(price);
	}

}
