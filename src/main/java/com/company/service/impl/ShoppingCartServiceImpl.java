package com.company.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.company.exception.NotEnoughProductsInStockException;
import com.company.model.Product;
import com.company.repository.ProductRepository;
import com.company.service.ShoppingCartService;
import com.company.shoppingcart.CartItem;
import com.company.shoppingcart.PriceScanner;

/**
 * We store the element in the shopping cart in a map. The scope of the bean is session
 * so that the elements on it will be kept for all the duration of the session.
 * 
 * @author luca.godano
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductRepository productRepository;

    private Map<Product, Integer> products = new HashMap<>();

    @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * If product is in the map just increment quantity by 1.
     * If product is not in the map with, add it with quantity 1
     *
     * @param product
     */
    @Override
    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    /**
     * If product is in the map with quantity > 1, just decrement quantity by 1.
     * If product is in the map with quantity 1, remove it from map
     *
     * @param product
     */
    @Override
    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1)
                products.replace(product, products.get(product) - 1);
            else if (products.get(product) == 1) {
                products.remove(product);
            }
        }
    }

    /**
     * @return unmodifiable copy of the map
     */
    @Override
    public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    /**
     * Checkout will rollback if there is not enough of some product in stock
     *
     * @throws NotEnoughProductsInStockException
     */
    @Override
    public void checkout() throws NotEnoughProductsInStockException {
        Product product;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            // Refresh quantity for every product before checking
            product = productRepository.findOne(entry.getKey().getId());
            if (product.getQuantity() < entry.getValue())
                throw new NotEnoughProductsInStockException(product);
            entry.getKey().setQuantity(product.getQuantity() - entry.getValue());
        }
        productRepository.save(products.keySet());
        productRepository.flush();
        products.clear();
    }
    
    /**
     * THe total price of the cart is calculated by the class PriceScanner that
     * is an implementation of the Visitor pattern. Each item in the cart implements the
     * interface Scannable that means that it can be passed in input to the price scanner
     * for the total price calculation. That allow us to separate the logic of the 
     * price calculation from the items.
     */
    @Override
    public BigDecimal getTotal() {
    	
    	PriceScanner priceScanner = new PriceScanner();
    	
    	products.forEach( (k, v) -> scan(k, v, priceScanner));
    	return priceScanner.getTotalPrice();
    	
    }
    
    private void scan(CartItem item, Integer items, PriceScanner scanner) {
    	
    	for (int i = 1; i <= items; i++) {
    		scanner.scan(item);
    	}
    	
    }
}
