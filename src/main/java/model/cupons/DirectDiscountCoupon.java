package model.cupons;



public class DirectDiscountCoupon extends Coupon {
    private static final float MIN_PRICE = 0;
    private final float discountValue;

    public DirectDiscountCoupon(String name, String description, int pointsPrice, boolean forNewMembers, boolean isNotCoumulable, float discountValue) {
        super(name, description, pointsPrice, forNewMembers, isNotCoumulable);
        this.discountValue = discountValue;
    }

    @Override
    public Coupon clone() {
        return new DirectDiscountCoupon( super.getName(),super.getDescription(),super.getPointsPrice(),super.isForNewUsers(),super.isNotCumulative(),discountValue);
    }

    @Override
    public float getPrice() {
        float price = super.getPrice();
        float priceAfterDiscount = price - discountValue;
        return Math.max(priceAfterDiscount, MIN_PRICE);
    }

    @Override
    public int getPoints() {
        return super.getPoints()-this.getPointsPrice();
    }

    @Override
    public String getType() {
        return "Direct Discount";
    }

    @Override
    public String getCouponsValue() {
        return String.valueOf(discountValue)+" $";
    }

}
