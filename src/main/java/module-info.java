module start {

    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;

    opens start to javafx.fxml;
    exports start;
    exports viewone.graphicalControllers.pt;
    exports viewone.graphicalControllers.launcher.gym;
    exports viewone;
    exports viewone.graphicalControllers.athlete;
    exports viewone.graphicalControllers.launcher;
    exports viewtwo.graphicalcontrollers.launcher;
    opens viewone to javafx.fxml;
    opens viewone.graphicalControllers.pt;
    exports  viewone.popup;
    opens viewone.popup to javafx.fxml;
    opens viewone.graphicalControllers.launcher.gym to javafx.fxml;
    opens viewone.graphicalControllers.launcher to javafx.fxml;
    opens viewtwo.graphicalcontrollers.launcher to javafx.fxml;
    opens viewtwo.popups.controllers to javafx.fxml;
    opens  viewtwo.graphicalcontrollers.home to javafx.fxml;
    exports viewone.graphicalControllers.gym;
    exports utils;
    exports beans;
    exports controllers;
    opens viewone.graphicalControllers.gym;
    opens viewone.graphicalControllers.athlete;
    exports engineering;
    opens engineering;
    exports engineering.manageListView;
    opens engineering.manageListView;
    exports engineering.manageListView.listCells;
    opens engineering.manageListView.listCells;
    opens utils;
    exports exceptions.dataException;
    opens engineering.popups to javafx.fxml;

}
