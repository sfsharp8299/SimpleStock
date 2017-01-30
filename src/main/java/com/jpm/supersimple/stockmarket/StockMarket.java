package com.jpm.supersimple.stockmarket;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.jpm.supersimple.calculators.Calculator;
import com.jpm.supersimple.calculators.CalculatorFactory;
import com.jpm.supersimple.calculators.PriceQuantity;
import com.jpm.supersimple.exceptions.ApplicationException;
import com.jpm.supersimple.exceptions.ValidationException;
import com.jpm.supersimple.instrument.Instrument;
import com.jpm.supersimple.instrument.Symbol;
import com.jpm.supersimple.instrument.index.InstrumentIndex;
import com.jpm.supersimple.trade.Trade;
import com.jpm.supersimple.trade.register.TradeRegister;
import com.jpm.supersimple.utils.TimeUtils;

public class StockMarket {

	private final InstrumentIndex index;
	private final TradeRegister tradeRegister;
	private final CalculatorFactory calculatorFactory;

	public StockMarket(InstrumentIndex index, TradeRegister tradeRegister, CalculatorFactory calculatorFactory) {
		this.index = index;
		this.tradeRegister = tradeRegister;
		this.calculatorFactory = calculatorFactory;
	}

	public void addInstrument(Instrument instrument, BigDecimal previousPrice) throws ApplicationException {
		index.addInstrument(instrument, previousPrice);
	}

	public Instrument getInstrument(Symbol symbol) throws ApplicationException {
		return index.getInstrument(symbol);
	}
	
	public BigDecimal getInstrumentPrice(Symbol symbol) throws ApplicationException {
		return index.getPrice(symbol);
	}
	
	public void addTrade(Trade trade) throws ValidationException {
		index.updatePrice(trade.getInstrument().getSymbol(), trade.getPrice());
		tradeRegister.addTrade(trade);
	}

	public BigDecimal calculateDividendYield(Symbol symbol) throws ApplicationException {
		final Instrument instrument = index.getInstrument(symbol);
		final BigDecimal price = index.getPrice(symbol);
		final Calculator createDividendYieldCalculator = calculatorFactory.createDividendYieldCalculator(
				instrument.getInstrumentType(), instrument.getLastDividend(), instrument.getFixedDividend(),
				instrument.getParValue(), price);
		return createDividendYieldCalculator.calculate();
	}

	public BigDecimal calculatePERation(Symbol symbol) throws Exception {
		final Instrument instrument = index.getInstrument(symbol);
		final BigDecimal price = index.getPrice(symbol);
		final BigDecimal lastDividend = instrument.getLastDividend();
		final Calculator peRatioCalculator = calculatorFactory.createPERatioCalculator(lastDividend, price);
		return peRatioCalculator.calculate();
	}

	public BigDecimal calculateVolumeWeightedPrice(Symbol symbol) throws ApplicationException {
		final Instrument instrument = index.getInstrument(symbol);
		final List<Trade> lastTrades = tradeRegister.getLastTrades(TimeUtils.getTimeMinutesAgo(15), instrument);

		final List<PriceQuantity> priceQuantities = lastTrades
				.stream()
				.map(p -> new PriceQuantity(p.getPrice(), p.getQuantity()))
				.collect(Collectors.toList());
		
		Calculator shareIndexMeanCalculator = calculatorFactory.createVolumeWeightedPriceCalculator(priceQuantities);
		return shareIndexMeanCalculator.calculate();
	}

	public BigDecimal calculateShareIndexMean() throws ValidationException {
		final Collection<BigDecimal> prices = index.getPrices();
		Calculator shareIndexMeanCalculator = calculatorFactory.createShareIndexMeanCalculator(prices);
		return shareIndexMeanCalculator.calculate();
	}

}
