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
    exports viewone.graphicalcontrollers.pt;
    exports viewone.graphicalcontrollers.launcher.gym;
    exports viewone;
    exports viewone.graphicalcontrollers.athlete;
    exports viewone.graphicalcontrollers.launcher;
    exports viewtwo.graphicalcontrollers.launcher;
    opens viewone to javafx.fxml;
    opens viewone.graphicalcontrollers.pt;
    exports  viewone.popup;
    opens viewone.popup to javafx.fxml;
    opens viewone.graphicalcontrollers.launcher.gym to javafx.fxml;
    opens viewone.graphicalcontrollers.launcher to javafx.fxml;
    opens viewtwo.graphicalcontrollers.launcher to javafx.fxml;
    opens viewtwo.popups.controllers to javafx.fxml;
    opens  viewtwo.graphicalcontrollers.home to javafx.fxml;
    exports viewone.graphicalcontrollers.gym;
    exports utils;
    exports beans;
    exports controllers;
    opens viewone.graphicalcontrollers.gym;
    opens viewone.graphicalcontrollers.athlete;
    exports engineering;
    opens engineering;
    exports viewone.managelistview;
    opens viewone.managelistview;
    exports viewone.managelistview.listCells;
    opens viewone.managelistview.listCells;
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
exports model.cupons;
exports engineering.decorator;
    opens database.dao;
    exports database.dao;

}
