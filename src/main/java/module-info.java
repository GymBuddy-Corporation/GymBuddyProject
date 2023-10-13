module start {

    requires javafx.controls;
    requires javafx.fxml;

    opens start to javafx.fxml;
    exports start;
    exports viewone.graphical_controllers.launcher.gym;
    exports viewone;

    exports viewone.graphical_controllers.launcher;
    opens viewone to javafx.fxml;
    opens viewone.graphical_controllers.launcher.gym to javafx.fxml;
    opens viewone.graphical_controllers.launcher to javafx.fxml;
    exports viewone.graphical_controllers.gym;
    opens viewone.graphical_controllers.gym to javafx.fxml;
    exports utils;
    opens utils to javafx.fxml;
}
