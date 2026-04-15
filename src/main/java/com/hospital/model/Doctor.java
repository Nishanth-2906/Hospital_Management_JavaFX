package com.hospital.model;

/**
 * Doctor class extends Person — demonstrating INHERITANCE.
 * Overrides getDetails() — demonstrating POLYMORPHISM.
 */
public class Doctor extends Person {

    // Doctor-specific field
    private String specialization;

    // Constructor
    public Doctor(String id, String name, int age, String specialization) {
        super(id, name, age);
        this.specialization = specialization;
    }

    // Getter / Setter
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    /**
     * Polymorphic override of getDetails().
     */
    @Override
    public String getDetails() {
        return "Doctor [ID: " + getId() + " | Name: " + getName()
                + " | Age: " + getAge() + " | Specialization: " + specialization + "]";
    }
}
