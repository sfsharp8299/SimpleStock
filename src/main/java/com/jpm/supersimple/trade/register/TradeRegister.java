package com.jpm.supersimple.trade.register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.jpm.supersimple.instrument.Instrument;
import com.jpm.supersimple.trade.Trade;

public class TradeRegister {

	private static final Logger LOGGER = Logger.getLogger(TradeRegister.class.getName());

	List<Trade> trades = Collections.synchronizedList(new ArrayList<Trade>());

	public void addTrade(Trade trade) {
		trades.add(trade);
		LOGGER.info("Added : " + trade);
	}

	public List<Trade> getLastTrades(Date sinceTime, Instrument instrument) {
		return trades.stream().filter(u -> u.getTradeDate().compareTo(sinceTime) <= 0)
				.filter(u -> u.getInstrument().equals(instrument)).collect(Collectors.toList());
	}
}
