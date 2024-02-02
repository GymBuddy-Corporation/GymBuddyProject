package model;

import engineering.decorator.MembershipInterface;
import exceptions.DecoratorNoBaseComponentException;
public class Membership implements MembershipInterface {

    private final String name;
    private final String description;
    private final float price;
    private final int durationInDays;
    private final int pointsAwardedOnBuy;

    public Membership(String name, String description, float price, int duration, int points){
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
        return name;
    }

    @Override
    public String getBuildedName() {
        return getName();
    }

    @Override
    public final String getDescription() {
        return description;
    }

    @Override
    public boolean isForNewUsers() throws DecoratorNoBaseComponentException{
        return false;
    }
}
