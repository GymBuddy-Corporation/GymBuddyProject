package model;

import engineering.decorator.MembershipInterface;
import exceptions.DecoratorNoBaseComponentException;

import java.lang.reflect.Member;

public class Membership implements MembershipInterface {
    public int getCode() {
        return code;
    }

    private final int code;
    private final String name;
    private final String description;
    private final float price;
    private final int durationInDays;
    private final int pointsAwardedOnBuy;

    public Membership(int code, String name, String description, float price, int duration, int points){
        this.code = code;
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

    @Override
    public boolean isForNewUsers() throws DecoratorNoBaseComponentException{
        return false;
    }
}
