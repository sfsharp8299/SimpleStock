package com.jpm.supersimple.instrument;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.jpm.supersimple.exceptions.ValidationException;
import com.jpm.supersimple.instrument.Instrument;
import com.jpm.supersimple.instrument.InstrumentBuilder;
import com.jpm.supersimple.instrument.InstrumentType;
import com.jpm.supersimple.instrument.InstrumentValidator;
import com.jpm.supersimple.instrument.Symbol;

public class InstrumentBuilderTest {
	
	private InstrumentValidator instrumentValidator = mock(InstrumentValidator.class);
	
	InstrumentBuilder instrumentBuilder;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@org.junit.Before
	public void setup(){
		instrumentBuilder = new InstrumentBuilder(instrumentValidator);
	}
	
	@Test
	public void testBuilderValidates() throws ValidationException {
		instrumentBuilder
		.instrumentType(InstrumentType.COMMON)
		.symbol(new Symbol("symbol"))
		.build();
		verify(instrumentValidator, times(1)).validate(Mockito.any(Instrument.class));
	}
	
	@Test
	public void testExceptionThrownWhenValidationFails() throws ValidationException {
		doThrow(new ValidationException("build-error")).when(instrumentValidator).validate(Mockito.any(Instrument.class));
		expectedEx.expect(ValidationException.class);
		expectedEx.expectMessage("build-error");
		instrumentBuilder.build();
	}
	
	@Test
	public void testFieldsApplied() throws ValidationException {
		final Instrument instrument = instrumentBuilder
				.instrumentType(InstrumentType.COMMON)
				.symbol(new Symbol("symbol"))
				.fixedDividend(new BigDecimal(1))
				.lastDividend(new BigDecimal(2))
				.parValue(new BigDecimal(3))
				.build();

		assertEquals(InstrumentType.COMMON, instrument.getInstrumentType());
		assertEquals(new Symbol("symbol"), instrument.getSymbol());
		assertEquals(new BigDecimal(1), instrument.getFixedDividend());
		assertEquals(new BigDecimal(2), instrument.getLastDividend());
		assertEquals(new BigDecimal(3), instrument.getParValue());
	}
	
}
