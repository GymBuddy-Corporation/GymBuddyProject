package exceptions;

public class MembershipCouponNotFoundException extends CostumException{
    public MembershipCouponNotFoundException(){
        super("Cant find selected membership/coupon");
    }
}
