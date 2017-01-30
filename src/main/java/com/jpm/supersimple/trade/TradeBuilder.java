package com.jpm.supersimple.trade;

import java.math.BigDecimal;
import java.util.Date;

import com.jpm.supersimple.exceptions.ValidationException;
import com.jpm.supersimple.instrument.Instrument;

public class TradeBuilder {
	
	private  Long tradeId;
	private Date tradeDate;
	private  Instrument instrument;
	private  Long quantity;
	private  TradeType tradeType;
	private  BigDecimal price;
	
	private TradeValidator tradeValidator;
	
	public TradeBuilder(final TradeIdGenerator tradeIdGenerator, TradeValidator tradeValidator, final Instrument instrument, final Long quantity, final TradeType tradeType, final BigDecimal price) {
		this.tradeValidator = tradeValidator;
		this.tradeId = tradeIdGenerator.getNextTradeId();
		this.tradeDate = new Date();

		this.instrument = instrument;
		this.quantity = quantity;
		this.tradeType = tradeType;
		this.price = price;
	}
	
    public Trade build() throws ValidationException {
    	Trade trade = new Trade(this);
    	tradeValidator.validate(trade);
        return trade;
    }

	public Long getTradeId() {
		return tradeId;
	}

	public Date getTradeDate() {
		return tradeDate;
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
    
}
