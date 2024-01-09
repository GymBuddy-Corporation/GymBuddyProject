package model.cupons;

import exceptions.DecoratorNoBaseComponentException;

public class PointsCoupon extends Cupon{

    int pointAwarded;
    public PointsCoupon(int code,String name, String description, boolean forNewMembers, int pointToAward) {
        super(code,name, description, 0, forNewMembers, false);
        this.pointAwarded=pointToAward;
    }
    @Override
    public int getPoints() throws DecoratorNoBaseComponentException {
        int points=super.getPoints();
        return points+pointAwarded;
    }

    @Override
    public String getType() {
        return "Points promotion";
    }

    @Override
    public String getCouponsValue() {
        return String.valueOf(pointAwarded);
    }
}
