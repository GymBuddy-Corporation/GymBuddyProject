package engineering.decorator;

import exceptions.CouponNotCumulativeException;
import exceptions.DecoratorNoBaseComponentException;
import model.Membership;
import model.cupons.Coupon;

import java.util.List;

public class MembershipBuilder {
    private MembershipBuilder() {
        throw new IllegalStateException("Utility class");
    }
    public static MembershipInterface buildMembership(Membership membership, List<Coupon> coupons) throws DecoratorNoBaseComponentException, CouponNotCumulativeException {
        MembershipInterface finalMembership=membership;
        for(Coupon temp:coupons){
            finalMembership=temp.setComponent(finalMembership);
        }
        return finalMembership;
    }
    }

