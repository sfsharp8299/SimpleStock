package com.jpm.supersimple.trade;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import com.jpm.supersimple.instrument.Instrument;

public class TradeTestUtils {

	public static Trade createMockTrade() {

		Trade trade = mock(Trade.class);
		Instrument instrument = mock(Instrument.class);
		Date tradeDate = new Date();
		
		when(trade.getInstrument()).thenReturn(instrument);
		when(trade.getPrice()).thenReturn(BigDecimal.valueOf(1));
		when(trade.getQuantity()).thenReturn(Long.valueOf(2));
		when(trade.getTradeDate()).thenReturn(tradeDate);
		when(trade.getTradeId()).thenReturn(Long.valueOf(3l));
		when(trade.getTradeType()).thenReturn(TradeType.SELL);
		
		return trade;
	}
}
