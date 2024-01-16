package model;

import model.record.Card;
import model.record.Credentials;
import model.record.PersonalInfo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;

public class Athlete extends Person implements Serializable {
    private Card card;
    //TODO Alex vedi come gestire le carte, che potrebbe essere comodo metterle qui
    // i metodi get e set gia ci sono nel caso, vedi tu come volertela gestire

    private WorkoutRoutine workoutRoutine;
    private Trainer trainer;
    //todo richiesta

    public Athlete(String username, PersonalInfo personalInfo, Credentials credentials, Gym gym) {
        super(username, credentials, personalInfo, gym);
    }

    public Athlete(String username, PersonalInfo personalInfo, Credentials credentials, Gym gym, Trainer trainer) {
        super(username, credentials, personalInfo, gym);
        this.trainer = trainer;
    }

    public Athlete(String username, PersonalInfo personalInfo, Credentials credentials, Card card, Gym gym, Trainer trainer) /*throws ExpiredCardException*/ {
        //costruttore con inizializzazione della carta
        super(username, credentials, personalInfo, gym);
        checkCardExpirationDate(card.cardExpirationDate());
        this.card = card;
        this.trainer = trainer;
    }

    public String getCardNumber() {
        //todo se non usata cancella
        if(card.cardNumber()==null) {
            System.out.println("Carta non inserita");
            return null;
        }else{
            return card.cardNumber();
        }
    }

    public void setCard(Card card) {
        checkCardExpirationDate(card.cardExpirationDate());
        this.card = card;
    }

    public YearMonth getCardExpirationDate() {
        if (card.cardExpirationDate() == null) {
            System.out.println("Carta non inserita");
            return null;
        } else {
            return card.cardExpirationDate();
        }
    }

    public void checkCardExpirationDate(YearMonth cardExpirationDate) {
        if ((cardExpirationDate != null) && !((cardExpirationDate.getYear() > LocalDate.now().getYear()) ||
                ((cardExpirationDate.getYear() == LocalDate.now().getYear()) &&
                        (cardExpirationDate.getMonthValue() > LocalDate.now().getMonthValue())))) {
            System.out.println("Carta scaduta");
            /*throw new ExpiredCardException();*/
        }
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

    public Trainer getTrainer() {
        return trainer;
    }
}
