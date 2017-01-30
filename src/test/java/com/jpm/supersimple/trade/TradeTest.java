package com.jpm.supersimple.trade;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.jpm.supersimple.instrument.Instrument;

public class TradeTest {

	TradeBuilder tradeBuilder = mock(TradeBuilder.class);
	Instrument instrument = mock(Instrument.class);
	Date tradeDate = new Date();
	
	@Before
	public void init() {
		when(tradeBuilder.getInstrument()).thenReturn(instrument);
		when(tradeBuilder.getPrice()).thenReturn(BigDecimal.valueOf(1));
		when(tradeBuilder.getQuantity()).thenReturn(Long.valueOf(2));
		when(tradeBuilder.getTradeDate()).thenReturn(tradeDate);
		when(tradeBuilder.getTradeId()).thenReturn(Long.valueOf(3l));
		when(tradeBuilder.getTradeType()).thenReturn(TradeType.SELL);
	}
	
	@Test
	public void canSetAllTradeFields() {
		Trade trade = new Trade(tradeBuilder);
		
		assertEquals(instrument, trade.getInstrument());
		assertEquals(BigDecimal.valueOf(1), trade.getPrice());
		assertEquals(Long.valueOf(2), trade.getQuantity());
		assertEquals(tradeDate, trade.getTradeDate());
		assertEquals(Long.valueOf(3), trade.getTradeId());
		assertEquals(TradeType.SELL, trade.getTradeType());
	}
	
}
