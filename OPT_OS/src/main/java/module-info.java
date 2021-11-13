module com.example.opt_os {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.opt_os to javafx.fxml;
    exports com.example.opt_os;
}