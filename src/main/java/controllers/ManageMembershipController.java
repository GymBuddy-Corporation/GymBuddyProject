package controllers;

import beans.*;
import boundaries.PaymentSystemBoundary;
import database.dao.GymDAO;
import database.dao.MembershipDAO;
import engineering.LoggedAthleteSingleton;
import engineering.decorator.MembershipInterface;
import exceptions.*;
import model.Athlete;
import model.Gym;
import model.Membership;
import model.cupons.Cupon;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

public class ManageMembershipController {
    protected final GymDAO gymDao;
    private static Gym selectedGym;
    LoggedAthleteSingleton singletonInstance;
    public ManageMembershipController() throws NoLoggedUserException {
        singletonInstance =LoggedAthleteSingleton.getSingleton();
        if(singletonInstance==null)throw new NoLoggedUserException();
        gymDao = new GymDAO();
        resetSelectedGym();
    }

    private static void resetSelectedGym(){
        selectedGym=null;
    }

    private static void setSelectedGym(Gym gym){
        selectedGym=gym;
    }

    public List<MembershipBean> getMembershipList(GymInfoBean onlyGymNameBean) throws  NoUserFoundException {
        loadSelectedGym(onlyGymNameBean);
        List<Membership> listOfMemberships = selectedGym.getMemberships();
        List<MembershipBean> beanList = new ArrayList<>();
        for (Membership membership : listOfMemberships) {
            beanList.add(new MembershipBean(
                    membership.getCode(),
                    onlyGymNameBean.getName(),
                    membership.getName(),
                    membership.getDescription(),
                    membership.getPrice(),
                    membership.getDuration(),
                    membership.getPoints()));
        }
        return beanList;
    }

    public List<CuponsBean> getCouponsList(GymInfoBean onlyNameGym) throws NoUserFoundException {
        loadSelectedGym(onlyNameGym);
        List<Cupon> listOfCoupons=selectedGym.getCoupons();
        List <CuponsBean> listOfCouponsBeans=new ArrayList<>();
        for(Cupon cupon: listOfCoupons ){
            listOfCouponsBeans.add(new CuponsBean(cupon.getCode(),
                    cupon.getPointsPrice(),
                    cupon.getName(),
                    cupon.getDescription(),
                    cupon.getOnlyForNewUsers(),
                    cupon.isCumulative(),
                    cupon.getType(),
                    cupon.getCouponsValue()));
        }
        return listOfCouponsBeans;
    }

    private void loadSelectedGym(GymInfoBean name) throws  NoUserFoundException {
            if(selectedGym!=null && Objects.equals(selectedGym.getGymName(), name.getName()))return;
        try {
            setSelectedGym(gymDao.getGymByName(name.getName()));
        } catch (SQLException e) {
            throw new NoUserFoundException();
        }
        MembershipDAO memDao=new MembershipDAO();
            selectedGym.setMemberships(memDao.getMembership(selectedGym.getGymName()));
            selectedGym.setCoupons(memDao.getCoupons(selectedGym.getGymName()));
    }

    public MembershipBean applyCouponsToMembership(GymInfoBean onlyName,MembershipBean selectedMembership,List<CuponsBean> selectedCoupons) throws MembershipCouponNotFoundException, DecoratorNoBaseComponentException, CouponNotFullyAppliedException, NoUserFoundException {
            loadSelectedGym(onlyName);
            List<Membership> membershipsList=selectedGym.getMemberships();
            List<Cupon> cuponsList=selectedGym.getCoupons();
            Membership membership=null;
            for(Membership temp:membershipsList){
                if(selectedMembership.getCode()==temp.getCode()){
                    membership=temp;
                    break;
                }
            }
            if(membership==null)throw new MembershipCouponNotFoundException();

            List<Cupon> couponsToApply=new ArrayList<>();
            Iterator<Cupon> couponIterator= cuponsList.iterator();
            while(couponIterator.hasNext()){
                Cupon couponTemp=couponIterator.next();
                Iterator<CuponsBean> couponBeanIterator= selectedCoupons.iterator();
                while (couponBeanIterator.hasNext()){
                    CuponsBean couponBeanTemp=couponBeanIterator.next();
                    if(couponBeanTemp.getCode()==couponTemp.getCode()){
                        couponsToApply.add(couponTemp);
                        couponBeanIterator.remove();
                        couponIterator.remove();
                        break;
                    }
                }
            }
            if(!cuponsList.isEmpty())throw new MembershipCouponNotFoundException();

            MembershipInterface finalMembership = null;
            try {
                finalMembership = decoratorBuilder(membership, couponsToApply);
            }catch (CouponNotCumulativeException e) {
                LoggedAthleteSingleton.getSingleton().setMembership(e.getMembership(),onlyName.getName());
                throw new CouponNotFullyAppliedException(e,onlyName.getName());
            }
            LoggedAthleteSingleton.getSingleton().setMembership(finalMembership,onlyName.getName());
            return new MembershipBean(onlyName.getName(),finalMembership.getName(),finalMembership.getPrice(),finalMembership.getDuration(), finalMembership.getPoints());
    }

