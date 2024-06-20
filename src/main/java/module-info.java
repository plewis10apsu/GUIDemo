module demo.guidemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens demo.guidemo to javafx.fxml;
    exports demo.guidemo;
}