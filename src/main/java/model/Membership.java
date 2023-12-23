package model;

import engineering.decorator.MembershipInterface;

import java.lang.reflect.Member;

public class Membership implements MembershipInterface {

    private final String name;
    private final String description;
    private final float price;
    private final int durationInDays;
    private final int pointsAwardedOnBuy;

    public Membership(String name,String description,float price,int duration,int points){
        this.name=name;
        this.description=description;
        this.durationInDays=duration;
        this.price=price;
        this.pointsAwardedOnBuy=points;
    }

    @Override
    public final float getPrice() {
        return price;
    }

    @Override
    public final int getPoints() {
        return pointsAwardedOnBuy;
    }

    @Override
    public final int getDuration() {
        return durationInDays;
    }

    @Override
    public final String getName() {
        return description;
    }

    @Override
    public final String getDescription() {
        return name;
    }
}
