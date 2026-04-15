package com.hospital.controller;

import com.hospital.util.DataStore;
import javafx.scene.control.Alert;

/**
 * BaseController — shared behaviour for all sub-controllers.
 * Provides access to DataStore and alert helpers.
 */
public abstract class BaseController {

    protected final DataStore store = DataStore.getInstance();
    protected DashboardController dashboard;

    /** Called by DashboardController after loading a sub-view. */
    public void setDashboard(DashboardController dashboard) {
        this.dashboard = dashboard;
    }

    // --- Reusable alert helpers ---

    protected void showSuccess(String message) {
        showAlert(Alert.AlertType.INFORMATION, "✅ Success", message);
    }

    protected void showError(String message) {
        showAlert(Alert.AlertType.ERROR, "❌ Error", message);
    }

    protected void showWarning(String message) {
        showAlert(Alert.AlertType.WARNING, "⚠️ Warning", message);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
