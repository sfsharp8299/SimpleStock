package com.jpm.supersimple;

import com.jpm.supersimple.exceptions.ApplicationException;
import com.jpm.supersimple.stockmarket.StockMarketFactory;

public class SuperSimple {

	public static void main(String[] args) throws ApplicationException {

		StockMarketFactory smFactory = new StockMarketFactory();
		smFactory.buildStockMarket();

		//TODO operate the stock market
	}

}
