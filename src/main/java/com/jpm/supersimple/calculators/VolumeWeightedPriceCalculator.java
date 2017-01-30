package com.jpm.supersimple.calculators;

import static com.jpm.supersimple.validation.Assertions.assertGreaterThanZero;
import static com.jpm.supersimple.validation.Assertions.assertNotNegative;
import static com.jpm.supersimple.validation.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import com.jpm.supersimple.exceptions.ValidationException;

public class VolumeWeightedPriceCalculator implements Calculator {

	private final List<PriceQuantity> priceQuantities;

	VolumeWeightedPriceCalculator(final List<PriceQuantity> priceQuantities) throws ValidationException {
		assertNotNull(priceQuantities, "prices");
		this.priceQuantities = priceQuantities;

		boolean nonZeroQuantityTotal = false;

		for (PriceQuantity priceQuantity : priceQuantities) {
			assertNotNull(priceQuantity, "price quantity");
			assertNotNegative(priceQuantity.getPrice(), "price");
			assertNotNegative(priceQuantity.getQuantity(), "quantity");
			if (priceQuantity.getQuantity().longValue() > 0) {
				nonZeroQuantityTotal = true;
			}
		}

		if (!nonZeroQuantityTotal) {
			// Deliberate use of 0 to force and centralise value assertion
			// logging and exception creation
			assertGreaterThanZero(0L, "quantity");
		}
	}

	@Override
	public BigDecimal calculate() {
		double priceByQuantity = 0;
		long quantity = 0;
		for (PriceQuantity priceQuantity : priceQuantities) {
			priceByQuantity = priceByQuantity
					+ (priceQuantity.getPrice().doubleValue() * priceQuantity.getQuantity().doubleValue());
			quantity = quantity + priceQuantity.getQuantity().longValue();
		}

		return BigDecimal.valueOf(priceByQuantity / quantity);
	}

}
