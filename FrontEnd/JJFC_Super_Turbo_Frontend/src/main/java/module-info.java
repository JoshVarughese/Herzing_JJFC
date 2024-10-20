module com.jjfc.superturboservice.jjfc_super_turbo_frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires java.net.http;

    opens com.jjfc.superturboservice.jjfc_super_turbo_frontend to javafx.fxml;
    exports com.jjfc.superturboservice.jjfc_super_turbo_frontend;
    exports com.jjfc.superturboservice.controllers;
    exports com.jjfc.superturboservice.models;
    opens com.jjfc.superturboservice.controllers to javafx.fxml;
}
