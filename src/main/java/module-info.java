module start {

    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;

    opens start to javafx.fxml;
    exports start;
    exports viewone.graphical_controllers.pt;
    exports viewone.graphical_controllers.launcher.gym;
    exports viewone.graphical_controllers.launcher.pt;
    exports viewone;
    exports viewone.graphical_controllers.launcher.athlete;
    exports viewone.graphical_controllers.athlete;
    exports viewone.graphical_controllers.launcher;
    opens viewone to javafx.fxml;
    opens viewone.graphical_controllers.pt;
    opens viewone.graphical_controllers.launcher.athlete  to javafx.fxml;
    opens viewone.graphical_controllers.launcher.gym to javafx.fxml;
    opens viewone.graphical_controllers.launcher to javafx.fxml;
    exports viewone.graphical_controllers.gym;
    exports utils;
    exports beans;
    exports controllers;
    opens utils to javafx.fxml;
    opens viewone.graphical_controllers.gym;
    opens viewone.graphical_controllers.athlete;
}
