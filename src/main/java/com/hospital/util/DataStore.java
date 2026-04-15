package com.hospital.util;

import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Singleton DataStore — holds all in-memory data.
 * Uses ObservableList so JavaFX TableViews update automatically.
 */
public class DataStore {

    // --- Singleton pattern ---
    private static DataStore instance;

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    // Private constructor — prevents direct instantiation
    private DataStore() {
        seedSampleData();
    }

    // --- Observable lists (auto-notify JavaFX UI) ---
    private final ObservableList<Patient>     patients     = FXCollections.observableArrayList();
    private final ObservableList<Doctor>      doctors      = FXCollections.observableArrayList();
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    // Auto-incrementing ID counters
    private int patientCounter     = 1;
    private int doctorCounter      = 1;
    private int appointmentCounter = 1;

    // --- Patient operations ---
    public ObservableList<Patient> getPatients() { return patients; }

    public void addPatient(String name, int age, String ailment) {
        String id = "P" + String.format("%03d", patientCounter++);
        patients.add(new Patient(id, name, age, ailment));
    }

    public void updatePatient(String id, String name, int age, String ailment) {
        Patient patient = patients.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (patient != null) {
            patient.setName(name);
            patient.setAge(age);
            patient.setAilment(ailment);
        }
    }

    public void deletePatient(String id) {
        patients.removeIf(p -> p.getId().equals(id));
    }

    public boolean patientExists(String name) {
        return patients.stream().anyMatch(p -> p.getName().equalsIgnoreCase(name));
    }

    // --- Doctor operations ---
    public ObservableList<Doctor> getDoctors() { return doctors; }

    public void addDoctor(String name, int age, String specialization) {
        String id = "D" + String.format("%03d", doctorCounter++);
        doctors.add(new Doctor(id, name, age, specialization));
    }

    public void updateDoctor(String id, String name, int age, String specialization) {
        Doctor doctor = doctors.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
        if (doctor != null) {
            doctor.setName(name);
            doctor.setAge(age);
            doctor.setSpecialization(specialization);
        }
    }

    public void deleteDoctor(String id) {
        doctors.removeIf(d -> d.getId().equals(id));
    }

    public boolean doctorExists(String name) {
        return doctors.stream().anyMatch(d -> d.getName().equalsIgnoreCase(name));
    }

    // --- Appointment operations ---
    public ObservableList<Appointment> getAppointments() { return appointments; }

    public void addAppointment(Patient patient, Doctor doctor, String date) {
        String id = "A" + String.format("%03d", appointmentCounter++);
        appointments.add(new Appointment(id, patient, doctor, date));
    }

    public void updateAppointment(String id, Patient patient, Doctor doctor, String date) {
        Appointment appointment = appointments.stream().filter(a -> a.getAppointmentId().equals(id)).findFirst().orElse(null);
        if (appointment != null) {
            appointment.setPatient(patient);
            appointment.setDoctor(doctor);
            appointment.setDate(date);
        }
    }

    public void deleteAppointment(String id) {
        appointments.removeIf(a -> a.getAppointmentId().equals(id));
    }

    // --- Summary stats ---
    public int getTotalPatients()     { return patients.size(); }
    public int getTotalDoctors()      { return doctors.size(); }
    public int getTotalAppointments() { return appointments.size(); }

    // --- Seed some sample data so UI isn't empty on first launch ---
    private void seedSampleData() {
        addPatient("Arjun Sharma",   28, "Fever");
        addPatient("Priya Mehta",    35, "Diabetes");
        addPatient("Rahul Verma",    45, "Hypertension");

        addDoctor("Dr. Ananya Singh",  42, "Cardiology");
        addDoctor("Dr. Vikram Nair",   38, "Neurology");
        addDoctor("Dr. Sonal Kapoor",  35, "General Medicine");

        // Book one sample appointment
        addAppointment(patients.get(0), doctors.get(2), "2025-06-20");
    }
}
