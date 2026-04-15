package com.hospital.model;

/**
 * Patient class extends Person — demonstrating INHERITANCE.
 * Overrides getDetails() — demonstrating POLYMORPHISM.
 */
public class Patient extends Person {

    // Patient-specific field
    private String ailment;

    // Constructor
    public Patient(String id, String name, int age, String ailment) {
        super(id, name, age); // Call parent constructor
        this.ailment = ailment;
    }

    // Convenience constructor (ailment optional)
    public Patient(String id, String name, int age) {
        super(id, name, age);
        this.ailment = "Not specified";
    }

    // Getter / Setter
    public String getAilment() { return ailment; }
    public void setAilment(String ailment) { this.ailment = ailment; }

    /**
     * Polymorphic override of getDetails().
     */
    @Override
    public String getDetails() {
        return "Patient [ID: " + getId() + " | Name: " + getName()
                + " | Age: " + getAge() + " | Ailment: " + ailment + "]";
    }
}
