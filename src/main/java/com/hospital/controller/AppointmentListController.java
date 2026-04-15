package com.hospital.controller;

import com.hospital.model.Appointment;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Appointment list TableView.
 */
public class AppointmentListController extends BaseController implements Initializable {

    @FXML private TableView<Appointment>              tableAppts;
    @FXML private TableColumn<Appointment, String>    colId;
    @FXML private TableColumn<Appointment, String>    colPatient;
    @FXML private TableColumn<Appointment, String>    colDoctor;
    @FXML private TableColumn<Appointment, String>    colSpec;
    @FXML private TableColumn<Appointment, String>    colDate;
    @FXML private TableColumn<Appointment, String>    colStatus;
    @FXML private TextField                           txtSearch;
    @FXML private Label                               lblCount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colDoctor.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        colSpec.setCellValueFactory(new PropertyValueFactory<>("doctorSpecialization"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Colour the status cell
        colStatus.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setText(null); setStyle("");
                } else {
                    setText(status);
                    switch (status) {
                        case "Scheduled"  -> setStyle("-fx-text-fill: #0369a1; -fx-font-weight: bold;");
                        case "Completed"  -> setStyle("-fx-text-fill: #15803d; -fx-font-weight: bold;");
                        case "Cancelled"  -> setStyle("-fx-text-fill: #b91c1c; -fx-font-weight: bold;");
                        default           -> setStyle("");
                    }
                }
            }
        });

        tableAppts.setItems(store.getAppointments());
        tableAppts.setPlaceholder(new Label("No appointments found."));

        updateCount();
        txtSearch.textProperty().addListener((obs, old, val) -> filterAppts(val));
    }

    private void filterAppts(String query) {
        if (query == null || query.isBlank()) {
            tableAppts.setItems(store.getAppointments());
        } else {
            String lower = query.toLowerCase();
            tableAppts.setItems(
                store.getAppointments().filtered(a ->
                    a.getPatientName().toLowerCase().contains(lower) ||
                    a.getDoctorName().toLowerCase().contains(lower) ||
                    a.getDate().contains(lower) ||
                    a.getStatus().toLowerCase().contains(lower)
                )
            );
        }
        updateCount();
    }

    /** Mark selected appointment as Completed */
    @FXML
    private void handleMarkComplete() {
        Appointment sel = tableAppts.getSelectionModel().getSelectedItem();
        if (sel == null) { showWarning("Please select an appointment."); return; }
        sel.setStatus("Completed");
        tableAppts.refresh();
        showSuccess("Appointment marked as Completed.");
    }

    /** Cancel selected appointment */
    @FXML
    private void handleCancel() {
        Appointment sel = tableAppts.getSelectionModel().getSelectedItem();
        if (sel == null) { showWarning("Please select an appointment."); return; }
        sel.setStatus("Cancelled");
        tableAppts.refresh();
        showSuccess("Appointment cancelled.");
    }

    @FXML
    private void handleEdit() {
        Appointment selected = tableAppts.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showWarning("Please select an appointment to edit.");
            return;
        }
        if (dashboard != null) {
            BookAppointmentController bookController = dashboard.getBookAppointmentController();
            if (bookController != null) {
                bookController.loadAppointmentForEditing(selected);
            }
        }
    }

    @FXML
    private void handleDelete() {
        Appointment selected = tableAppts.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showWarning("Please select an appointment to delete.");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete appointment for \"" + selected.getPatientName() + "\"?",
                ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Confirm Delete");
        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.YES) {
                store.getAppointments().remove(selected);
                showSuccess("Appointment removed.");
                if (dashboard != null) dashboard.refreshStats();
                updateCount();
            }
        });
    }

    @FXML
    private void handleRefresh() {
        txtSearch.clear();
        tableAppts.setItems(store.getAppointments());
        tableAppts.refresh();
        updateCount();
    }

    private void updateCount() {
        lblCount.setText("Total: " + tableAppts.getItems().size() + " appointments");
    }
}
