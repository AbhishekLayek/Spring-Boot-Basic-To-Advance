package com.spring.SpringMVC_PresentationLayer.DTO;

import java.time.LocalDate;

public class Employee {
	private long id;
	private String name;
	private String email;
	private int age;
	private LocalDate dateOfJoining;
	private boolean isActive;
	
	public Employee() {
	}
	public Employee(long id, String name, String email, int age, LocalDate dateOfJoining, boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
		this.dateOfJoining = dateOfJoining;
		this.isActive = isActive;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
