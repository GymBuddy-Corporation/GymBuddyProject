package database.dao;

import model.cupons.Cupon;
import model.cupons.DirectDiscountCupon;
import model.cupons.PercentageDiscountCoupon;
import model.cupons.PointsCoupon;

import java.util.ArrayList;
import java.util.List;

public class CouponsDAO {
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
