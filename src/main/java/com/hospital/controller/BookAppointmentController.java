package com.hospital.controller;

import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Controller for booking an appointment.
 */
public class BookAppointmentController extends BaseController implements Initializable {

    @FXML private TextField           txtApptId;
    @FXML private ComboBox<Patient>   cmbPatient;
    @FXML private ComboBox<Doctor>    cmbDoctor;
    @FXML private DatePicker          dpDate;

    private Appointment editingAppointment = null;
    private boolean isEditing = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Populate dropdowns from DataStore
        cmbPatient.setItems(store.getPatients());
        cmbDoctor.setItems(store.getDoctors());

        // Show patient name in dropdown
        cmbPatient.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Patient p, boolean empty) {
                super.updateItem(p, empty);
                setText((p == null || empty) ? null : p.getId() + " — " + p.getName());
            }
        });
        cmbPatient.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(Patient p, boolean empty) {
                super.updateItem(p, empty);
                setText((p == null || empty) ? null : p.getId() + " — " + p.getName());
            }
        });

        // Show doctor name in dropdown
        cmbDoctor.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Doctor d, boolean empty) {
                super.updateItem(d, empty);
                setText((d == null || empty) ? null : d.getId() + " — Dr. " + d.getName()
                        + " (" + d.getSpecialization() + ")");
            }
        });
        cmbDoctor.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(Doctor d, boolean empty) {
                super.updateItem(d, empty);
                setText((d == null || empty) ? null : d.getId() + " — Dr. " + d.getName()
                        + " (" + d.getSpecialization() + ")");
            }
        });

        // Default date = today
        dpDate.setValue(LocalDate.now());

        // Show next appointment ID
        updateApptId();
        txtApptId.setEditable(false);
    }

    public void loadAppointmentForEditing(Appointment appointment) {
        this.editingAppointment = appointment;
        this.isEditing = true;
        txtApptId.setText(appointment.getAppointmentId());
        cmbPatient.setValue(appointment.getPatient());
        cmbDoctor.setValue(appointment.getDoctor());
        dpDate.setValue(LocalDate.parse(appointment.getDate()));
    }

    @FXML
    private void handleBookAppointment() {
        Patient patient = cmbPatient.getValue();
        Doctor  doctor  = cmbDoctor.getValue();
        LocalDate date  = dpDate.getValue();

        // --- Validation ---
        if (patient == null) { showError("Please select a patient."); return; }
        if (doctor  == null) { showError("Please select a doctor.");  return; }
        if (date    == null) { showError("Please select a date.");    return; }
        if (date.isBefore(LocalDate.now())) {
            showWarning("You have selected a past date. Appointment will still be recorded.");
        }

        // --- Save ---
        if (isEditing && editingAppointment != null) {
            store.updateAppointment(editingAppointment.getAppointmentId(), patient, doctor, date.toString());
            showSuccess("Appointment updated!\n"
                    + "Patient : " + patient.getName() + "\n"
                    + "Doctor  : " + doctor.getName() + "\n"
                    + "Date    : " + date);
        } else {
            store.addAppointment(patient, doctor, date.toString());
            showSuccess("Appointment booked!\n"
                    + "Patient : " + patient.getName() + "\n"
                    + "Doctor  : " + doctor.getName() + "\n"
                    + "Date    : " + date);
        }

        if (dashboard != null) dashboard.refreshStats();
        handleClear();
    }

    @FXML
    private void handleClear() {
        cmbPatient.setValue(null);
        cmbDoctor.setValue(null);
        dpDate.setValue(LocalDate.now());
        editingAppointment = null;
        isEditing = false;
        updateApptId();
    }

    private void updateApptId() {
        int next = store.getTotalAppointments() + 1;
        txtApptId.setText("A" + String.format("%03d", next));
    }
}
