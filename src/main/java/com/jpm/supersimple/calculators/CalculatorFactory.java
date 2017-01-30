package com.jpm.supersimple.calculators;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import com.jpm.supersimple.exceptions.ValidationException;
import com.jpm.supersimple.instrument.InstrumentType;

public class CalculatorFactory {
	
	public Calculator createDividendYieldCalculator(InstrumentType instrumentType, BigDecimal lastDividend, BigDecimal fixedDividend, BigDecimal parValue, BigDecimal price) throws ValidationException {
		switch (instrumentType) {
		case COMMON:
			return new CommonDividendYieldCalculator(lastDividend, price);
		case PREFERED:
			return new PreferedDividendYieldCalculator(fixedDividend, parValue, price);
		default:
			throw new IllegalArgumentException("Unsupported instrument type " + instrumentType.name());
		}
	}

	public Calculator createPERatioCalculator(BigDecimal lastDividend, BigDecimal price) throws ValidationException {
		return new PERatioCalculator(lastDividend, price);
	}
	
	public Calculator createShareIndexMeanCalculator(Collection<BigDecimal> prices) throws ValidationException {
		return new GeometricMeanCalculator(prices);
	}
	
	public Calculator createVolumeWeightedPriceCalculator(List<PriceQuantity> priceQuantities) throws ValidationException {
		return new VolumeWeightedPriceCalculator(priceQuantities);
	}
}
