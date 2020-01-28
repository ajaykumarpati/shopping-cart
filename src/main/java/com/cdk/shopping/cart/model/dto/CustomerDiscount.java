package com.cdk.shopping.cart.model.dto;

import java.util.List;

public class CustomerDiscount {

	private String customerType;
	private List<Discount> discounts;

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

}
