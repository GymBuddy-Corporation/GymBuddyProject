package exceptions;

import beans.MembershipBean;
import engineering.decorator.MembershipInterface;

public class CouponNotFullyAppliedException extends CostumException{
    MembershipBean membershipBean;

    public MembershipBean getMembershipBean(){
        return membershipBean;
    }
    public CouponNotFullyAppliedException(CouponNotCumulativeException e,String name) throws DecoratorNoBaseComponentException {
        super("Some coupons were not applied");
        if(e.getMembership()==null)membershipBean=null;
        MembershipInterface temp=e.getMembership();
        membershipBean=new MembershipBean(name,temp.getName(), temp.getPrice(), temp.getDuration(), temp.getPoints());

    }
}
