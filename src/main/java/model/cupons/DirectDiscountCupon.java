package model.cupons;

import exceptions.DecoratorNoBaseComponentException;


public class DirectDiscountCupon extends Cupon {
    private static final float MIN_PRICE = 0;
    private final float discountValue;

    DirectDiscountCupon(String name, String description, int pointsPrice, boolean forNewMembers, boolean isCoumulable, float discountValue) {
        super(name, description, pointsPrice, forNewMembers, isCoumulable);
        this.discountValue = discountValue;
    }

    @Override
    public float getPrice() throws DecoratorNoBaseComponentException {
        float price = super.getPrice();
        float priceAfterDiscount = price - discountValue;
        return priceAfterDiscount > MIN_PRICE ? priceAfterDiscount : MIN_PRICE;
    }
}
