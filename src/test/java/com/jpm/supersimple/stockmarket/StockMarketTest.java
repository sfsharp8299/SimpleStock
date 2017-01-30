package com.jpm.supersimple.stockmarket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Test;
import org.mockito.Mockito;

import com.jpm.supersimple.calculators.CalculatorFactory;
import com.jpm.supersimple.exceptions.ApplicationException;
import com.jpm.supersimple.instrument.Instrument;
import com.jpm.supersimple.instrument.index.InstrumentIndex;
import com.jpm.supersimple.stockmarket.StockMarket;
import com.jpm.supersimple.trade.register.TradeRegister;

public class StockMarketTest {


	private final InstrumentIndex index = mock(InstrumentIndex.class);
	private final TradeRegister tradeRegister = mock(TradeRegister.class);
	private final CalculatorFactory calculatorFactory = mock(CalculatorFactory.class);

	private final Instrument instrument = mock(Instrument.class);
	
	StockMarket stockMarket = new StockMarket(index, tradeRegister, calculatorFactory);
	
	@Test
	public void testCanAddInstrument() throws ApplicationException {
		stockMarket.addInstrument(instrument, BigDecimal.ONE);
		verify(index, times(1)).addInstrument(Mockito.any(Instrument.class), Mockito.any(BigDecimal.class));
	}
	
}
