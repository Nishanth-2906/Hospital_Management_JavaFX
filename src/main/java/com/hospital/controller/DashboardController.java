package com.hospital.controller;

import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML private TabPane tabPane;
    @FXML private AnchorPane patientListRoot;
    @FXML private AnchorPane doctorListRoot;
    @FXML private AnchorPane appointmentListRoot;
    @FXML private AnchorPane addPatientRoot;
    @FXML private AnchorPane addDoctorRoot;
    @FXML private AnchorPane bookAppointmentRoot;

    // Included controllers
    private PatientListController patientListController;
    private DoctorListController doctorListController;
    private AppointmentListController appointmentListController;
    private AddPatientController addPatientController;
    private AddDoctorController addDoctorController;
    private BookAppointmentController bookAppointmentController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPatientListView();
        loadDoctorListView();
        loadAppointmentListView();
        loadAddPatientView();
        loadAddDoctorView();
        loadBookAppointmentView();

        // Set dashboard reference for included controllers
        if (patientListController != null) patientListController.setDashboard(this);
        if (doctorListController != null) doctorListController.setDashboard(this);
        if (appointmentListController != null) appointmentListController.setDashboard(this);
        if (addPatientController != null) addPatientController.setDashboard(this);
        if (addDoctorController != null) addDoctorController.setDashboard(this);
        if (bookAppointmentController != null) bookAppointmentController.setDashboard(this);
    }

    private void loadPatientListView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hospital/view/PatientList.fxml"));
            Parent root = loader.load();
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
            patientListRoot.getChildren().setAll(root);
            patientListController = (PatientListController) loader.getController();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load PatientList view", e);
        }
    }

    private void loadDoctorListView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hospital/view/DoctorList.fxml"));
            Parent root = loader.load();
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
            doctorListRoot.getChildren().setAll(root);
            doctorListController = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load DoctorList view", e);
        }
    }

    private void loadAppointmentListView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hospital/view/AppointmentList.fxml"));
            Parent root = loader.load();
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
            appointmentListRoot.getChildren().setAll(root);
            appointmentListController = (AppointmentListController) loader.getController();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load AppointmentList view", e);
        }
    }

    private void loadAddPatientView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hospital/view/AddPatient.fxml"));
            Parent root = loader.load();
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
            addPatientRoot.getChildren().setAll(root);
            addPatientController = (AddPatientController) loader.getController();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load AddPatient view", e);
        }
    }

    private void loadAddDoctorView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hospital/view/AddDoctor.fxml"));
            Parent root = loader.load();
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
            addDoctorRoot.getChildren().setAll(root);
            addDoctorController = (AddDoctorController) loader.getController();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load AddDoctor view", e);
        }
    }

    private void loadBookAppointmentView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hospital/view/BookAppointment.fxml"));
            Parent root = loader.load();
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
            bookAppointmentRoot.getChildren().setAll(root);
            bookAppointmentController = (BookAppointmentController) loader.getController();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load BookAppointment view", e);
        }
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

    public void switchToTab(String tabName) {
        switch (tabName) {
            case "Patients" -> tabPane.getSelectionModel().select(0);
            case "Doctors" -> tabPane.getSelectionModel().select(1);
            case "Appointments" -> tabPane.getSelectionModel().select(2);
            default -> {
            }
        }
    }

    @FXML public void refreshStats() {
        // Refresh stats if needed, but since data is Observable, it updates automatically
    }
}
