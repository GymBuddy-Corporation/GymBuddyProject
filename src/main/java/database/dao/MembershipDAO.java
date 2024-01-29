package database.dao;

import model.Membership;
import model.cupons.Cupon;
import model.cupons.DirectDiscountCupon;
import model.cupons.PercentageDiscountCoupon;
import model.cupons.PointsCoupon;

import java.util.ArrayList;
import java.util.List;

public class MembershipDAO {
    public List<Membership> getMembership(String gymName){
        List<Membership> membershipList=new ArrayList<Membership>();
        membershipList.add(new Membership(1,"1 Month packet","A good membership for starting workout",45.0f,30,10));
        membershipList.add(new Membership(2,"3 Months packet","Good for those committed to regular workout",120.0f,90,20));
        membershipList.add(new Membership(3,"6 Months packet","Motivate yourself to keep up for half a year",220.0f,180,30));
        membershipList.add(new Membership(4,"12 Months packet","Perfect deal for workout enthusiasts",400.0f,360,50));

        return membershipList;
    }

    public List<Cupon> getCoupons(String gymname){
        List<Cupon> coupons = new ArrayList<>();
        coupons.add(new PercentageDiscountCoupon(0,"Percentage Discount",null,1000,false,true,20.3F));
        coupons.add(new PointsCoupon(1,"Points Coupon", null,  false, 100));
        coupons.add(new DirectDiscountCupon(2,"Direct Discount", null, 1500, false, true, 50.5F));
        coupons.add(new PercentageDiscountCoupon(3,"Extra Percentage Discount",null,1200,false,true,25.5F));
        coupons.add(new PointsCoupon(4,"Extra Points Coupon", null,  false, 150));
        coupons.add(new DirectDiscountCupon(5,"Extra Direct Discount", null, 2000, false, true, 75.5F));
        return coupons;
    }



}