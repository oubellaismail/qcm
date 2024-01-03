module controller.qcmpro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;
    requires java.net.http;

    opens controller.qcmpro to javafx.fxml;
    opens com.json.model to com.google.gson;
    requires com.google.gson;
    requires java.net.http;
    exports controller.qcmpro;
}