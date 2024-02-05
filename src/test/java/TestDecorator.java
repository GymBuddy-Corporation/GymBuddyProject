
import beans.CredentialsBean;
import controllers.ManageMembershipController;
import controllers.UserAccessController;
import engineering.decorator.MembershipInterface;
import exceptions.*;
import exceptions.dataexception.DataFieldException;
import model.Membership;
import model.cupons.Coupon;
import model.cupons.DirectDiscountCoupon;
import model.cupons.PercentageDiscountCoupon;

import java.util.ArrayList;
import java.util.List;

public class TestDecorator{

    static class testController extends ManageMembershipController{

        public testController() throws NoLoggedUserException {
            super();
        }

        public MembershipInterface decorate(Membership b, List<Coupon>a) throws CouponNotCumulativeException, DecoratorNoBaseComponentException {
            return  this.decoratorBuilder(b,a);
        }
    }
    public TestDecorator() {
        UserAccessController controller1 = new UserAccessController();
        try {
            controller1.login(CredentialsBean.ctorWithSyntaxCheck("lux@gmail.com","Password123@"),false);
        }catch(AlreadyLoggedUserException e){

        } catch (DBUnrreachableException e) {
            throw new RuntimeException(e);
        } catch (DataFieldException e) {
            throw new RuntimeException(e);
        } catch (NoUserFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    public void testDecorator() throws DecoratorNoBaseComponentException, CouponNotCumulativeException, NoLoggedUserException {
        testController controller= new testController();

        Membership membership=new Membership("Test abbonamento","Good for those committed to regular workout",100.0f,90,20);
        List<Coupon> coupons=new ArrayList<>();
        coupons.add(new PercentageDiscountCoupon("Percentage Discount",null,1000,false,true,20F));
        coupons.add(new DirectDiscountCoupon("Direct Discount", null, 1500, false, false, 50F));
        MembershipInterface test=controller.decorate(membership,coupons);
        System.out.println(test.getBuildedName());
        assert test.getPrice()==30f;
    }
}