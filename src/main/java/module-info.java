module controller.qcmpro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires com.google.gson;


    opens controller.qcmpro to javafx.fxml;
    exports controller.qcmpro;
}