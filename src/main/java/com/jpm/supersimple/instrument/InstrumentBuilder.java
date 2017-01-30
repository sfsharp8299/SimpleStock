package com.jpm.supersimple.instrument;

import java.math.BigDecimal;

import com.jpm.supersimple.exceptions.ValidationException;

public class InstrumentBuilder {

	private InstrumentType instrumentType;
	private Symbol symbol;
	private BigDecimal parValue;
	private BigDecimal fixedDividend; 
	private BigDecimal lastDividend;
	
	private InstrumentValidator instrumentValidator;
	
	public InstrumentBuilder(final InstrumentValidator instrumentValidator) {
		this.instrumentValidator = instrumentValidator;
	}
	
    public Instrument build() throws ValidationException {
    	Instrument instrument =  new Instrument(this);
        instrumentValidator.validate(instrument);
        return instrument;
    }

    public InstrumentBuilder instrumentType(final InstrumentType instrumentType) {
    	this.instrumentType = instrumentType;
    	return this;
    }
    
    public InstrumentBuilder symbol(final Symbol symbol) {
    	this.symbol = symbol;
    	return this;
    }
    
	public InstrumentBuilder parValue(BigDecimal parValue) {
		this.parValue = parValue;
		return this;
	}
	
	public InstrumentBuilder fixedDividend(BigDecimal fixedDividend) {
		this.fixedDividend = fixedDividend;
		return this;
	}
	
	public InstrumentBuilder lastDividend(BigDecimal lastDividend) {
		this.lastDividend = lastDividend;
		return this;
	}
	    
	public final InstrumentType getInstrumentType() {
		return instrumentType;
	}

	public final Symbol getSymbol() {
		return symbol;
	}

	public final BigDecimal getParValue() {
		return parValue;
	}

	public final BigDecimal getFixedDividend() {
		return fixedDividend;
	}

	public final BigDecimal getLastDividend() {
		return lastDividend;
	}
}
