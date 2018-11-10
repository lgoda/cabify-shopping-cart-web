package com.company.shoppingcart.pricepolicy;

import java.math.BigDecimal;

import com.company.shoppingcart.CartItem;
import com.company.shoppingcart.Scanner;


/**
 * The regular Price policy is the standard policy that
 * does not apply any discount
 * @author luca.godano
 *
 */
public class RegularPricePolicy implements CartItem{
	
	private final CartItem baseItem;

    public RegularPricePolicy(CartItem baseItem) {
        this.baseItem = baseItem;
    }
	
	@Override
	public void accept(Scanner scanner) {
		scanner.scan(this);
	}

	@Override
	public BigDecimal getPrice() {
		return baseItem.getPrice();
	}

	@Override
	public BigDecimal getPriceForQuantity(int quantity) {
		return baseItem.getPriceForQuantity(quantity);
	}

	@Override
	public String getCode() {
		return baseItem.getCode();
	}

	@Override
	public String getName() {
		return baseItem.getName();
	}
	
	@Override
	public DiscountPolicyEnum getPricePolicy() {
		return baseItem.getPricePolicy();
	}
	@Override
	public String getPricePolicyData() {
		return baseItem.getPricePolicyData();
	}
}
