package com.company.service;

import java.math.BigDecimal;
import java.util.Map;

import com.company.exception.NotEnoughProductsInStockException;
import com.company.model.Product;

public interface ShoppingCartService {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();
}
