package com.spring.Beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") // @Scope annotation is used to define the scope of the beans like singleton(only one object will be created), prototype(everytime new object will be created) etc.
public class BeanScopes {
	
}
