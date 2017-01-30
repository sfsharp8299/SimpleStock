package com.jpm.supersimple.stockmarket;

import com.jpm.supersimple.calculators.CalculatorFactory;
import com.jpm.supersimple.exceptions.ApplicationException;
import com.jpm.supersimple.instrument.InstrumentValidator;
import com.jpm.supersimple.instrument.index.InstrumentIndex;
import com.jpm.supersimple.instrument.index.InstrumentIndexActionValidator;
import com.jpm.supersimple.trade.register.TradeRegister;

public class StockMarketFactory {

	public StockMarket buildStockMarket() throws ApplicationException {

		InstrumentValidator instrumentValidator = InstrumentValidator.newInstrumentValidator();
		InstrumentIndexActionValidator indexActionValidator = new InstrumentIndexActionValidator(instrumentValidator);
		
		InstrumentIndex instrumentIndex = buildInstrumentIndex(indexActionValidator, instrumentValidator);
		TradeRegister tradeRegister = buildTradeRegister();
		CalculatorFactory calculatorFactory = buildCalculatorFactory();

		return new StockMarket(instrumentIndex, tradeRegister, calculatorFactory);
	}

	private CalculatorFactory buildCalculatorFactory() {
		return new CalculatorFactory();
	}

	private TradeRegister buildTradeRegister() {
		return new TradeRegister();
	}

	private InstrumentIndex buildInstrumentIndex(InstrumentIndexActionValidator indexActionValidator, InstrumentValidator instrumentValidator) {
		InstrumentIndex instrumentIndex = new InstrumentIndex(indexActionValidator, instrumentValidator);
		return instrumentIndex;
	}
}
