package model;

import java.util.Date;

public class Wallet {
    private final Date startOfMembership;
    private final Date endOfMembership;
    private final Gym currentGym;
    private final int points;
    private final String membershipName;
    private final float membershipPrice;

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    private  Trainer trainer;

    public Wallet(Date startOfMembership, Date endOfMembership, Gym currentGym, int points, String membershipName, float membershipPrice, Trainer trainer) {
        this.startOfMembership = startOfMembership;
        this.endOfMembership = endOfMembership;
        this.currentGym = currentGym;
        this.points = points;
        this.membershipName = membershipName;
        this.membershipPrice = membershipPrice;
        this.trainer = trainer;
    }
    
    public Date getStartOfMembership() {
        return startOfMembership;
    }

    public Date getEndOfMembership() {
        return endOfMembership;
    }

    public Gym getCurrentGym() {
        return currentGym;
    }

    public int getPoints() {
        return points;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public float getMembershipPrice() {
        return membershipPrice;
    }
}