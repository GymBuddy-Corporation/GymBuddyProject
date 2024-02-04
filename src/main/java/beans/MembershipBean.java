package beans;

public class MembershipBean {
    public String getGymName() {
        return gymName;
    }


    private final String gymName;
    private final String name;
    private final String description;
    private final float price;
    private final int durationInDays;
    private final int pointsAwardedOnBuy;

    public boolean isOnlyForNewUsers() {
        return onlyForNewUsers;
    }

    private boolean onlyForNewUsers;

    public MembershipBean( String gymName, String name, String description, float price, int durationInDays, int pointsAwardedOnBuy){
        this.gymName=gymName;
        this.name = name;
        this.description = description;
        this.price = price;
        this.durationInDays = durationInDays;
        this.pointsAwardedOnBuy = pointsAwardedOnBuy;
        onlyForNewUsers=false;
    }
    public MembershipBean(String gymName, String name, float price, int durationInDays, int pointsAwardedOnBuy,boolean onlyForNewUsers){
        this.gymName=gymName;
        this.name = name;
        this.description=null;
        this.price = price;
        this.durationInDays = durationInDays;
        this.pointsAwardedOnBuy = pointsAwardedOnBuy;
        this.onlyForNewUsers=onlyForNewUsers;
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