package com.spring.DependencyInjection.FieldInjection;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
public class RazorpayPaymentService implements PaymentService{

	@Override
	public void pay() {
		System.out.println("Paying Using Razorpay");
	}
}
