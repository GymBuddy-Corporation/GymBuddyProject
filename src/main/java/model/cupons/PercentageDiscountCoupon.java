package model.cupons;


public class PercentageDiscountCoupon extends Coupon {
    private final float discountPercentage;

    public PercentageDiscountCoupon(String name, String description, int pointsPrice, boolean forNewMembers, boolean isNotCoumulable, float discountPecentage) {
        super(name, description, pointsPrice, forNewMembers, isNotCoumulable);
        this.discountPercentage=discountPecentage;
    }
    public Coupon clone() {
        return new PercentageDiscountCoupon( super.getName(),super.getDescription(),super.getPointsPrice(),super.isForNewUsers(),super.isNotCumulative(),discountPercentage);
    }
    @Override
    public float getPrice() {
        float price = super.getPrice();
        return Math.round(price-price*discountPercentage/100f);
    }

    @Override
    public int getPoints() {
        return super.getPoints()-this.getPointsPrice();
    }

    @Override
    public String getType() {
        return "Percentage Discount";
    }

    @Override
    public String getCouponsValue() {
        return String.valueOf(discountPercentage)+"%";
    }
}
