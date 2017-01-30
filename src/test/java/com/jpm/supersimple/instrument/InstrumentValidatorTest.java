package com.jpm.supersimple.instrument;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jpm.supersimple.exceptions.ValidationException;
import com.jpm.supersimple.instrument.Instrument;
import com.jpm.supersimple.instrument.InstrumentType;
import com.jpm.supersimple.instrument.InstrumentValidator;
import com.jpm.supersimple.instrument.Symbol;

public class InstrumentValidatorTest {

	private Instrument instrument = mock(Instrument.class);
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	InstrumentValidator instrumentValidator = InstrumentValidator.newInstrumentValidator();
	
	@org.junit.Before
	public void setup(){
		when(instrument.getSymbol()).thenReturn(new Symbol("a_symbol"));
		when(instrument.getInstrumentType()).thenReturn(InstrumentType.COMMON);
		when(instrument.getLastDividend()).thenReturn(new BigDecimal(1));
		when(instrument.getParValue()).thenReturn(new BigDecimal(1));
	}
	
	
	@Test
	public void testCanValidateCommonInstrument() throws ValidationException {
		instrumentValidator.validate(instrument);		
	}
	
	@Test
	public void testCanValidateEmptySymbol() throws ValidationException {
		when(instrument.getSymbol()).thenReturn(new Symbol(""));
		assertException(instrument, "Instrument symbol cannot be empty");
	}

	@Test
	public void testCanValidateNullSymbol() throws ValidationException {
		when(instrument.getSymbol()).thenReturn(null);
		assertException(instrument, "Instrument symbol cannot be null");
	}
	
	@Test
	public void testCanValidateEmptyType() throws ValidationException {
		when(instrument.getInstrumentType()).thenReturn(null);
		assertException(instrument, "Instrument type cannot be null");
	}
	
	@Test
	public void testCanValidateLastDividendNull() throws ValidationException {
		when(instrument.getLastDividend()).thenReturn(null);
		assertException(instrument, "Instrument last dividend cannot be null");
	}
	
	@Test
	public void testCanValidateLastDividendNegative() throws ValidationException {
		when(instrument.getLastDividend()).thenReturn(new BigDecimal(-1));
		assertException(instrument, "Instrument last dividend cannot be negative");
	}

	@Test
	public void testCanValidateParValueNull() throws ValidationException {
		when(instrument.getParValue()).thenReturn(null);
		assertException(instrument, "Instrument par value cannot be null");
	}
	
	@Test
	public void testCanValidateParValueNegative() throws ValidationException {
		when(instrument.getLastDividend()).thenReturn(new BigDecimal(-1));
		assertException(instrument, "Instrument last dividend cannot be negative");
	}
	
	@Test
	public void testCanValidatePreferedInstrument() throws ValidationException {
		when(instrument.getInstrumentType()).thenReturn(InstrumentType.PREFERED);
		when(instrument.getFixedDividend()).thenReturn(new BigDecimal(1));
		instrumentValidator.validate(instrument);		
	}

	@Test
	public void testCanValidatePreferedInstrumentWithNegativeFixedDividend() throws ValidationException {
		when(instrument.getInstrumentType()).thenReturn(InstrumentType.PREFERED);
		when(instrument.getFixedDividend()).thenReturn(new BigDecimal(-1));
		assertException(instrument, "Instrument fixed dividend cannot be less than or equal to zero");
	}
	
	@Test
	public void testCanValidatePreferedInstrumentWithMissingFixedDividend() throws ValidationException {
		when(instrument.getInstrumentType()).thenReturn(InstrumentType.PREFERED);
		when(instrument.getFixedDividend()).thenReturn(null);
		assertException(instrument, "Instrument fixed dividend cannot be null");
	}
	
	private void assertException(Instrument instrument, String expectedErrorMessage) throws ValidationException {		
		expectedEx.expect(ValidationException.class);
		expectedEx.expectMessage(expectedErrorMessage);
		instrumentValidator.validate(instrument);
	}
}