package model;

import model.record.Card;
import model.record.Credentials;
import model.record.PersonalInfo;
import java.time.YearMonth;

public class Athlete extends Person  {
    private Card card;
    private WorkoutRoutine workoutRoutine;

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    private Wallet wallet;

    public Athlete(String username, PersonalInfo personalInfo, Credentials credentials) {
        super(username, credentials, personalInfo);
    }


    public Athlete(String username, PersonalInfo personalInfo, Credentials credentials, Card card, Trainer trainer) /*throws ExpiredCardException*/ {
        //costruttore con inizializzazione della carta
        super(username, credentials, personalInfo);
        this.card = card;
    }

    public String getCardNumber() {
        if(card==null) {
            return null;
        }else{
            return card.cardNumber();
        }
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public YearMonth getCardExpirationDate() {
            return card.cardExpirationDate();
    }

    public String getCardExpirationYear(){
            return String.valueOf(card.cardExpirationDate().getYear());
    }
    public String getCardExpirationMonth(){
            return String.valueOf(card.cardExpirationDate().getMonthValue());
    }

    public WorkoutRoutine getWorkoutRoutine() {
        return workoutRoutine;
    }

    public void setWorkoutRoutine(WorkoutRoutine workoutRoutine) {
        if(workoutRoutine != null) {
            this.workoutRoutine = new WorkoutRoutine();
            this.workoutRoutine.addAllWorkoutDays(workoutRoutine.getWorkoutDayList());
        } else {
            this.workoutRoutine = null;
        }
    }

    public String getTrainerFc() {
        if(wallet==null || wallet.getTrainer()==null)return "";
        return wallet.getTrainer().getFC();
    }

}
