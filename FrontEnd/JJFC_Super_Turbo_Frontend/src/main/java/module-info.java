module com.jjfc.superturboservice.jjfc_super_turbo_frontend {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.jjfc.superturboservice.jjfc_super_turbo_frontend to javafx.fxml;
    exports com.jjfc.superturboservice.jjfc_super_turbo_frontend;
    exports com.jjfc.superturboservice.controllers;
    opens com.jjfc.superturboservice.controllers to javafx.fxml;
}
