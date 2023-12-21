module controller.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens controller.demo to javafx.fxml;
    exports controller.demo;
}