package com.company.shoppingcart;


/**
 * Each item to which we want to use with the visitor pattern
 * must implement this interface. This allow us to separate the logic
 * of the price calculation from the product itself.
 * @author luca.godano
 *
 */
public interface Scannable {
	void accept(Scanner scanner);
}
