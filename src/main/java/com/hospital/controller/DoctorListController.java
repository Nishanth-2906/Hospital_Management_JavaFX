package com.hospital.controller;

import com.hospital.model.Doctor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Doctor list TableView.
 */
public class DoctorListController extends BaseController implements Initializable {

    @FXML private TableView<Doctor>              tableDoctors;
    @FXML private TableColumn<Doctor, String>    colId;
    @FXML private TableColumn<Doctor, String>    colName;
    @FXML private TableColumn<Doctor, Integer>   colAge;
    @FXML private TableColumn<Doctor, String>    colSpec;
    @FXML private TextField                      txtSearch;
    @FXML private Label                          lblCount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colSpec.setCellValueFactory(new PropertyValueFactory<>("specialization"));

        tableDoctors.setItems(store.getDoctors());
        tableDoctors.setPlaceholder(new Label("No doctors found."));

        updateCount();
        txtSearch.textProperty().addListener((obs, old, val) -> filterDoctors(val));
    }

    private void filterDoctors(String query) {
        if (query == null || query.isBlank()) {
            tableDoctors.setItems(store.getDoctors());
        } else {
            String lower = query.toLowerCase();
            tableDoctors.setItems(
                store.getDoctors().filtered(d ->
                    d.getName().toLowerCase().contains(lower) ||
                    d.getId().toLowerCase().contains(lower) ||
                    d.getSpecialization().toLowerCase().contains(lower)
                )
            );
        }
        updateCount();
    }

    @FXML
    private void handleDelete() {
        Doctor selected = tableDoctors.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showWarning("Please select a doctor to delete.");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete doctor \"" + selected.getName() + "\"?",
                ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Confirm Delete");
        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.YES) {
                store.getDoctors().remove(selected);
                showSuccess("Doctor removed.");
                if (dashboard != null) dashboard.refreshStats();
                updateCount();
            }
        });
    }

    @FXML
    private void handleRefresh() {
        txtSearch.clear();
        tableDoctors.setItems(store.getDoctors());
        updateCount();
    }

    private void updateCount() {
        lblCount.setText("Total: " + tableDoctors.getItems().size() + " doctors");
    }
}
