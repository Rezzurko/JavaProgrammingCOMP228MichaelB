package application;

import java.time.LocalDate;

public class Patient {

    private int patientId;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String diagnosis;
    private LocalDate admissionDate;

    public Patient(int patientId, String name, int age, String gender, String phone, String diagnosis, LocalDate admissionDate) {

        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
    }

    public int getPatientId() { 
    	return patientId; 
    }
    
    public String getName() {
    	return name;
    }
    
    public int getAge() {
    	return age;
    }
    
    public String getGender() {
    	return gender;
    }
    
    public String getPhone() {
    	return phone;
    }
    
    public String getDiagnosis() {
    	return diagnosis;
    }
    
    public LocalDate getAdmissionDate() { 
    	return admissionDate; 
    }
}
