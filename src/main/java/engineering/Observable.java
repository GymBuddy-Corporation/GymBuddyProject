package engineering;

import model.Exercise;
import model.ExerciseStatus;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    //ogni esercizio ha N observer
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public synchronized void notifyObservers(Exercise exercise){
        for (Observer observer : this.observers) {
            observer.update(exercise);
        }
    }
}
