package com.jpm.supersimple.instrument;

import static com.jpm.supersimple.validation.Assertions.assertGreaterThanZero;
import static com.jpm.supersimple.validation.Assertions.assertNotEmpty;
import static com.jpm.supersimple.validation.Assertions.assertNotNegative;
import static com.jpm.supersimple.validation.Assertions.assertNotNull;

import com.jpm.supersimple.exceptions.ValidationException;

public class InstrumentValidator {
	
	public static InstrumentValidator newInstrumentValidator() {
		return new InstrumentValidator();
	}

	public void validate(Instrument instrument) throws ValidationException {
		assertNotNull(instrument, "Instrument");
		assertNotNull(instrument.getInstrumentType(), "Instrument type");
		assertNotNull(instrument.getSymbol(), "Instrument symbol");		
		assertNotEmpty(instrument.getSymbol().getCode(), "Instrument symbol");		
		assertNotNegative(instrument.getLastDividend(), "Instrument last dividend");
		assertGreaterThanZero(instrument.getParValue(), "Instrument par value");
		
		if (instrument.getInstrumentType() == InstrumentType.PREFERED) {
			assertGreaterThanZero(instrument.getFixedDividend(), "Instrument fixed dividend");
		}
	}
	
}
