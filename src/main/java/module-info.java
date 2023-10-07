module start {
    requires javafx.controls;
    requires javafx.fxml;

    opens start to javafx.fxml;
    exports start;
    exports viewone;
    opens viewone to javafx.fxml;
}
