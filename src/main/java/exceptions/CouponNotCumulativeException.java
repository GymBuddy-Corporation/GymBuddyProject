package exceptions;

import engineering.decorator.MembershipInterface;

public class CouponNotCumulativeException extends CostumException{
    public MembershipInterface getMembership() {
        return membership;
    }

    private final MembershipInterface membership;
    public CouponNotCumulativeException(MembershipInterface membership){
        super("Some coupons wheere not cumulable");
        this.membership=membership;
    }
    public CouponNotCumulativeException(){
        super("This coupon is not comulative");
        membership=null;
    }
}
