module demo.guidemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens demo.guidemo to javafx.fxml;
    exports demo.guidemo;
}