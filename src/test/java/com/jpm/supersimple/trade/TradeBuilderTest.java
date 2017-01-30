package com.jpm.supersimple.trade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.jpm.supersimple.exceptions.ValidationException;
import com.jpm.supersimple.instrument.Instrument;
import com.jpm.supersimple.utils.TimeUtils;

public class TradeBuilderTest {

	private static final BigDecimal TEST_PRICE = BigDecimal.valueOf(1.23);
	private static final Long TEST_QUANTITY = Long.valueOf(123L);
	private TradeValidator tradeValidator = mock(TradeValidator.class);
	private TradeIdGenerator tradeIdGenerator = mock(TradeIdGenerator.class);
	private Instrument instrument = mock(Instrument.class);

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testBuilderValidates() throws ValidationException {
		final TradeBuilder tradeBuilder = createTradeBuilder(instrument, TEST_QUANTITY, TradeType.BUY, TEST_PRICE);
		tradeBuilder.build();
		verify(tradeValidator, times(1)).validate(Mockito.any(Trade.class));
	}

	@Test
	public void testExceptionThrownWhenValidationFails() throws ValidationException {
		doThrow(new ValidationException("build-error")).when(tradeValidator).validate(Mockito.any(Trade.class));
		expectedEx.expect(ValidationException.class);
		expectedEx.expectMessage("build-error");
		final TradeBuilder tradeBuilder = createTradeBuilder(instrument, TEST_QUANTITY, TradeType.BUY, TEST_PRICE);
		tradeBuilder.build();
	}

	@Test
	public void testConstructorFieldsApplied() throws ValidationException {
		when(tradeIdGenerator.getNextTradeId()).thenReturn(123L);
		final TradeBuilder tradeBuilder = createTradeBuilder(instrument, TEST_QUANTITY, TradeType.BUY, TEST_PRICE);
		final Trade trade = tradeBuilder.build();
		assertEquals(Long.valueOf(123L), trade.getTradeId());
		assertTrue(trade.getTradeDate().before(TimeUtils.getNow()));

		final Date timeSecondsAgo = TimeUtils.getTimeSecondsAgo(2);
		System.out.println("TradeDate: " + trade.getTradeDate());
		System.out.println("timeSecondsAgo: " + timeSecondsAgo);

		assertTrue(trade.getTradeDate().after(timeSecondsAgo));

		assertEquals(TradeType.BUY, trade.getTradeType());

		assertEquals(instrument, trade.getInstrument());
		assertEquals(TEST_PRICE, trade.getPrice());
		assertEquals(TEST_QUANTITY, trade.getQuantity());
	}

	private TradeBuilder createTradeBuilder(final Instrument instrument, final Long quantity, final TradeType tradeType,
			final BigDecimal price) {
		return new TradeBuilder(tradeIdGenerator, tradeValidator, instrument, quantity, tradeType, price);
	}
}
