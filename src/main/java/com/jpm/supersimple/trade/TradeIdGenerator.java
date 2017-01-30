package com.jpm.supersimple.trade;

import java.util.concurrent.atomic.AtomicLong;

public class TradeIdGenerator {

	private static volatile TradeIdGenerator tradeIdGenerator;
	
	private final AtomicLong currentId = new AtomicLong();
	
	private TradeIdGenerator() {}
	
	public static TradeIdGenerator getTradeIdGenerator() {
		
		if (tradeIdGenerator == null) {
			synchronized (TradeIdGenerator.class) {
				if (tradeIdGenerator == null) {
					tradeIdGenerator = new TradeIdGenerator();
				}
			}
		}
		
		return tradeIdGenerator;
	}
	
	
	public long getNextTradeId() {
		return currentId.incrementAndGet();
	}
	
}
