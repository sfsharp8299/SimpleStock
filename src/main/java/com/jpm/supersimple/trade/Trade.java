package com.jpm.supersimple.trade;

import java.math.BigDecimal;
import java.util.Date;

import com.jpm.supersimple.instrument.Instrument;

public class Trade {

	private final Long tradeId;
	private final Instrument instrument;
	private final Long quantity;
	private final TradeType tradeType;
	private final BigDecimal price;
	private final Date tradeDate;
	
	public Trade(final TradeBuilder tradeBuilder) {
		this.tradeId = tradeBuilder.getTradeId();
		this.tradeDate = tradeBuilder.getTradeDate();
		this.instrument = tradeBuilder.getInstrument();
		this.quantity = tradeBuilder.getQuantity();
		this.tradeType = tradeBuilder.getTradeType();
		this.price = tradeBuilder.getPrice();
	}

	public Long getTradeId() {
		return tradeId;
	}
	
	public Instrument getInstrument() {
		return instrument;
	}

	public Long getQuantity() {
		return quantity;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", instrument=" + instrument.getSymbol() + ", quantity=" + quantity + ", tradeType="
				+ tradeType + ", price=" + price + ", tradeDate=" + tradeDate + "]";
	}
	
}
