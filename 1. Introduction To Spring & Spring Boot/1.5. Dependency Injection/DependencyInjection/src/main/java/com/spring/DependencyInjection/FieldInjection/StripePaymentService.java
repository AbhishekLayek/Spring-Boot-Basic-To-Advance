package com.spring.DependencyInjection.FieldInjection;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentService implements PaymentService{

	@Override
	public void pay() {
		System.out.println("Paying Using Stripe");
	}
}
