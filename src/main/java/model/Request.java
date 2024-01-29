package model;

import java.io.Serializable;

public class Request implements Serializable {
    private final String info;
    private final Athlete athlete;
    private final Trainer trainer;

    public Request(String info, Athlete athlete, Trainer trainer){
        this.info = info;
        this.athlete = athlete;
        this.trainer = trainer;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public String getInfo() {
        return info;
    }
}
