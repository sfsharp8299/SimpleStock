package com.jpm.supersimple.trade;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jpm.supersimple.exceptions.ValidationException;

public class TradeValidatorTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	private TradeValidator tradeValidator = new TradeValidator();
	private Trade testTrade = TradeTestUtils.createMockTrade();
	
	@Test
	public void testCanValidate() throws ValidationException {
		tradeValidator.validate(testTrade);
	}
	
	@Test
	public void testNullInstrumentHandled() throws ValidationException {
		when(testTrade.getInstrument()).thenReturn(null);
		assertValidationExceptionThrown("Instrument cannot be null", testTrade);
	}

	@Test
	public void testNullPrice() throws ValidationException {
		when(testTrade.getPrice()).thenReturn(null);
		assertValidationExceptionThrown("Price cannot be null", testTrade);
	}
	
	@Test
	public void testNullQuantity() throws ValidationException {
		when(testTrade.getQuantity()).thenReturn(null);
		assertValidationExceptionThrown("Quantity cannot be null", testTrade);
	}
	
	@Test
	public void testNullTradeDate() throws ValidationException {
		when(testTrade.getTradeDate()).thenReturn(null);
		assertValidationExceptionThrown("Trade date cannot be null", testTrade);
	}

	@Test
	public void testNullTradeId() throws ValidationException {
		when(testTrade.getTradeId()).thenReturn(null);
		assertValidationExceptionThrown("Trade id cannot be null", testTrade);
	}
	
	@Test
	public void testNullTradeType() throws ValidationException {
		when(testTrade.getTradeType()).thenReturn(null);
		assertValidationExceptionThrown("Trade type cannot be null", testTrade);
	}
	
	@Test
	public void testZeroPriceNotAllowed() throws ValidationException {
		when(testTrade.getPrice()).thenReturn(BigDecimal.valueOf(0));
		assertValidationExceptionThrown("Price cannot be less than or equal to zero", testTrade);
	}
	
	@Test
	public void testZeroQuantityNotAllowed() throws ValidationException {
		when(testTrade.getQuantity()).thenReturn(Long.valueOf(0));
		assertValidationExceptionThrown("Quantity cannot be less than or equal to zero", testTrade);
	}
	
	@Test
	public void testZeroTradeIdNotAllowed() throws ValidationException {
		when(testTrade.getTradeId()).thenReturn(Long.valueOf(0));
		assertValidationExceptionThrown("Trade id cannot be less than or equal to zero", testTrade);
	}
	
	private void assertValidationExceptionThrown(String message, Trade trade) throws ValidationException {
		expectedEx.expect(ValidationException.class);
		expectedEx.expectMessage(message);
		tradeValidator.validate(trade);
	}
	
}