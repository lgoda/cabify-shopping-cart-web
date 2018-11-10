package com.company.shoppingcart.pricepolicy;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;

import com.company.shoppingcart.CartItem;
import com.company.shoppingcart.Scanner;

/**
 * Degressive price policy is the policy that reduce the price of a single item
 * if the total number of items exceed or is equal a defined threshold.
 * 
 * @author luca.godano
 *
 */
public class DegressivePricePolicy extends RegularPricePolicy {

	private int thresholdQuantity;
	private BigDecimal reducedUnitPrice;

	public DegressivePricePolicy(CartItem baseItem) {
		super(baseItem);
		if (baseItem.getPricePolicyData() == null) {
			throw new RuntimeException("Missing Price Policy Data");
		}

		JsonParser jsonParser = new BasicJsonParser();
		Map<String, Object> jsonMap = jsonParser.parseMap(baseItem.getPricePolicyData());
		this.thresholdQuantity = Integer.valueOf(jsonMap.get("thresholdQuantity").toString());
		this.reducedUnitPrice = BigDecimal.valueOf(new Integer(jsonMap.get("reducedUnitPrice").toString()));
		;
	}

	@Override
	public void accept(Scanner scanner) {
		scanner.scan(this);
	}

	@Override
	public BigDecimal getPriceForQuantity(int quantity) {
		if (quantity < thresholdQuantity) {
			return super.getPriceForQuantity(quantity);
		} else {
			return reducedUnitPrice.multiply(BigDecimal.valueOf(quantity));
		}
	}

}
