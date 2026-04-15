package com.hospital.controller;

import com.hospital.model.Patient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Patient list TableView.
 */
public class PatientListController extends BaseController implements Initializable {

    @FXML private TableView<Patient>          tablePatients;
    @FXML private TableColumn<Patient, String> colId;
    @FXML private TableColumn<Patient, String> colName;
    @FXML private TableColumn<Patient, Integer> colAge;
    @FXML private TableColumn<Patient, String> colAilment;
    @FXML private TextField                    txtSearch;
    @FXML private Label                        lblCount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Bind columns to Patient properties
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colAilment.setCellValueFactory(new PropertyValueFactory<>("ailment"));

        // Load data directly from ObservableList (auto-updates)
        tablePatients.setItems(store.getPatients());
        tablePatients.setPlaceholder(new Label("No patients found."));

        updateCount();

        // Live search filter
        txtSearch.textProperty().addListener((obs, old, val) -> filterPatients(val));
    }

    private void filterPatients(String query) {
        if (query == null || query.isBlank()) {
            tablePatients.setItems(store.getPatients());
        } else {
            String lower = query.toLowerCase();
            tablePatients.setItems(
                store.getPatients().filtered(p ->
                    p.getName().toLowerCase().contains(lower) ||
                    p.getId().toLowerCase().contains(lower) ||
                    p.getAilment().toLowerCase().contains(lower)
                )
            );
        }
        updateCount();
    }

    @FXML
    private void handleDelete() {
        Patient selected = tablePatients.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showWarning("Please select a patient to delete.");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete patient \"" + selected.getName() + "\"?",
                ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Confirm Delete");
        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.YES) {
                store.getPatients().remove(selected);
                showSuccess("Patient removed.");
                if (dashboard != null) dashboard.refreshStats();
                updateCount();
            }
        });
    }

    @FXML
    private void handleEdit() {
        Patient selected = tablePatients.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showWarning("Please select a patient to edit.");
            return;
        }
        // Switch to Add Patient tab and load data
        if (dashboard != null) {
            dashboard.switchToTab("Add Patient");
            AddPatientController addController = dashboard.getAddPatientController();
            if (addController != null) {
                addController.loadPatientForEditing(selected);
            }
        }
    }

    @FXML
    private void handleRefresh() {
        txtSearch.clear();
        tablePatients.setItems(store.getPatients());
        updateCount();
    }

    private void updateCount() {
        lblCount.setText("Total: " + tablePatients.getItems().size() + " patients");
    }
}
