package model.cupons;

import exceptions.DecoratorNoBaseComponentException;

public class PointsCoupon extends Cupon{

    int pointAwarded;
    PointsCoupon(String name, String description, int pointsPrice, boolean forNewMembers, int pointToAward) {
        super(name, description, pointsPrice, forNewMembers, false);
        this.pointAwarded=pointToAward;
    }
    @Override
    public int getPoints() throws DecoratorNoBaseComponentException {
        int points=super.getPoints();
        return points+pointAwarded;
    }
}
