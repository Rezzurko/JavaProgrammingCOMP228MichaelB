package com.va.week5.Person;

public class EmergencyDoctor extends Doctor implements MedicalProfessional, Billable {
	
	public EmergencyDoctor (int personId, String name, int age, String specialization, int consultationFee) {
		super(personId, name, age, specialization, consultationFee);
	}
	
	public void handleEmergency () {
		System.out.println("My patients need serious attention");
	}
	
    public void prescribeMedication() {
        System.out.println("Prescribing medication.");
    }

    public void generateBill() {
        System.out.println("Generating the medical bill.");
    }
	
	@Override
	public void performDuties() {
		super.performDuties();
		System.out.println("I am attending to the patients who are having emergencies.");
	}
	
}
