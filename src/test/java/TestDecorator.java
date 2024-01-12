
import controllers.ManageMembershipController;
import engineering.decorator.MembershipInterface;
import exceptions.*;
import exceptions.dataException.DataFieldException;
import model.Membership;
import model.cupons.Cupon;
import model.cupons.DirectDiscountCupon;
import model.cupons.PercentageDiscountCoupon;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDecorator extends ManageMembershipController{
    @org.junit.jupiter.api.Test
    public void testDecorator() throws DataFieldException, NoUserFoundException, SQLException, AlreadyLoggedUserException, MembershipCouponNotFoundException, DecoratorNoBaseComponentException, CouponNotCumulativeException {
        Membership membership=new Membership(2,"Test abbonamento","Good for those committed to regular workout",100.0f,90,20);
        List<Cupon> coupons=new ArrayList<>();
        coupons.add(new PercentageDiscountCoupon(0,"Percentage Discount",null,1000,false,true,20F));
        coupons.add(new DirectDiscountCupon(2,"Direct Discount", null, 1500, false, true, 50F));
        MembershipInterface test=decoratorBuilder(membership,coupons);
        System.out.println(test.getBuildedName());
        assert test.getPrice()==30f;
    }
}
