package com.spring.DependencyInjection.ConstructorInjection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("email")
public class EmailNotificationService implements NotificationService{

	@Override
	public void send(String message) {
		System.out.println("Email Notification: " + message);
	}
}
