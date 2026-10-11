package com.va.week5.Person;

public class Doctor extends Person{
	
	protected String specialization;
	protected int consultationFee;
	
	public Doctor (int personId, String name, int age, String specialization, int consultationFee) {
		super(personId, name, age);
		this.specialization = specialization;
		this.consultationFee = consultationFee;
	}
	
	public void diagnosePatient () {
		System.out.println("My patient seems to be sick");
	}
	
	@Override
	public void performDuties() {
		super.performDuties();
		System.out.println("I am currently attending to my patients.\n"
				+ "My specialization is " + specialization + ".\n"
						+ "My consultation fee is $" + consultationFee + ".");
	}
	
}
