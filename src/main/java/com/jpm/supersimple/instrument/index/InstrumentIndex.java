package com.jpm.supersimple.instrument.index;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.jpm.supersimple.exceptions.ApplicationException;
import com.jpm.supersimple.exceptions.ValidationException;
import com.jpm.supersimple.instrument.Instrument;
import com.jpm.supersimple.instrument.InstrumentValidator;
import com.jpm.supersimple.instrument.Symbol;

public class InstrumentIndex {

	private static final Logger LOGGER = Logger.getLogger(InstrumentIndex.class.getName());

	final String GET_INSTRUMENT = "get instrument";
	final String GET_INSTRUMENT_PRICE = "get instrument price";
	final String UPDATE_INSTRUMENT = "update instrument";
	final String UPDATE_INSTRUMENT_PRICE = "update instrument price";

	Map<Symbol, Instrument> instrumentsIndex = new ConcurrentHashMap<Symbol, Instrument>();
	Map<Symbol, BigDecimal> priceIndex = new ConcurrentHashMap<Symbol, BigDecimal>();

	private final InstrumentIndexActionValidator indexActionValidator;
	private final InstrumentValidator instrumentValidator;

	public InstrumentIndex(InstrumentIndexActionValidator indexActionValidator, InstrumentValidator instrumentValidator) {
		this.indexActionValidator = indexActionValidator;
		this.instrumentValidator = instrumentValidator;
	}
	
	public void addInstrument(final Instrument instrument, BigDecimal lastPrice) throws ApplicationException {
		indexActionValidator.assertCanAddInstrumentWithPrice(instrumentsIndex, priceIndex, instrument, lastPrice);

		final Symbol symbol = instrument.getSymbol();
		instrumentsIndex.put(symbol, instrument);
		priceIndex.put(symbol, lastPrice);

		LOGGER.info("Added : " + instrument + " and price: " + lastPrice);
	}

	public void removeInstrument(Symbol symbol) throws ApplicationException {

		indexActionValidator.assertCanRemoveInstrument(instrumentsIndex, priceIndex, symbol);

		instrumentsIndex.remove(symbol);
		if (priceIndex.containsKey(priceIndex)) {
			priceIndex.remove(priceIndex);
		}
		LOGGER.info("Removed instrument with symbol: " + symbol.getCode());
	}

	public Instrument getInstrument(Symbol symbol) throws ApplicationException {
		indexActionValidator.assertInstrumentExists(instrumentsIndex, symbol, GET_INSTRUMENT);

		LOGGER.debug("Retrieved instrument with symbol: " + symbol.getCode());
		return instrumentsIndex.get(symbol);
	}

	public BigDecimal getPrice(Symbol symbol) throws ApplicationException {

		indexActionValidator.assertInstrumentExists(instrumentsIndex, symbol, GET_INSTRUMENT_PRICE);
		indexActionValidator.assertPriceExists(priceIndex, symbol, GET_INSTRUMENT_PRICE);

		BigDecimal price = priceIndex.get(symbol);
		LOGGER.debug("Retrieved price for symbol: " + symbol.getCode() + " having price: " + price);
		return price;
	}

	public void updateInstrument(Instrument instrument) throws ValidationException {
		instrumentValidator.validate(instrument);
		indexActionValidator.assertInstrumentExists(instrumentsIndex, instrument.getSymbol(), UPDATE_INSTRUMENT);

		LOGGER.info("Updated instrument : " + instrument);
		instrumentsIndex.put(instrument.getSymbol(), instrument);
	}

	public void updatePrice(Symbol symbol, BigDecimal newPrice) throws ValidationException {
		indexActionValidator.assertInstrumentExists(instrumentsIndex, symbol, UPDATE_INSTRUMENT_PRICE);

		LOGGER.debug("Updated price for symbol: " + symbol.getCode() + " with price: " + newPrice);
		priceIndex.put(symbol, newPrice);
	}

	public Collection<BigDecimal> getPrices() {
		return Collections.unmodifiableCollection(priceIndex.values());
	}
	
}