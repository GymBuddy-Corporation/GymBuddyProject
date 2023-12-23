package beans;

public class MembershipBean {
    private final String gymName;
    private final String name;
    private final String description;
    private final float price;
    private final int durationInDays;
    private final int pointsAwardedOnBuy;

    public MembershipBean(String gymName,String name, String description, float price, int durationInDays, int pointsAwardedOnBuy){
        this.gymName=gymName;
        this.name = name;
        this.description = description;
        this.price = price;
        this.durationInDays = durationInDays;
        this.pointsAwardedOnBuy = pointsAwardedOnBuy;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public int getPointsAwardedOnBuy() {
        return pointsAwardedOnBuy;
    }
}