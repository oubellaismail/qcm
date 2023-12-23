module controller.qcmpro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens controller.qcmpro to javafx.fxml;
    exports controller.qcmpro;

}