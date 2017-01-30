package com.jpm.supersimple.calculators;

import java.math.BigDecimal;

import com.jpm.supersimple.exceptions.ValidationException;
import com.jpm.supersimple.validation.Assertions;

public class PERatioCalculator implements Calculator {
	
	private final BigDecimal lastDividend;
	private final BigDecimal price;
	
	PERatioCalculator(BigDecimal lastDividend, BigDecimal price) throws ValidationException {
		Assertions.assertGreaterThanZero(lastDividend, "lastDividend");
		Assertions.assertNotNegative(price, "price");
		this.lastDividend = lastDividend;
		this.price = price;
	}

	@Override
	public BigDecimal calculate() {
		return price.divide(lastDividend);
	}

}
