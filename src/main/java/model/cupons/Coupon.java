package model.cupons;

import engineering.decorator.MembershipInterface;
import exceptions.CouponNotCumulativeException;
import exceptions.DecoratorNoBaseComponentException;
import model.Membership;


public abstract class Coupon implements MembershipInterface {


    private final int pointsPrice;
    private final String name;
    private final String description;
    private final boolean isNotCumulative;
    protected MembershipInterface component;
    private  boolean onlyForNewMembers;

    Coupon(String name, String description, int pointsPrice, boolean forNewMembers, boolean isNotComulative){
        this.name=name;
        this.description=description;
        this.pointsPrice=pointsPrice;
        this.onlyForNewMembers=forNewMembers;
        this.isNotCumulative =isNotComulative;
    }

    public boolean isNotCumulative() {
        return isNotCumulative;
    }

    public abstract Coupon clone();
    public Coupon setComponent(MembershipInterface component) throws CouponNotCumulativeException, DecoratorNoBaseComponentException {
        if(this.isNotCumulative){
            if(component instanceof Membership membership){
                this.component=membership;
            }else{
                throw new CouponNotCumulativeException();
            }
        }
        if(component instanceof Coupon coupon){
            if(coupon.isNotCumulative){
                throw new CouponNotCumulativeException();
            }
            coupon.checkIfBaseExists();
            this.onlyForNewMembers=this.onlyForNewMembers| coupon.onlyForNewMembers;
        }
        this.component=component;
        return this;
    }
    private void checkIfBaseExists() throws DecoratorNoBaseComponentException {
        if(this.component==null){
            throw  new DecoratorNoBaseComponentException();
        }
    }
    @Override
    public float getPrice() {
        return this.component.getPrice();
    }

    @Override
    public int getPoints()  {
        if(this.component instanceof Membership )return 0;
        return this.component.getPoints();
    }

    @Override
    public int getDuration() {
        return component.getDuration();
    }

    @Override
    public String getBuildedName()  {
        return this.component.getBuildedName()+"+"+this.name;
    }

    @Override
    public boolean isForNewUsers() {

        return onlyForNewMembers ;
    }

    public String getDescription(){
        return this.description;
    }

    public String getName(){
        return name;
    }

    public int getPointsPrice() {
        return pointsPrice;
    }

    public boolean getOnlyForNewUsers(){
        return this.onlyForNewMembers;
    }

    public abstract String getType();
    public abstract String getCouponsValue();
}
