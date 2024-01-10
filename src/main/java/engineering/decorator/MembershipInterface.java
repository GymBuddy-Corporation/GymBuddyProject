package engineering.decorator;

import exceptions.DecoratorNoBaseComponentException;

public interface MembershipInterface {
    float getPrice() throws DecoratorNoBaseComponentException;
    int getPoints() throws DecoratorNoBaseComponentException;
    int getDuration() throws DecoratorNoBaseComponentException;
    String getName() throws DecoratorNoBaseComponentException;

    String getBuildedName()throws DecoratorNoBaseComponentException;
    String getDescription();
    boolean isForNewUsers() throws DecoratorNoBaseComponentException;
}
