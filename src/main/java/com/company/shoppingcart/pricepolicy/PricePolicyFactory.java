package com.company.shoppingcart.pricepolicy;

import com.company.shoppingcart.CartItem;

/**
 * Factory class that return the implementation of the Price Policy.
 * When we add a new price policy we can simply write a class that implement such a policy.
 * 
 * @author luca.godano
 *
 */
public final class PricePolicyFactory {


	private PricePolicyFactory() {
	}
	
	/**
	 * 
	 * @param pricePolicy - Value of the enum that determine the Price Policy
	 * @param cartItem - The Item on the cart to which we want to apply the price Policy
	 * @return
	 */
	public static CartItem getPricePolicy(DiscountPolicyEnum pricePolicy, CartItem cartItem) {
		switch (pricePolicy) {
			case DEGRESSIVE_PRICE_POLICY:
				return new DegressivePricePolicy(cartItem);
	
			case REGULAR_PRICE_POLICY:
				return new RegularPricePolicy(cartItem);
	
			case TWO_FOR_ONE_PRICE_POLICY:
				return new TwoForOnePricePolicy(cartItem);
			default:
				throw new IllegalArgumentException("Unknown Price Policy!");
		}
	}
}
