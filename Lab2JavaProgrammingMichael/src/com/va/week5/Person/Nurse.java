package com.va.week5.Person;

public class Nurse extends Person{
	
	protected String department;
	protected int shift;
	
	public Nurse (int personId, String name, int age, String department, int shift) {
		super(personId, name, age);
		this.department = department;
		this.shift = shift;
	}
	
	public void assistPatient() {
		System.out.println("Hello, how may I assist you?");
	}
	
	@Override
	public void performDuties() {
		super.performDuties();
		System.out.println("I am assisting the doctors and patients.\n"
				+ "My department is " + department + ".\n"
						+ "My shift is " + shift + " hours long.");
	}

}
