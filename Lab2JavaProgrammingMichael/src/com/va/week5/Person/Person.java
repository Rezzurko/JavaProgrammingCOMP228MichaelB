package com.va.week5.Person;

public class Person {
	
	int personId, age;
	String name;
	
	public Person (int personId, String name, int age) {
		this.personId = personId;
		this.name = name;
		this.age = age;
	}
	
	public void performDuties() {
		System.out.println("Hello, my name is " + name + ". \nI am working and doing my tasks at the hospital.");
	}
	
	public void displayInfo() {
		System.out.println("Name: " + name + "\nPerson ID: " + personId + "\nAge: " + age);
	}
	
	
}
