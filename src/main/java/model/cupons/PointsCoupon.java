package model.cupons;


public class PointsCoupon extends Coupon {

    int pointAwarded;
    public PointsCoupon(String name, String description, boolean forNewMembers, int pointToAward) {
        super(name, description, 0, forNewMembers, true);
        this.pointAwarded=pointToAward;
    }
    public Coupon clone() {
        return new PointsCoupon( super.getName(),super.getDescription(),super.isForNewUsers(),pointAwarded);
    }
    @Override
    public int getPoints() {
        int points=super.getPoints();
        return points+pointAwarded;
    }

    @Override
    public String getType() {
        return "Points promotion";
    }

    @Override
    public String getCouponsValue() {
        return String.valueOf(pointAwarded)+" points";
    }
}
