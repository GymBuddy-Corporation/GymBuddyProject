package model.cupons;

import engineering.decorator.MembershipInterface;
import exceptions.CouponNotCumulativeException;
import exceptions.DecoratorNoBaseComponentException;
import model.Membership;


public abstract class Cupon implements MembershipInterface {
    private final int pointsPrice;
    protected MembershipInterface component;
    private final String name;
    private final String description;
    private final boolean onlyForNewMembers;
    private final boolean isCumulative;
    Cupon(String name,String description,int pointsPrice,boolean forNewMembers,boolean isComulative){
        this.name=name;
        this.description=description;
        this.pointsPrice=pointsPrice;
        this.onlyForNewMembers=forNewMembers;
        this.isCumulative=isComulative;
    }
    public void setComponent(MembershipInterface component) throws CouponNotCumulativeException {
        if(this.isCumulative){
            if(component instanceof Membership membership){
                this.component=membership;
            }else{
                throw new CouponNotCumulativeException();
            }
        }
        this.component=component;
    }
    private void checkIfBaseExists() throws DecoratorNoBaseComponentException {
        if(this.component==null){
            throw  new DecoratorNoBaseComponentException();
        }
    }
    @Override
    public float getPrice() throws DecoratorNoBaseComponentException {
        checkIfBaseExists();
        return this.component.getPrice();
    }

    @Override
    public int getPoints() throws DecoratorNoBaseComponentException {
        checkIfBaseExists();
        return this.component.getPoints();
    };

    @Override
    public int getDuration() throws DecoratorNoBaseComponentException {
        checkIfBaseExists();
        return component.getDuration();
    };
    public int getPointsPrice() {
        return pointsPrice;
    }
    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public String getDescription(){
        return this.description;
    }
    public boolean isOnlyForNewUsers(){
        return this.onlyForNewMembers;
    }

}
