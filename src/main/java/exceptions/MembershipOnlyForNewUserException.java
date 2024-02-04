package exceptions;

public class MembershipOnlyForNewUserException extends CostumException{

    public MembershipOnlyForNewUserException() {
        super("This Membership is only for new users");
    }
}
