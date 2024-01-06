module start {

    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;

    opens start to javafx.fxml;
    exports start;
    exports model.record;
    exports model;
    opens model;
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
    exports viewone.manageListView;
    opens viewone.manageListView;
    exports viewone.manageListView.listCells;
    opens viewone.manageListView.listCells;
    opens utils;
    exports exceptions.dataException;
    opens engineering.popups to javafx.fxml;
    exports exceptions.dataException.TyperEnumerations;
    exports exceptions;
    exports viewone.beans;
    opens viewone.beans to javafx.fxml;
    opens beans to javafx.fxml;
    opens model.record;
    exports viewtwo.graphicalcontrollers.pt;
    opens viewtwo.graphicalcontrollers.pt;

    opens database.dao;
    exports database.dao;

}
