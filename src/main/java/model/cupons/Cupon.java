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

    public boolean isCumulative() {
        return isCumulative;
    }

    private final boolean isCumulative;
    Cupon( String name, String description, int pointsPrice, boolean forNewMembers, boolean isComulative){
        this.name=name;
        this.description=description;
        this.pointsPrice=pointsPrice;
        this.onlyForNewMembers=forNewMembers;
        this.isCumulative=isComulative;
    }
    public Cupon setComponent(MembershipInterface component) throws CouponNotCumulativeException {
        if(!this.isCumulative){
            if(component instanceof Membership membership){
                this.component=component;
            }else{
                throw new CouponNotCumulativeException();
            }
        }
        if(component instanceof Cupon cupon){
            if(!cupon.isCumulative){
                throw new CouponNotCumulativeException();
            }
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
    public float getPrice() throws DecoratorNoBaseComponentException {
        checkIfBaseExists();
        return this.component.getPrice();
    }
    @Override
    public String getName(){
        return name;
    }

    @Override
    public int getPoints() throws DecoratorNoBaseComponentException {
        checkIfBaseExists();
        if(this.component instanceof Membership )return 0; //In caso si utilizzino i coupon non si puo ottenere pure i punti del abbonamento
        return this.component.getPoints();
    }

    @Override
    public int getDuration() throws DecoratorNoBaseComponentException {
        checkIfBaseExists();
        return component.getDuration();
    }

    public int getPointsPrice() {
        return pointsPrice;
    }
    @Override
    public String getBuildedName() throws DecoratorNoBaseComponentException {
        checkIfBaseExists();
        return this.component.getBuildedName()+"+"+this.name;
    }

    @Override
    public String getDescription(){
        return this.description;
    }
    public boolean getOnlyForNewUsers(){
        return this.onlyForNewMembers;
    }


    @Override
    public boolean isForNewUsers() throws DecoratorNoBaseComponentException {
        checkIfBaseExists();
        return onlyForNewMembers || this.component.isForNewUsers();
    }
    public abstract String getType();
    public abstract String getCouponsValue();
}
