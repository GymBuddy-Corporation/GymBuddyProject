package exceptions;

public class CouponNotCumulativeException extends CostumException{
    public CouponNotCumulativeException(){
        super("This coupon is not comulative");
    }
}
