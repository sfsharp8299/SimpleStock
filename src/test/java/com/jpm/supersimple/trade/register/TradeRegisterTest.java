package com.jpm.supersimple.trade.register;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.jpm.supersimple.instrument.Instrument;
import com.jpm.supersimple.trade.Trade;

public class TradeRegisterTest {

	private static final Date PAST_DATE = new Date(1);
	private static final Date CURRENT_DATE = new Date(2);
	private static final Date FUTURE_DATE = new Date(3);
	
	TradeRegister tradeRegister;

	Instrument instrument = mock(Instrument.class);
	Trade oldTrade = mock(Trade.class);
	Trade newTrade = mock(Trade.class);
	
	@org.junit.Before
	public void setup(){
		tradeRegister = new TradeRegister();
		when(newTrade.getInstrument()).thenReturn(instrument);
		when(oldTrade.getInstrument()).thenReturn(instrument);
		when(oldTrade.getTradeDate()).thenReturn(PAST_DATE);
		when(newTrade.getTradeDate()).thenReturn(FUTURE_DATE);
	}
	
	@Test
	public void canAddOneTrade() {
		tradeRegister.addTrade(oldTrade);
		final List<Trade> lastTrades = tradeRegister.getLastTrades(PAST_DATE, oldTrade.getInstrument());
		assertTrue(lastTrades.size() == 1);
		assertEquals(oldTrade, lastTrades.get(0));
	}
	
	@Test
	public void canAddTwoTrade() {
		tradeRegister.addTrade(oldTrade);
		tradeRegister.addTrade(oldTrade);
		final List<Trade> lastTrades = tradeRegister.getLastTrades(PAST_DATE, oldTrade.getInstrument());
		assertTrue(lastTrades.size() == 2);
	}

	@Test
	public void canGetLastTrades() {
		tradeRegister.addTrade(oldTrade);
		tradeRegister.addTrade(newTrade);
		final List<Trade> lastTrades = tradeRegister.getLastTrades(CURRENT_DATE, oldTrade.getInstrument());
		assertTrue(lastTrades.size() == 1);		
	}
}
