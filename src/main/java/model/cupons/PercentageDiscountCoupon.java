package model.cupons;

import exceptions.DecoratorNoBaseComponentException;

public class PercentageDiscountCoupon extends Cupon{
    private final float discountPercentage;

    public PercentageDiscountCoupon(String name, String description, int pointsPrice, boolean forNewMembers, boolean isCoumulable, float discountPecentage) {
        super(name, description, pointsPrice, forNewMembers, isCoumulable);
        this.discountPercentage=discountPecentage;
    }

    @Override
    public float getPrice() throws DecoratorNoBaseComponentException {
        float price = super.getPrice();
        return price-price*discountPercentage/100f;
    }

    @Override
    public int getPoints() throws DecoratorNoBaseComponentException {
        return super.getPoints()-this.getPointsPrice();
    }

    @Override
    public String getType() {
        return "Percentage Discount";
    }

    @Override
    public String getCouponsValue() {
        return String.valueOf(discountPercentage);
    }
}
