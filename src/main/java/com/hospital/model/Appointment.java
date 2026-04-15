package com.hospital.model;

/**
 * Appointment model class.
 * Links a Patient and a Doctor on a specific date.
 */
public class Appointment {

    private String appointmentId;
    private Patient patient;
    private Doctor  doctor;
    private String  date;       // e.g. "2025-06-15"
    private String  status;     // "Scheduled" / "Completed" / "Cancelled"

    // Constructor
    public Appointment(String appointmentId, Patient patient, Doctor doctor, String date) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor  = doctor;
        this.date    = date;
        this.status  = "Scheduled"; // default status
    }

    // --- Getters and Setters ---

    public String getAppointmentId() { return appointmentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // --- Convenience methods for TableView binding ---

    public String getPatientName() { return patient != null ? patient.getName() : ""; }
    public String getDoctorName()  { return doctor  != null ? doctor.getName()  : ""; }
    public String getDoctorSpecialization() { return doctor != null ? doctor.getSpecialization() : ""; }

    @Override
    public String toString() {
        return "Appointment [ID: " + appointmentId
                + " | Patient: " + (patient != null ? patient.getName() : "N/A")
                + " | Doctor: "  + (doctor  != null ? doctor.getName()  : "N/A")
                + " | Date: " + date + " | Status: " + status + "]";
    }
}
