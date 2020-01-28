package com.cdk.shopping.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdk.shopping.cart.model.response.DiscountResponse;
import com.cdk.shopping.cart.service.ShoppingCartService;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	@Autowired
	ShoppingCartService shoppingCartService;
	
	@GetMapping( value = {"/calculateBillAmount/{customerType}/{purchaseAmount}"}, produces = { "application/json"})
	public ResponseEntity<DiscountResponse> calculateBillAmount
			(@PathVariable (value = "customerType")String customerType, @PathVariable (value = "purchaseAmount")String purchaseAmount) throws Exception
	{
		
		DiscountResponse discountResponse = shoppingCartService.calculateBillAmount(customerType, purchaseAmount);
		
		return new ResponseEntity<DiscountResponse>(discountResponse, HttpStatus.OK);
	}
}
