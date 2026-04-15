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

    // Included controllers
    @FXML private PatientListController patientListController;
    @FXML private DoctorListController doctorListController;
    @FXML private AppointmentListController appointmentListController;
    @FXML private AddPatientController addPatientController;
    @FXML private AddDoctorController addDoctorController;
    @FXML private BookAppointmentController bookAppointmentController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set dashboard reference for included controllers
        if (patientListController != null) patientListController.setDashboard(this);
        if (doctorListController != null) doctorListController.setDashboard(this);
        if (appointmentListController != null) appointmentListController.setDashboard(this);
        if (addPatientController != null) addPatientController.setDashboard(this);
        if (addDoctorController != null) addDoctorController.setDashboard(this);
        if (bookAppointmentController != null) bookAppointmentController.setDashboard(this);
    }

    // Getter methods for included controllers
    public AddPatientController getAddPatientController() {
        return addPatientController;
    }

    public AddDoctorController getAddDoctorController() {
        return addDoctorController;
    }

    public BookAppointmentController getBookAppointmentController() {
        return bookAppointmentController;
    }

    public BookAppointmentController getBookAppointmentController() {
        return bookAppointmentController;
    }

    public void switchToTab(String tabName) {
        switch (tabName) {
            case "Patient List" -> tabPane.getSelectionModel().select(0);
            case "Doctor List" -> tabPane.getSelectionModel().select(1);
            case "Appointment List" -> tabPane.getSelectionModel().select(2);
            case "Add Patient" -> tabPane.getSelectionModel().select(3);
            case "Add Doctor" -> tabPane.getSelectionModel().select(4);
            case "Book Appointment" -> tabPane.getSelectionModel().select(5);
        }
    }

    @FXML public void refreshStats() {
        // Refresh stats if needed, but since data is Observable, it updates automatically
    }
}
