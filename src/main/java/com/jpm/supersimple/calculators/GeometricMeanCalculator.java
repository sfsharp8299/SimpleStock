package com.jpm.supersimple.calculators;

import static com.jpm.supersimple.validation.Assertions.assertNotNegative;
import static com.jpm.supersimple.validation.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Collection;

import com.jpm.supersimple.exceptions.ValidationException;

public class GeometricMeanCalculator implements Calculator {
	
	private final Collection<BigDecimal> prices;
	
	public GeometricMeanCalculator(final Collection<BigDecimal> prices) throws ValidationException {
		assertNotNull(prices, "prices");
		this.prices = prices;
		
		for (BigDecimal price : prices) {
			assertNotNegative(price, "price");
		}
	}

	@Override
	public BigDecimal calculate() {
		BigDecimal priceTotal = BigDecimal.ZERO;
		for (BigDecimal price : prices) {
			priceTotal = priceTotal.add(price);
		}
		return BigDecimal.valueOf(Math.pow(priceTotal.doubleValue(), 1.0 / prices.size()));
	}

}
