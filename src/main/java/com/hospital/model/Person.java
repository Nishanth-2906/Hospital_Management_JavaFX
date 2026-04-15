package com.hospital.model;

/**
 * Abstract base class demonstrating ABSTRACTION and ENCAPSULATION.
 * Patient and Doctor inherit from this class (INHERITANCE).
 */
public abstract class Person {

    // Private fields — Encapsulation
    private String id;
    private String name;
    private int age;

    // Constructor
    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // --- Getters and Setters (Encapsulation) ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    /**
     * Abstract method — subclasses MUST provide their own implementation.
     * This is POLYMORPHISM via method overriding.
     */
    public abstract String getDetails();

    @Override
    public String toString() {
        return getDetails();
    }
}
