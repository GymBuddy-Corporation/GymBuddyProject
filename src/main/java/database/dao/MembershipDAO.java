package database.dao;

import model.Membership;
import model.cupons.Coupon;
import model.cupons.DirectDiscountCoupon;
import model.cupons.PercentageDiscountCoupon;
import model.cupons.PointsCoupon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MembershipDAO {
    public List<Membership> getMembership(String gymName){
        //The membership are not pre mande, gyms make them but for now we have this premade memberships till the use case is implemented
        if(gymName==null)return Collections.emptyList();
        List<Membership> membershipList=new ArrayList<Membership>();
        membershipList.add(new Membership("1 Month packet","A good membership for starting workout",45.0f,30,10));
        membershipList.add(new Membership("3 Months packet","Good for those committed to regular workout",120.0f,90,20));
        membershipList.add(new Membership("6 Months packet","Motivate yourself to keep up for half a year",220.0f,180,30));
        membershipList.add(new Membership("12 Months packet","Perfect deal for workout enthusiasts",400.0f,360,50));

        return membershipList;
    }

    public List<Coupon> getCoupons(String gymName){
        //The coupons are not pre made, gyms make them but for now we have this premade coupons till the use case is implemented

        if(gymName==null)return Collections.emptyList();
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new PercentageDiscountCoupon("Percentage Discount",null,1000,false,false,20.3F));
        coupons.add(new PointsCoupon("Points Coupon", null,  false, 100));
        coupons.add(new DirectDiscountCoupon("Direct Discount", null, 1500, false, false, 50.5F));
        coupons.add(new PercentageDiscountCoupon("Extra Percentage Discount",null,1200,false,true,25.5F));
        coupons.add(new PointsCoupon("Extra Points Coupon", null,  false, 150));
        coupons.add(new DirectDiscountCoupon("Extra Direct Discount", null, 2000, false, false, 75.5F));
        return coupons;
    }



}