package com.jpm.supersimple.calculators;

import java.math.BigDecimal;

public class PriceQuantity {

	private final BigDecimal price;
	private final Long quantity;
	
	public PriceQuantity(BigDecimal price, Long quantity) {
		this.price = price;
		this.quantity = quantity;
	}

	public final BigDecimal getPrice() {
		return price;
	}

	public final Long getQuantity() {
		return quantity;
	}
	
}
