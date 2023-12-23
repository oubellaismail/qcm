module controller.qcmpro {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;


    opens controller.qcmpro to javafx.fxml;
    exports controller.qcmpro;
    exports model;
    exports DAO;
    exports DAOimplementation;

}