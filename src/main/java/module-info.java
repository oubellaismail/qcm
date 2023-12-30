module controller.qcmpro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;


    opens controller.qcmpro to javafx.fxml;
    exports controller.qcmpro;
}