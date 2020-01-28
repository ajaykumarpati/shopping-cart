package com.cdk.shopping.cart.model.dto;

public class Discount {

	private String discountPercentage;
	private DiscountSlab discountSlab;

	public String getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public DiscountSlab getDiscountSlab() {
		return discountSlab;
	}

	public void setDiscountSlab(DiscountSlab discountSlab) {
		this.discountSlab = discountSlab;
	}

}
