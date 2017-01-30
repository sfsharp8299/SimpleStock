package com.jpm.supersimple.stockmarket;

import java.math.BigDecimal;

import com.jpm.supersimple.exceptions.ApplicationException;
import com.jpm.supersimple.exceptions.ValidationException;
import com.jpm.supersimple.instrument.Instrument;
import com.jpm.supersimple.instrument.InstrumentBuilder;
import com.jpm.supersimple.instrument.InstrumentType;
import com.jpm.supersimple.instrument.InstrumentValidator;
import com.jpm.supersimple.instrument.Symbol;

public class StockMarketIntegrationTestHelper {

	public StockMarket buildStockMarket() throws ApplicationException {
		
		StockMarketFactory stockMarketFactory = new StockMarketFactory();
		final StockMarket stockMarket = stockMarketFactory.buildStockMarket();
		
		initialiseIndex(stockMarket);
		
		return stockMarket;
	}

	private void initialiseIndex(StockMarket stockMarket) throws ApplicationException {
		addInstrument(stockMarket, InstrumentType.COMMON, new Symbol("TEA"), new BigDecimal(0), null, new BigDecimal(100), BigDecimal.valueOf(0));
		addInstrument(stockMarket, InstrumentType.COMMON, new Symbol("POP"), new BigDecimal(8), null, new BigDecimal(100), BigDecimal.valueOf(0));
		addInstrument(stockMarket, InstrumentType.COMMON, new Symbol("ALE"), new BigDecimal(23), null, new BigDecimal(60), BigDecimal.valueOf(0));
		addInstrument(stockMarket, InstrumentType.PREFERED, new Symbol("GIN"), new BigDecimal(8), BigDecimal.valueOf(0.02), new BigDecimal(100), BigDecimal.valueOf(0));
		addInstrument(stockMarket, InstrumentType.COMMON, new Symbol("JOE"), new BigDecimal(13), null, new BigDecimal(250), BigDecimal.valueOf(0));
	}
	
	private void addInstrument(StockMarket stockMarket, InstrumentType instrumentType, Symbol symbol, BigDecimal lastDividend, BigDecimal fixedDividend, BigDecimal parValue, BigDecimal initialPrice) throws ApplicationException {
		Instrument instrument = createInstrument(instrumentType, symbol, lastDividend, fixedDividend, parValue);
		stockMarket.addInstrument(instrument, initialPrice);
	}
	
	private Instrument createInstrument(InstrumentType instrumentType, Symbol symbol, BigDecimal lastDividend, BigDecimal fixedDividend, BigDecimal parValue) throws ValidationException {
		return new InstrumentBuilder(InstrumentValidator.newInstrumentValidator())
		.instrumentType(instrumentType)
		.symbol(symbol)
		.lastDividend(lastDividend)
		.fixedDividend(fixedDividend)
		.parValue(parValue)
		.build();
	}

}
