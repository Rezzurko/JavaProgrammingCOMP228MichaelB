package com.va.week5.Person;

public class Surgeon extends Doctor{
	
	protected String surgeryType;
	protected int operatingRoom;
	
	public Surgeon (int personId, String name, int age, String specialization, int consultationFee, String surgeryType, int operatingRoom) {
		super(personId, name, age, specialization, consultationFee);
		this.surgeryType = surgeryType;
		this.operatingRoom = operatingRoom;
	}
	
	public void performSurgery () {
		System.out.println("My patient seems to need surgery");
	}
	
	@Override
	public void performDuties() {
		super.performDuties();
		System.out.println("I am operating on my patients.\n"
								+ "My surgery type is " + surgeryType + ".\n"
										+ "I am operating in room number " + operatingRoom + ".");
	}
	
}
