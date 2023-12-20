package model.cupons;

import exceptions.DecoratorNoBaseComponentException;

public class PercentageDiscountCoupon extends Cupon{
    private final float discountPercentage;

    PercentageDiscountCoupon(String name, String description, int pointsPrice, boolean forNewMembers, boolean isCoumulable, float discountPecentage) {
        super(name, description, pointsPrice, forNewMembers, isCoumulable);
        this.discountPercentage=discountPecentage;
    }

    @Override
    public float getPrice() throws DecoratorNoBaseComponentException {
        float price = super.getPrice();
        return price*discountPercentage/100f;
    }
}
