package com.spring.TestApp.services.Impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring.TestApp.services.DataService;

@Service
@Profile("dev")
public class DevDataServiceImpl implements DataService{
	@Override
	public String getData() {
		return "DEV Data";
	}

}