module controller.qcmpro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;
    requires java.net.http;

    opens controller.qcmpro to javafx.fxml;
    exports controller.qcmpro;
}