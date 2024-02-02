package model.cupons;

import exceptions.DecoratorNoBaseComponentException;


public class DirectDiscountCupon extends Cupon {
    private static final float MIN_PRICE = 0;
    private final float discountValue;

    public DirectDiscountCupon(String name, String description, int pointsPrice, boolean forNewMembers, boolean isCoumulable, float discountValue) {
        super(name, description, pointsPrice, forNewMembers, isCoumulable);
        this.discountValue = discountValue;
    }

    @Override
    public float getPrice() throws DecoratorNoBaseComponentException {
        float price = super.getPrice();
        float priceAfterDiscount = price - discountValue;
        return Math.max(priceAfterDiscount, MIN_PRICE);
    }

    @Override
    public int getPoints() throws DecoratorNoBaseComponentException {
        return super.getPoints()-this.getPointsPrice();
    }

    @Override
    public String getType() {
        return "Direct Discount";
    }

    @Override
    public String getCouponsValue() {
        return String.valueOf(discountValue);
    }

}
