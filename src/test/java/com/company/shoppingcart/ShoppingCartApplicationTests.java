package com.company.shoppingcart;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.model.Product;
import com.company.repository.ProductRepository;
import com.company.service.ShoppingCartService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartApplicationTests {
	
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	ProductRepository productRepository;
	
	
	@Test
	public void voucher_tshirt_mug_tot_32_and_50() {
		
		Product p1 = productRepository.findByCode("VOUCHER").get();
		Product p2 = productRepository.findByCode("TSHIRT").get();
		Product p3 = productRepository.findByCode("MUG").get();
		
		shoppingCartService.addProduct(p1);
		shoppingCartService.addProduct(p2);
		shoppingCartService.addProduct(p3);
				
		BigDecimal expectedTotal = BigDecimal.valueOf(32.50);
		BigDecimal actualTotal = shoppingCartService.getTotal();
		
		Assert.assertTrue("Assert Total is 32.50", expectedTotal.compareTo(actualTotal) == 0);
		
	}
	
	@Test
	public void vouher_tshirt_voucher_tot_25() {
		Product p1 = productRepository.findByCode("VOUCHER").get();
		Product p2 = productRepository.findByCode("TSHIRT").get();
		Product p3 = productRepository.findByCode("VOUCHER").get();
		
		shoppingCartService.addProduct(p1);
		shoppingCartService.addProduct(p2);
		shoppingCartService.addProduct(p3);
				
		BigDecimal expectedTotal = BigDecimal.valueOf(25.00);
		BigDecimal actualTotal = shoppingCartService.getTotal();
		
		Assert.assertTrue("Assert Total is 25.00", expectedTotal.compareTo(actualTotal) == 0);
	}
	
	@Test
	public void tshirt_tshirt_tshirt_voucher_tshirt_tot_81() {
		Product p1 = productRepository.findByCode("TSHIRT").get();
		Product p2 = productRepository.findByCode("TSHIRT").get();
		Product p3 = productRepository.findByCode("TSHIRT").get();
		Product p4 = productRepository.findByCode("VOUCHER").get();
		Product p5 = productRepository.findByCode("TSHIRT").get();
		
		shoppingCartService.addProduct(p1);
		shoppingCartService.addProduct(p2);
		shoppingCartService.addProduct(p3);
		shoppingCartService.addProduct(p4);
		shoppingCartService.addProduct(p5);
				
		BigDecimal expectedTotal = BigDecimal.valueOf(81.00);
		BigDecimal actualTotal = shoppingCartService.getTotal();
		
		Assert.assertTrue("Assert Total is 81.00", expectedTotal.compareTo(actualTotal) == 0);
	}

	@Test
	public void voucher_tshirt_voucher_voucher_mug_tshirt_tshirt_tot_74_and_50() {
		Product p1 = productRepository.findByCode("VOUCHER").get();
		Product p2 = productRepository.findByCode("TSHIRT").get();
		Product p3 = productRepository.findByCode("VOUCHER").get();
		Product p4 = productRepository.findByCode("VOUCHER").get();
		Product p5 = productRepository.findByCode("MUG").get();
		Product p6 = productRepository.findByCode("TSHIRT").get();
		Product p7 = productRepository.findByCode("TSHIRT").get();
		
		shoppingCartService.addProduct(p1);
		shoppingCartService.addProduct(p2);
		shoppingCartService.addProduct(p3);
		shoppingCartService.addProduct(p4);
		shoppingCartService.addProduct(p5);
		shoppingCartService.addProduct(p6);
		shoppingCartService.addProduct(p7);
				
		BigDecimal expectedTotal = BigDecimal.valueOf(74.50);
		BigDecimal actualTotal = shoppingCartService.getTotal();
		
		Assert.assertTrue("Assert Total is 74.50", expectedTotal.compareTo(actualTotal) == 0);
	}

}
