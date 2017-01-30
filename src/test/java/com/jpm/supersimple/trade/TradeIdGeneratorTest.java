package com.jpm.supersimple.trade;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TradeIdGeneratorTest {

	private TradeIdGenerator tradeIdGeneratorA = TradeIdGenerator.getTradeIdGenerator();
	private TradeIdGenerator tradeIdGeneratorB = TradeIdGenerator.getTradeIdGenerator();

	
	@Test
	public void testIDGeneratorIsASingleton() {
		assertEquals(tradeIdGeneratorA, tradeIdGeneratorB);
	}
	
	@Test
	public void testIdsAreSequential() {
		final long nextTradeIdB = tradeIdGeneratorB.getNextTradeId();
		final long nextTradeIdA = tradeIdGeneratorB.getNextTradeId();
		
		assertEquals(nextTradeIdB + 1, nextTradeIdA);
	}
}
