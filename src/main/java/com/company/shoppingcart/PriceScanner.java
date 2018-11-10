package com.company.shoppingcart;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.company.shoppingcart.pricepolicy.PricePolicyFactory;


/**
 * A price scanner is the implementation of the Visitor pattern for the price calculation.
 * This allow us to separate the logic of the price calculation from the
 * product itself.
 * 
 * @author luca.godano
 *
 */
public class PriceScanner implements Scanner {

	private BigDecimal totalPrice = BigDecimal.ZERO;

	private Map<String, AtomicInteger> items;

	public PriceScanner() {
		items = new HashMap<String, AtomicInteger>();
	}

	@Override
	public void scan(CartItem product) {
		CartItem item = PricePolicyFactory.getPricePolicy(product.getPricePolicy(), product);
		
		int elementsInBasket = 0;
		
		BigDecimal toSubtract = BigDecimal.ZERO;
		if (items.containsKey(product.getCode())) {
			elementsInBasket = items.get(product.getCode()).get();
			toSubtract = item.getPriceForQuantity(elementsInBasket);
			items.get(product.getCode()).incrementAndGet();
		} else {
			items.put(product.getCode(), new AtomicInteger(1));
		}

		BigDecimal toAdd = item.getPriceForQuantity(items.get(product.getCode()).get());

		totalPrice = totalPrice.subtract(toSubtract).add(toAdd);

	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
}
