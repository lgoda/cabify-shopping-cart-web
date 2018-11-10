package com.company.shoppingcart;

import java.math.BigDecimal;

import com.company.shoppingcart.pricepolicy.DiscountPolicyEnum;

/**
 * CartItem is the generic interface that represent an Item on the cart.
 * It extends the interface scannable. The pattern is the visitor pattern 
 * that allow us to decouple the logic of the price calculation from the element itself.
 * @author luca.godano
 *
 */
public interface CartItem extends Scannable {
	BigDecimal getPrice();
    BigDecimal getPriceForQuantity(int quantity);
    String getCode();
    String getName();
    DiscountPolicyEnum getPricePolicy();
    String getPricePolicyData();
}
