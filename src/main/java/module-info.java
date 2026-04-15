module com.hospital {
    requires javafx.controls;
    requires javafx.fxml;

    // Open packages to javafx.fxml for reflection (needed for FXML controllers)
    opens com.hospital            to javafx.fxml;
    opens com.hospital.controller to javafx.fxml;
    opens com.hospital.model      to javafx.base;    // needed for PropertyValueFactory

    exports com.hospital;
    exports com.hospital.controller;
    exports com.hospital.model;
    exports com.hospital.util;
}
