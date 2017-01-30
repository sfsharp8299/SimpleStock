package com.jpm.supersimple.instrument;

import java.math.BigDecimal;

public class Instrument {

	private final InstrumentType instrumentType;
	private final Symbol symbol;
	private BigDecimal parValue;
	private BigDecimal fixedDividend;
	private BigDecimal lastDividend;
	
	public Instrument(InstrumentBuilder instrumentBuilder) {
		instrumentType =  instrumentBuilder.getInstrumentType();
		symbol = instrumentBuilder.getSymbol();
		parValue = instrumentBuilder.getParValue();
		fixedDividend = instrumentBuilder.getFixedDividend();
		lastDividend = instrumentBuilder.getLastDividend();
	}

	public InstrumentType getInstrumentType() {
		return instrumentType;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	public BigDecimal getFixedDividend() {
		return fixedDividend;
	}

	public BigDecimal getLastDividend() {
		return lastDividend;
	}

	@Override
	public int hashCode() {
		return symbol.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instrument other = (Instrument) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Instrument [instrumentType=" + instrumentType + ", symbol=" + symbol + ", parValue=" + parValue
				+ ", fixedDividend=" + fixedDividend + ", lastDividend=" + lastDividend + "]";
	}

}