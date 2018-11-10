package com.company.shoppingcart.pricepolicy;

import java.math.BigDecimal;

import com.company.shoppingcart.CartItem;
import com.company.shoppingcart.Scanner;

/**
 * Two for One Price Policy.
 * 
 * @author luca.godano
 *
 */
public class TwoForOnePricePolicy extends RegularPricePolicy {

	public TwoForOnePricePolicy(CartItem cartItem) {
		super(cartItem);
	}

	@Override
	public void accept(Scanner scanner) {
		scanner.scan(this);
	}

	@Override
	public BigDecimal getPriceForQuantity(int quantity) {
		BigDecimal price = getPrice();
		int occurrencies = quantity / 2;
		BigDecimal tot = price.multiply(BigDecimal.valueOf(occurrencies))
				.add(price.multiply(BigDecimal.valueOf(quantity - (2 * occurrencies))));
		return tot;
	}

}
