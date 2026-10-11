package com.va.week5.Person;

public class MainDriver {
	
	public static void main (String args[]) {
		System.out.println("===============================\n\tHospital System\n===============================\n");
		
		System.out.println("==========================\nPerson\n==========================");
		
		Person per;
		
		per = new Person(100, "Michael", 18);
		per.performDuties();
		per.displayInfo();
		
		System.out.println("\n==========================\nDoctor\n==========================");
		Doctor doc = new Doctor(105, "Gus", 20, "Medicine", 80);
		per = doc;
		per.performDuties();
		doc.diagnosePatient();
		per.displayInfo();
		
		System.out.println("\n==========================\nNurse\n==========================");
		Nurse nur = new Nurse(110, "Justin", 19, "Intensive Care", 8);
		per = nur;
		per.performDuties();
		nur.assistPatient();
		per.displayInfo();
		
		System.out.println("\n==========================\nSurgeon\n==========================");
		Surgeon sur = new Surgeon(115, "Frank", 23, "Neuro Surgery", 150, "Brain Surgery", 301);
		per = sur;
		per.performDuties();
		sur.performSurgery();
		per.displayInfo();
		
		System.out.println("\n==========================\nEmergency Doctor\n==========================");
		EmergencyDoctor emd = new EmergencyDoctor(120, "Simone", 19, "All types", 250);
		per = emd;
		per.performDuties();
		emd.handleEmergency();
		emd.prescribeMedication();
		emd.generateBill();
		per.displayInfo();

	}
}
