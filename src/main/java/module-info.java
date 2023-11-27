module start {

    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;

    opens start to javafx.fxml;
    exports start;
    exports viewone.graphicalControllers.pt;
    exports viewone.graphicalControllers.launcher.gym;
    exports viewone.graphicalControllers.launcher.pt;
    exports viewone;
    exports viewone.graphicalControllers.launcher.athlete;
    exports viewone.graphicalControllers.athlete;
    exports viewone.graphicalControllers.launcher;
    exports viewtwo.graphicalcontrollers.launcher;
    opens viewone to javafx.fxml;
    opens viewone.graphicalControllers.pt;
    exports  viewone.engineering;
    opens viewone.engineering to javafx.fxml;
    opens viewone.graphicalControllers.launcher.athlete  to javafx.fxml;
    opens viewone.graphicalControllers.launcher.gym to javafx.fxml;
    opens viewone.graphicalControllers.launcher to javafx.fxml;
    opens viewtwo.graphicalcontrollers.launcher to javafx.fxml;
    opens viewtwo.popups.controllers to javafx.fxml;
    exports viewone.graphicalControllers.gym;
    exports utils;
    exports beans;
    exports controllers;
    opens utils to javafx.fxml;
    opens viewone.graphicalControllers.gym;
    opens viewone.graphicalControllers.athlete;

}
