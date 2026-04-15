package com.hospital.controller;

import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML private TabPane tabPane;
    @FXML private TableView<Patient> patientTable;
    @FXML private TableView<Doctor> doctorTable;
    @FXML private TableView<Appointment> appointmentTable;

    @FXML private TextField patientIdField;
    @FXML private TextField patientNameField;
    @FXML private TextField patientAgeField;
    @FXML private TextField patientAilmentField;

    @FXML private TextField doctorIdField;
    @FXML private TextField doctorNameField;
    @FXML private TextField doctorAgeField;
    @FXML private TextField doctorSpecializationField;

    @FXML private ComboBox<Patient> appointmentPatientCombo;
    @FXML private ComboBox<Doctor> appointmentDoctorCombo;
    @FXML private TextField appointmentDateField;

    private final ObservableList<Patient> patients = FXCollections.observableArrayList();
    private final ObservableList<Doctor> doctors = FXCollections.observableArrayList();
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSampleData();
        refreshTables();
    }

    private void loadSampleData() {
        Patient patient1 = new Patient("P001", "Alice", 28, "Flu");
        Patient patient2 = new Patient("P002", "Sam", 34, "Cold");
        Patient patient3 = new Patient("P003", "Nina", 22, "Fever");

        Doctor doctor1 = new Doctor("D001", "Dr. Roy", 45, "General");
        Doctor doctor2 = new Doctor("D002", "Dr. Mehra", 38, "Cardiology");
        Doctor doctor3 = new Doctor("D003", "Dr. Patel", 50, "Orthopedics");

        Appointment appointment1 = new Appointment("A001", patient1, doctor1, "2026-05-20");
        Appointment appointment2 = new Appointment("A002", patient2, doctor2, "2026-05-22");
        Appointment appointment3 = new Appointment("A003", patient3, doctor3, "2026-05-23");

        patients.setAll(patient1, patient2, patient3);
        doctors.setAll(doctor1, doctor2, doctor3);
        appointments.setAll(appointment1, appointment2, appointment3);
    }

    private void refreshTables() {
        patientTable.setItems(patients);
        doctorTable.setItems(doctors);
        appointmentTable.setItems(appointments);

        appointmentPatientCombo.setItems(patients);
        appointmentDoctorCombo.setItems(doctors);
    }

    @FXML public void refreshStats() {
        patientTable.refresh();
        doctorTable.refresh();
        appointmentTable.refresh();
        appointmentPatientCombo.setItems(patients);
        appointmentDoctorCombo.setItems(doctors);
    }

    @FXML private void showPatientForm() {
        tabPane.getSelectionModel().select(0);
    }

    @FXML private void showPatients() {
        tabPane.getSelectionModel().select(0);
    }

    @FXML private void showDoctorForm() {
        tabPane.getSelectionModel().select(1);
    }

    @FXML private void showDoctors() {
        tabPane.getSelectionModel().select(1);
    }

    @FXML private void showAppointmentForm() {
        tabPane.getSelectionModel().select(2);
    }

    @FXML private void showAppointments() {
        tabPane.getSelectionModel().select(2);
    }

    @FXML private void addPatient() {
        String id = patientIdField.getText().trim();
        String name = patientNameField.getText().trim();
        String ageText = patientAgeField.getText().trim();
        String ailment = patientAilmentField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || ageText.isEmpty()) {
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            return;
        }

        patients.add(new Patient(id, name, age, ailment.isEmpty() ? "Not specified" : ailment));
        refreshTables();
        patientIdField.clear();
        patientNameField.clear();
        patientAgeField.clear();
        patientAilmentField.clear();
    }

    @FXML private void addDoctor() {
        String id = doctorIdField.getText().trim();
        String name = doctorNameField.getText().trim();
        String ageText = doctorAgeField.getText().trim();
        String specialization = doctorSpecializationField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || ageText.isEmpty()) {
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            return;
        }

        doctors.add(new Doctor(id, name, age, specialization.isEmpty() ? "General" : specialization));
        refreshTables();
        doctorIdField.clear();
        doctorNameField.clear();
        doctorAgeField.clear();
        doctorSpecializationField.clear();
    }

    @FXML private void bookAppointment() {
        Patient selectedPatient = appointmentPatientCombo.getValue();
        Doctor selectedDoctor = appointmentDoctorCombo.getValue();
        String date = appointmentDateField.getText().trim();

        if (selectedPatient == null || selectedDoctor == null || date.isEmpty()) {
            return;
        }

        String nextId = "A" + String.format("%03d", appointments.size() + 1);
        appointments.add(new Appointment(nextId, selectedPatient, selectedDoctor, date));
        refreshTables();
        appointmentDateField.clear();
    }
}
