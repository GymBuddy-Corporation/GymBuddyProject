package model;

import model.record.Card;
import model.record.Credentials;
import model.record.PersonalInfo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;

public class Athlete extends User implements Serializable {
    private Card card; //TODO vedi come gestire le carte
    private WorkoutRoutine workoutRoutine;
    private Trainer trainer;

    public Athlete(String username, PersonalInfo personalInfo, Credentials credentials) {
        super(username, personalInfo, credentials);
        card = new Card(
                "",
                null
        );
    }

    public Athlete(String username, PersonalInfo personalInfo, Credentials credentials, Card card) /*throws ExpiredCardException*/ {
        super(username, personalInfo, credentials);
        //checkCardExpirationDate(card.cardExpirationDate());
        this.card = card;
    }

    public String getCardNumber() /*throws NoCardInsertedException*/ {
        if(card.cardNumber()==null) {
            /*throw new NoCardInsertedException();*/
            System.out.println("Carta non inserita");
            return null;
        }else{
            return card.cardNumber();
        }
    }

    public void setCard(Card card) /*throws ExpiredCardException*/ {
        checkCardExpirationDate(card.cardExpirationDate());
        this.card = card;
    }

    public YearMonth getCardExpirationDate() /*throws NoCardInsertedException */{
        if (card.cardExpirationDate() == null) {
            /*throw new NoCardInsertedException();*/
            System.out.println("Carta non inserita");
            return null;
        } else {
            return card.cardExpirationDate();
        }
    }

    public void checkCardExpirationDate(YearMonth cardExpirationDate) /*throws ExpiredCardException*/ {
        if ((cardExpirationDate != null) && !((cardExpirationDate.getYear() > LocalDate.now().getYear()) ||
                ((cardExpirationDate.getYear() == LocalDate.now().getYear()) &&
                        (cardExpirationDate.getMonthValue() > LocalDate.now().getMonthValue())))) {
            System.out.println("Carta scaduta");
            /*throw new ExpiredCardException();*/
        }
    }

    public WorkoutRoutine getWorkoutPlan() {
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

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}
