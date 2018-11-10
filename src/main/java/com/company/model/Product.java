package com.company.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import com.company.shoppingcart.CartItem;
import com.company.shoppingcart.Scanner;
import com.company.shoppingcart.pricepolicy.DiscountPolicyEnum;

@Entity
@Table(name = "product")
public class Product implements CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	@Length(min = 3, message = "*Name must have at least 5 characters")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "quantity", nullable = false)
	@Min(value = 0, message = "*Quantity has to be non negative number")
	private Integer quantity;

	@Column(name = "price", nullable = false)
	@DecimalMin(value = "0.00", message = "*Price has to be non negative number")
	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	@Column(name = "price_policy", nullable=true)
	private DiscountPolicyEnum pricePolicy;

	@Column(name = "code", nullable = false, unique = true)
	@Length(min = 3, message = "*Code must have at least 3 characters")
	private String code;
	
	@Column(name = "price_policy_data", nullable = true, unique = false)
	private String pricePolicyData;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal unitPrice) {
		this.price = unitPrice;
	}

	public DiscountPolicyEnum getPricePolicy() {
		return pricePolicy;
	}

	public void setPricePolicy(DiscountPolicyEnum pricePolicy) {
		this.pricePolicy = pricePolicy;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Product product = (Product) o;

		return id.equals(product.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public void accept(Scanner scanner) {
		scanner.scan(this);

	}
	
	@Override
	public BigDecimal getPriceForQuantity(int quantity) {
		
		return price.multiply(BigDecimal.valueOf(quantity));
	}

	public String getPricePolicyData() {
		return pricePolicyData;
	}

	public void setPricePolicyData(String pricePolicyData) {
		this.pricePolicyData = pricePolicyData;
	}
	
	
}
