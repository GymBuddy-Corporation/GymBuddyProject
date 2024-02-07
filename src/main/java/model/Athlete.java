package model;

import model.record.Card;
import model.record.Credentials;
import model.record.PersonalInfo;

import java.time.Instant;
import java.util.Date;

public class Athlete extends Person  {
    private Card card;
    private WorkoutRoutine workoutRoutine;
    private Wallet wallet;

    public Athlete(String username, PersonalInfo personalInfo, Credentials credentials) {
        super(username, credentials, personalInfo);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getCardNumber() {
        if(card==null) {
            return null;
        }else{
            return card.cardNumber();
        }
    }

    public String gymName(){
            if(wallet==null || wallet.getCurrentGym().getGymName()==null)return "";
            return  wallet.getCurrentGym().getGymName();

    }
    public boolean isMembershipValid(){
        if(wallet==null || wallet.getEndOfMembership()==null)return false;
        return Date.from(Instant.now()).after(wallet.getEndOfMembership());
    }

    public int getPoints(){
        if(wallet==null)return 0;
        return wallet.getPoints();
    }

    public Date getExpirationOfmembership(){
        if(wallet==null)return null;
        return wallet.getEndOfMembership();
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
