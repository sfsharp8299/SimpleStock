package com.jpm.supersimple.instrument.index;

import static com.jpm.supersimple.validation.Assertions.assertNotNegative;
import static com.jpm.supersimple.validation.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jpm.supersimple.exceptions.InstrumentNotFoundException;
import com.jpm.supersimple.exceptions.ValidationException;
import com.jpm.supersimple.instrument.Instrument;
import com.jpm.supersimple.instrument.InstrumentValidator;
import com.jpm.supersimple.instrument.Symbol;

public class InstrumentIndexActionValidator {
	
	private static final Logger LOGGER = Logger.getLogger(InstrumentIndexActionValidator.class.getName());
	private static final String SYMBOL = "symbol";
	
	private InstrumentValidator instrumentValidator;

	public InstrumentIndexActionValidator(InstrumentValidator instrumentValidator) {
		this.instrumentValidator = instrumentValidator;
	}

	void assertCanAddInstrumentWithPrice(final Map<Symbol, Instrument> instrumentsIndex, final Map<Symbol, BigDecimal> priceIndex, final Instrument instrument, BigDecimal lastPrice) throws ValidationException  {
		instrumentValidator.validate(instrument);
		assertNotNegative(lastPrice, "last price");
		
		final Symbol symbol = instrument.getSymbol();
		if (instrumentsIndex.containsKey(symbol)) {
			String msg = String.format("Cannot add instrument with symbol %s. Instrument already exist.", symbol.getCode());
			LOGGER.error(msg);
			throw new ValidationException(msg);
		} else if (priceIndex.containsKey(symbol)) {
			String msg = String.format("Instrument-price with symbol %s already exists and will be replaced.", symbol.getCode());
			LOGGER.warn(msg);
		}
	}

	void assertInstrumentExists(final Map<Symbol, Instrument> instrumentsIndex, final Symbol symbol, final String attemptedAction) throws ValidationException {
		assertNotNull(symbol, SYMBOL);
		if (!instrumentsIndex.containsKey(symbol)) {
			String msg = String.format("Can't %s. Instrument with symbol %s does not exists", attemptedAction, symbol.getCode());
			LOGGER.error(msg);
			throw new ValidationException(msg);
		}
	}
	
	
	void assertPriceExists(final Map<Symbol, BigDecimal> priceIndex, final Symbol symbol, final String attemptedAction) throws ValidationException {
		assertNotNull(symbol, SYMBOL);
		if (!priceIndex.containsKey(symbol)) {
			String msg = String.format("Can't %s. Instrument-price with symbol %s does not exists", attemptedAction, symbol.getCode());
			LOGGER.error(msg);
			throw new ValidationException(msg);
		}
	}

	
	void assertCanRemoveInstrument(final Map<Symbol, Instrument> instrumentsIndex, final Map<Symbol, BigDecimal> priceIndex, final Symbol symbol) throws InstrumentNotFoundException, ValidationException {
		assertNotNull(symbol, SYMBOL);
		if (!instrumentsIndex.containsKey(symbol)) {
			String msg = String.format("Cannot remove instrument with symbol %s. Instrument does not exist.", symbol.getCode());
			LOGGER.error(msg);
			throw new InstrumentNotFoundException(msg);
		} else if (!priceIndex.containsKey(symbol)) {
			String msg = String.format("Instrument-price with symbol %s doesn't exist.", symbol.getCode());
			LOGGER.warn(msg);
		}
	}
	
}
