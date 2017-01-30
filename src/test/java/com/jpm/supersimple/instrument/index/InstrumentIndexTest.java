package com.jpm.supersimple.instrument.index;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.jpm.supersimple.exceptions.ApplicationException;
import com.jpm.supersimple.instrument.Instrument;
import com.jpm.supersimple.instrument.InstrumentValidator;
import com.jpm.supersimple.instrument.Symbol;

public class InstrumentIndexTest {
	
	private static final Symbol TEST_SYMBOL = new Symbol("a-symbol");
	private static final Instrument TEST_INSTRUMENT = mock(Instrument.class);
	private static final Instrument UPDATED_INSTRUMENT = mock(Instrument.class);
	private static final BigDecimal TEST_PRICE = new BigDecimal(123.45);

	InstrumentValidator instrumentValidator = mock(InstrumentValidator.class);
	InstrumentIndexActionValidator indexActionValidator = mock(InstrumentIndexActionValidator.class);

	InstrumentIndex instrumentIndex = new InstrumentIndex(indexActionValidator, instrumentValidator);
		
	@Before
	public void init() {
		when(TEST_INSTRUMENT.getSymbol()).thenReturn(TEST_SYMBOL);
		when(UPDATED_INSTRUMENT.getSymbol()).thenReturn(TEST_SYMBOL);
	}
	
	@Test
	public void testCanAddInstrument() throws ApplicationException {
		instrumentIndex.addInstrument(TEST_INSTRUMENT, TEST_PRICE);
		verify(indexActionValidator, times(1)).assertCanAddInstrumentWithPrice(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
		
		final Instrument instrument = instrumentIndex.getInstrument(TEST_SYMBOL);
		
		assertEquals(TEST_INSTRUMENT, instrument);
		
		final BigDecimal price = instrumentIndex.getPrice(TEST_SYMBOL);
		assertEquals(TEST_PRICE, price);
		
		final Collection<BigDecimal> prices = instrumentIndex.getPrices();
		assertTrue(prices.size() == 1);
		assertEquals(TEST_PRICE, prices.iterator().next());
	}
	
	@Test
	public void testCanRemoveInstrument() throws ApplicationException {
		instrumentIndex.addInstrument(TEST_INSTRUMENT, TEST_PRICE);
		assertNotNull(instrumentIndex.getInstrument(TEST_SYMBOL));
		
		instrumentIndex.removeInstrument(TEST_SYMBOL);
		assertNull(instrumentIndex.getInstrument(TEST_SYMBOL));
	}
	
	@Test
	public void testCanUpdateInstrument() throws ApplicationException {
		instrumentIndex.addInstrument(TEST_INSTRUMENT, TEST_PRICE);
		assertNotNull(instrumentIndex.getInstrument(TEST_SYMBOL));

		instrumentIndex.updateInstrument(UPDATED_INSTRUMENT);
		
		assertEquals(UPDATED_INSTRUMENT, instrumentIndex.getInstrument(TEST_SYMBOL));
	}
	
	@Test
	public void testCanUpdateInstrumentPrice() throws ApplicationException {
		instrumentIndex.addInstrument(TEST_INSTRUMENT, TEST_PRICE);
		assertNotNull(instrumentIndex.getInstrument(TEST_SYMBOL));
		
		final BigDecimal newPrice = BigDecimal.valueOf(987654321.123d);
		instrumentIndex.updatePrice(TEST_SYMBOL, newPrice);
		assertEquals(newPrice, instrumentIndex.getPrice(TEST_SYMBOL));
	}
	
}
