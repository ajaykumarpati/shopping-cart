package com.cdk.shopping.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdk.shopping.cart.loader.DiscountDataLoader;
import com.cdk.shopping.cart.model.dto.CustomerDiscount;
import com.cdk.shopping.cart.model.dto.Discount;
import com.cdk.shopping.cart.model.response.DiscountResponse;

@Service
public class ShoppingCartService {

	@Autowired
	DiscountDataLoader discountDataLoader;

	public DiscountResponse calculateBillAmount(String customerType, String purchaseAmount) throws Exception 
	{
		DiscountResponse discountResponse = new DiscountResponse();

		List<CustomerDiscount> customerDiscounts = discountDataLoader.loadDiscountData();

		List<Discount> discounts = customerDiscounts.stream()
				.filter(customerDiscount -> customerType.equals(customerDiscount.getCustomerType())).findFirst()
				.orElseThrow(Exception::new).getDiscounts();

		discounts.sort((discount1, discount2) -> {
			int lowerLimit1 = Integer.parseInt(discount1.getDiscountSlab().getLowerLimit());
			int lowerLimit2 = Integer.parseInt(discount2.getDiscountSlab().getLowerLimit());
			return lowerLimit1 - lowerLimit2;
		});

		int  purchase = Integer.parseInt(purchaseAmount);
		int billAmount = purchase;
		
		for(Discount discount:discounts)
		{
			int percentage = Integer.parseInt(discount.getDiscountPercentage());
			int lower = Integer.parseInt(discount.getDiscountSlab().getLowerLimit());
			
			if(discount.getDiscountSlab().getUpperLimit() == null ||
					purchase <= Integer.parseInt(discount.getDiscountSlab().getUpperLimit()))
			{
				billAmount = billAmount - (((purchase - lower) * percentage )/100);
				break;
			}
			else
			{
				int upper = Integer.parseInt(discount.getDiscountSlab().getUpperLimit());
				billAmount = billAmount - (((upper - lower) * percentage )/100);
			}
		}
		
		discountResponse.setBillAmount(String.valueOf(billAmount));
		discountResponse.setCustomerType(customerType);
		discountResponse.setPurchaseAmount(purchaseAmount);
		
		return discountResponse;
	}
}
