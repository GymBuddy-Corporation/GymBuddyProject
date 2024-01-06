package controllers;

import beans.AthleteBean;
import beans.RequestBean;
import database.dao.RequestDAO;

public class CreateRequestController {
    public void askForNewWorkoutRoutine(RequestBean requestBean){
        /*new RequestDAO().saveRequest(
                requestBean.getRequestDate(),
                requestBean.getInfo(),
                requestBean.getAthleteBean().getFiscalCode(),
                requestBean.getTrainerFc()
        );*/
    }
}
