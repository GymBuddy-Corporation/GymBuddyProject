package viewone.graphical_controllers.pt;

        import javafx.fxml.FXML;
        import utils.MainStage;
        import utils.SwitchPage;

public class ProfileAthleteGUIController {

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AthleteHome.fxml","athlete",1);
    }
    @FXML
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"AthleteLogin.fxml","launcher",1);
    }
    @FXML
    public void deleteAccount() throws Exception {
        //TODO gestisci l'eliminazione dell'account con notifiche e pop-up
        SwitchPage.setStage(MainStage.getStage(),"AthleteLogin.fxml","launcher",1);
    }
    @FXML
    public void editProfile() throws Exception {
        //TODO gestisci la gestione dell'account
        SwitchPage.setStage(MainStage.getStage(),"AthletePT.fxml","athlete",1);
    }
}
