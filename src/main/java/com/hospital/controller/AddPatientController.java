package com.hospital.controller;

import com.hospital.model.Patient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the "Add Patient" form.
 */
public class AddPatientController extends BaseController implements Initializable {

    @FXML private TextField txtId;          // auto-generated, read-only display
    @FXML private TextField txtName;
    @FXML private TextField txtAge;
    @FXML private TextField txtAilment;

    private Patient editingPatient = null;
    private boolean isEditing = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Show what the next ID will look like (informational only)
        int next = store.getTotalPatients() + 1;
        txtId.setText("P" + String.format("%03d", next));
        txtId.setEditable(false);
    }

    public void loadPatientForEditing(Patient patient) {
        this.editingPatient = patient;
        this.isEditing = true;
        txtId.setText(patient.getId());
        txtName.setText(patient.getName());
        txtAge.setText(String.valueOf(patient.getAge()));
        txtAilment.setText(patient.getAilment());
    }

    @FXML
    private void handleAddPatient() {
        String name    = txtName.getText().trim();
        String ageText = txtAge.getText().trim();
        String ailment = txtAilment.getText().trim();

        // --- Validation ---
        if (name.isEmpty()) {
            showError("Patient name is required.");
            return;
        }
        if (ageText.isEmpty()) {
            showError("Age is required.");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
            if (age <= 0 || age > 150) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            showError("Please enter a valid age (1-150).");
            return;
        }

        if (ailment.isEmpty()) ailment = "Not specified";

        // --- Save ---
        if (isEditing && editingPatient != null) {
            store.updatePatient(editingPatient.getId(), name, age, ailment);
            showSuccess("Patient \"" + name + "\" updated successfully!");
        } else {
            store.addPatient(name, age, ailment);
            showSuccess("Patient \"" + name + "\" added successfully!");
        }

        // Refresh dashboard stats & clear form
        if (dashboard != null) dashboard.refreshStats();
        handleClear();
    }

    @FXML
    private void handleClear() {
        txtName.clear();
        txtAge.clear();
        txtAilment.clear();
        editingPatient = null;
        isEditing = false;
        int next = store.getTotalPatients() + 1;
        txtId.setText("P" + String.format("%03d", next));
    }
}
