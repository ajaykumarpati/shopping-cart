package com.cdk.shopping.cart.loader;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdk.shopping.cart.model.dto.CustomerDiscount;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DiscountDataLoader {

	private static final String FILE_NAME = "/DiscountSlabs.json";
	
	@Autowired
	ObjectMapper objectMapper;
	
	public List<CustomerDiscount> loadDiscountData() throws Exception
	{
		File file = new File(getClass().getResource(FILE_NAME).getFile());
		
		List<CustomerDiscount> customerDiscounts = Arrays.asList(objectMapper.readValue(file, CustomerDiscount[].class));
		
		return customerDiscounts;
	}
}