    protected MembershipInterface decoratorBuilder(Membership membership,List<Cupon> coupons) throws CouponNotCumulativeException {
        MembershipInterface finalMembership=membership;
        boolean wasExceptionTriggered=false;
        for(Cupon temp:coupons){
            try {
                finalMembership=temp.setComponent(finalMembership);
            } catch (CouponNotCumulativeException e) {
                wasExceptionTriggered=true;
            }
        }
        if(wasExceptionTriggered)throw new CouponNotCumulativeException(finalMembership);
        return finalMembership;
       }


    public PaymentConfirmationBean payNewMembership(GymInfoBean onlyNameGym,MembershipBean membershipBean){

                return null;
    }

    public PaymentConfirmationBean payNewMebership(GymInfoBean onlyNameGym,MembershipBean membershipBean,CardInfoBean cardInfoBean,boolean rememberCard){
        return null;
    }

    private PaymentConfirmationBean makePayment(GymInfoBean onlyNameGym,MembershipBean membershipBean,CardInfoBean cardInfoBean) throws PaymentFailedException, FailedToSaveNewMembership {
        PaymentBean paymentBean=makePaymentBean(onlyNameGym,membershipBean);
        PaymentSystemBoundary boundary=new PaymentSystemBoundary(paymentBean);
        PaymentConfirmationBean paymentConfirmationBean=boundary.pay();
        try{
            saveNewMemberShipToPersistence(paymentConfirmationBean,onlyNameGym,membershipBean);
        }catch (FailedToSaveNewMembership e){
            boundary.refund(paymentConfirmationBean);
            throw new FailedToSaveNewMembership();
        }
        return paymentConfirmationBean;
    }

    private void saveNewMemberShipToPersistence(PaymentConfirmationBean paymentConfirmationBean,GymInfoBean onlyNameGym,MembershipBean membershipBean) throws FailedToSaveNewMembership {
        throw new FailedToSaveNewMembership();
    }

    private PaymentBean makePaymentBean(GymInfoBean onlyNameGym,MembershipBean membershipBean) throws PaymentFailedException {
        if(!Objects.equals(onlyNameGym.getName(), singletonInstance.getGymToSubscribe())){
            throw new PaymentFailedException();
        }
        if(!Objects.equals(selectedGym.getGymName(),onlyNameGym.getName()))throw new PaymentFailedException();
        try {
            if(!Objects.equals(membershipBean.getName(), singletonInstance.getMembership().getBuildedName())) {
                throw new PaymentFailedException();
            }
        } catch (DecoratorNoBaseComponentException e) {
            throw new PaymentFailedException();
        }
        return getPaymentBean(membershipBean);
    }

    @NotNull
    private PaymentBean getPaymentBean(MembershipBean membershipBean) {
        Athlete athlete=singletonInstance.getUser();
        PaymentBean paymentBean=new PaymentBean(
                selectedGym.getIban(),
                selectedGym.getGymName(),
                membershipBean.getName(),
                membershipBean.getPrice(),
                new CardInfoBean(
                        athlete.getCardNumber(),
                        athlete.getCardExpirationMonth(),
                        athlete.getCardExpirationYear(),
                        athlete.getName(),
                        athlete.getSurname()),
                athlete.getEmail());
        return paymentBean;
    }


    private boolean stringExistsInText(String toBeSearched, String text) {
        return Pattern.compile(Pattern.quote(toBeSearched), Pattern.CASE_INSENSITIVE).matcher(text).find();
    }

    public List<GymInfoBean> searchGym(SearchGymBean searchBean) {
        List<Gym> gyms = gymDao.loadAllGyms();
        List<GymInfoBean> beanList = new ArrayList<>();
        for (Gym gym : gyms) {
            boolean isNameValid = (searchBean.getName() == null) || stringExistsInText(searchBean.getName(), gym.getGymName());
            boolean isAddressValid = (searchBean.getAddress() == null) || stringExistsInText(searchBean.getAddress(), gym.getAddress());
            boolean isCityValid = (searchBean.getCity() == null) || stringExistsInText(searchBean.getCity(), gym.getCity());
            boolean isCountryValid = (searchBean.getCountry() == null) || stringExistsInText(searchBean.getCountry(), gym.getCountry());

            if (isNameValid && isAddressValid && isCityValid && isCountryValid) {
                beanList.add(new GymInfoBean(gym.getGymName(),
                        gym.getAddress(),
                        gym.getCity(),
                        gym.getIban(),
                        gym.getCountry()));
            }
        }
        return beanList;
    }
}