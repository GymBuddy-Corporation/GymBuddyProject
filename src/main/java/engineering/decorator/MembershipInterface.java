package engineering.decorator;

import exceptions.DecoratorNoBaseComponentException;

public interface MembershipInterface {
    float getPrice();
    int getPoints() ;
    int getDuration() ;
    String getName();

    String getBuildedName();
    String getDescription();
    boolean isForNewUsers() ;
}
