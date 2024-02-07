import engineering.decorator.MembershipBuilder;
import engineering.decorator.MembershipInterface;
import exceptions.CouponNotCumulativeException;
import exceptions.DecoratorNoBaseComponentException;
import exceptions.NoLoggedUserException;
import model.Membership;
import model.cupons.Coupon;
import model.cupons.DirectDiscountCoupon;
import model.cupons.PercentageDiscountCoupon;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDecorator{
    /*Test del alunno Alexandru Nazare 0307030*/

    public TestDecorator() {

    }

    @org.junit.jupiter.api.Test
     void testDecorator() throws DecoratorNoBaseComponentException, CouponNotCumulativeException, NoLoggedUserException {

        Membership membership=new Membership("Test abbonamento","Good for those committed to regular workout",100.0f,90,20);
        List<Coupon> coupons=new ArrayList<>();
        coupons.add(new PercentageDiscountCoupon("Percentage Discount",null,1000,false,false,20F));
        coupons.add(new DirectDiscountCoupon("Direct Discount", null, 1500, false, false, 50F));
        MembershipInterface test= MembershipBuilder.buildMembership(membership,coupons);
        System.out.println(test.getBuildedName());
        assertEquals(30f,test.getPrice());
    }
}