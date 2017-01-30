package com.jpm.supersimple.stockmarket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.jpm.supersimple.calculators.CalculatorConstants;
import com.jpm.supersimple.exceptions.ApplicationException;
import com.jpm.supersimple.exceptions.ValidationException;
import com.jpm.supersimple.instrument.Instrument;
import com.jpm.supersimple.instrument.Symbol;
import com.jpm.supersimple.trade.Trade;
import com.jpm.supersimple.trade.TradeBuilder;
import com.jpm.supersimple.trade.TradeIdGenerator;
import com.jpm.supersimple.trade.TradeType;
import com.jpm.supersimple.trade.TradeValidator;

public class StockMarketIntegrationTest {

	final Symbol TEA_SYMBOL = new Symbol("TEA");

	StockMarketIntegrationTestHelper marketHelper = new StockMarketIntegrationTestHelper();
	StockMarket stockMarket;
	TradeIdGenerator tradeIdGenerator = TradeIdGenerator.getTradeIdGenerator();
	TradeValidator tradeValidator = new TradeValidator();
	

	@Before
	public void init() throws ApplicationException {
		stockMarket = marketHelper.buildStockMarket();
	}
	
	
	@Test
	public void canBuildAStockMarketWithDefaultValues() throws ApplicationException {
		assertNotNull(stockMarket);
		assertThat(BigDecimal.ZERO, is(closeTo(stockMarket.getInstrumentPrice(TEA_SYMBOL), new BigDecimal(CalculatorConstants.MATHS_PRECISION))));
		assertThat(BigDecimal.ZERO, is(closeTo(stockMarket.calculateShareIndexMean(), new BigDecimal(CalculatorConstants.MATHS_PRECISION))));
	}

	@Test
	public void canCreateATradeOnTheStockMarket() throws ApplicationException {

		//Assert default value
		assertThat(BigDecimal.ZERO, is(closeTo(stockMarket.getInstrumentPrice(TEA_SYMBOL), new BigDecimal(CalculatorConstants.MATHS_PRECISION))));

		final Instrument instrument = stockMarket.getInstrument(TEA_SYMBOL);
		
		//Add trade
		addTrade(instrument, Long.valueOf(100l), TradeType.BUY, BigDecimal.valueOf(123.45));
		
		//Assert new value
		assertThat(BigDecimal.valueOf(123.45), is(closeTo(stockMarket.getInstrumentPrice(TEA_SYMBOL), new BigDecimal(CalculatorConstants.MATHS_PRECISION))));
		stockMarket.calculateDividendYield(TEA_SYMBOL);
	}
	
	private void addTrade(Instrument instrument, Long quantity, TradeType tradeType, BigDecimal price) throws ValidationException {
		TradeBuilder tb = new TradeBuilder(tradeIdGenerator, tradeValidator, instrument, 1l, TradeType.BUY, price);
		final Trade trade = tb.build();
		stockMarket.addTrade(trade);			
	}

}
