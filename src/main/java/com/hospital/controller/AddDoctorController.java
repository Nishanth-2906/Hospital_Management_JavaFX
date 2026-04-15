package com.hospital.controller;

import com.hospital.model.Doctor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the "Add Doctor" form.
 */
public class AddDoctorController extends BaseController implements Initializable {

    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtAge;
    @FXML private ComboBox<String> cmbSpecialization;

    private Doctor editingDoctor = null;
    private boolean isEditing = false;

    // Common medical specializations
    private static final String[] SPECIALIZATIONS = {
        "General Medicine", "Cardiology", "Neurology", "Orthopedics",
        "Pediatrics", "Dermatology", "Gynecology", "Ophthalmology",
        "ENT", "Psychiatry", "Radiology", "Oncology", "Other"
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbSpecialization.getItems().addAll(SPECIALIZATIONS);
        cmbSpecialization.setValue("General Medicine");

        int next = store.getTotalDoctors() + 1;
        txtId.setText("D" + String.format("%03d", next));
        txtId.setEditable(false);
    }

    public void loadDoctorForEditing(Doctor doctor) {
        this.editingDoctor = doctor;
        this.isEditing = true;
        txtId.setText(doctor.getId());
        txtName.setText(doctor.getName());
        txtAge.setText(String.valueOf(doctor.getAge()));
        cmbSpecialization.setValue(doctor.getSpecialization());
    }

    @FXML
    private void handleAddDoctor() {
        String name           = txtName.getText().trim();
        String ageText        = txtAge.getText().trim();
        String specialization = cmbSpecialization.getValue();

        // --- Validation ---
        if (name.isEmpty()) {
            showError("Doctor name is required.");
            return;
        }
        if (ageText.isEmpty()) {
            showError("Age is required.");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
            if (age <= 0 || age > 100) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            showError("Please enter a valid age (1-100).");
            return;
        }

        if (specialization == null || specialization.isEmpty()) {
            showError("Please select a specialization.");
            return;
        }

        // --- Save ---
        if (isEditing && editingDoctor != null) {
            store.updateDoctor(editingDoctor.getId(), name, age, specialization);
            showSuccess("Doctor \"" + name + "\" updated successfully!");
        } else {
            store.addDoctor(name, age, specialization);
            showSuccess("Doctor \"" + name + "\" added successfully!");
        }

        if (dashboard != null) dashboard.refreshStats();
        handleClear();
    }

    @FXML
    private void handleClear() {
        txtName.clear();
        txtAge.clear();
        cmbSpecialization.setValue("General Medicine");
        editingDoctor = null;
        isEditing = false;
        int next = store.getTotalDoctors() + 1;
        txtId.setText("D" + String.format("%03d", next));
    }
}
