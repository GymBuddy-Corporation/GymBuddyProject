package viewtwo.popups.controllers;

import exceptions.logger.CostumeLogger;
import javafx.fxml.FXML;
import viewtwo.engegnering.MainMenuSingleton;

import java.io.IOException;

public class MenuPopUpAthleteController extends MenuPopUpController {
    @FXML
    void goToManageMembership() {
        try {
            MainMenuSingleton.getMainMenu().setActivity("ManageMembershipAthlete.fxml", "athlete");
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
        clear();
    }

    @FXML
    void goToProfile() {
        try {
            MainMenuSingleton.getMainMenu().setActivity("PersonInfoPage.fxml", "home");
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }
        clear();
    }
}
