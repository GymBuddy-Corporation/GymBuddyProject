package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class Athlete extends User implements Serializable {
    /*private Card card;
    private WorkoutRoutine workoutPlan;
    private Trainer trainer;

    public Athlete(String username, PersonalInfo personalInfo, Credentials credentials) {
        super(username, personalInfo, credentials);
        card = new Card(
                "",
                null
        );
    }

    public Athlete(String username, PersonalInfo personalInfo, Credentials credentials, Card card) throws ExpiredCardException {
        super(username, personalInfo, credentials);
        checkCardExpirationDate(card.cardExpirationDate());
        this.card = card;
    }

    public String getCardNumber() *//*throws NoCardInsertedException *//*{
        if(card.cardNumber()==null) {
            throw new NoCardInsertedException();
        }else{
            return card.cardNumber();
        }
    }

    public void setCard(Card card) *//*throws ExpiredCardException *//*{
        checkCardExpirationDate(card.cardExpirationDate());
        this.card = card;
    }

    public YearMonth getCardExpirationDate() *//*throws NoCardInsertedException *//*{
        if (card.cardExpirationDate() == null) {
            throw new NoCardInsertedException();
        } else {
            return card.cardExpirationDate();
        }
    }

    public void checkCardExpirationDate(YearMonth cardExpirationDate) *//*throws ExpiredCardException *//*{
        if ((cardExpirationDate != null) && !((cardExpirationDate.getYear() > LocalDate.now().getYear()) ||
                ((cardExpirationDate.getYear() == LocalDate.now().getYear()) &&
                        (cardExpirationDate.getMonthValue() > LocalDate.now().getMonthValue())))) {
            throw new ExpiredCardException();
        }
    }

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        if(workoutPlan != null) {
            this.workoutPlan = new WorkoutPlan();
            this.workoutPlan.addAllWorkoutDays(workoutPlan.getWorkoutDayList());
        } else {
            this.workoutPlan = null;
        }
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }*/
}
