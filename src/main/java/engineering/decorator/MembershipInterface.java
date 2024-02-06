package engineering.decorator;

public interface MembershipInterface {
    float getPrice();
    int getPoints() ;
    int getDuration() ;
    String getBuildedName();

    boolean isForNewUsers() ;
}
