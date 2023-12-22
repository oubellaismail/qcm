module controller.qcmpro {
    requires javafx.controls;
    requires javafx.fxml;


    opens controller.qcmpro to javafx.fxml;
    exports controller.qcmpro;
}