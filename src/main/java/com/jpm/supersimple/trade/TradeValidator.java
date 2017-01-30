package com.jpm.supersimple.trade;

import static com.jpm.supersimple.validation.Assertions.assertNotNull;

import com.jpm.supersimple.exceptions.ValidationException;

import static com.jpm.supersimple.validation.Assertions.assertGreaterThanZero;

public class TradeValidator {
	
	public static TradeValidator newTradeValidator() {
		return new TradeValidator();
	}

	public void validate(Trade trade) throws ValidationException {
		assertNotNull(trade, "Trade");
		assertGreaterThanZero(trade.getTradeId(), "Trade id");
		assertNotNull(trade.getTradeType(), "Trade type");
		assertNotNull(trade.getTradeDate(), "Trade date");	

		assertNotNull(trade.getInstrument(), "Instrument");
		assertGreaterThanZero(trade.getPrice(), "Price");
		assertGreaterThanZero(trade.getQuantity(), "Quantity");	
	}

}
