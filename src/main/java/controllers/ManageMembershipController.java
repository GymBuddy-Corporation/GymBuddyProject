package controllers;

import beans.*;
import boundaries.PaymentSystemBoundary;
import database.dao.AthleteDAO;
import database.dao.GymDAO;
import database.dao.MembershipDAO;
import database.dao.TrainerDAO;
import engineering.LoggedAthleteSingleton;
import engineering.decorator.MembershipInterface;
import exceptions.*;
import model.*;
import model.cupons.Coupon;
import model.record.Card;
import org.jetbrains.annotations.NotNull;
import java.sql.SQLException;
import java.time.Instant;
import java.time.YearMonth;
import java.util.*;
import java.util.regex.Pattern;

public class ManageMembershipController {
    protected final GymDAO gymDao;
    private static Gym selectedGym;
    private final boolean hasCard;
    LoggedAthleteSingleton singletonInstance;
    public ManageMembershipController() throws NoLoggedUserException {
        singletonInstance =LoggedAthleteSingleton.getSingleton();
        if(singletonInstance==null)throw new NoLoggedUserException();
        hasCard=new AthleteDAO().loadCard(singletonInstance.getUser());
        gymDao = new GymDAO();
        resetSelectedGym();
    }

    private static void resetSelectedGym(){
        selectedGym=null;
    }
    public static ActiveMembershipBean getActiveMembership(){
        Athlete me=LoggedAthleteSingleton.getSingleton().getUser();
        return new ActiveMembershipBean(me.gymName(), me.getPoints(), me.getExpirationOfmembership());
    }
    private static void setSelectedGym(Gym gym){
        selectedGym=gym;
    }

    public List<MembershipBean> getMembershipList(GymInfoBean onlyGymNameBean) throws NoUserFoundException, DBUnrreachableException {
        loadSelectedGym(onlyGymNameBean);
        List<Membership> listOfMemberships = selectedGym.getMemberships();
        List<MembershipBean> beanList = new ArrayList<>();
        for (Membership membership : listOfMemberships) {
            beanList.add(new MembershipBean(
                    onlyGymNameBean.getName(),
                    membership.getName(),
                    membership.getDescription(),
                    membership.getPrice(),
                    membership.getDuration(),
                    membership.getPoints()));
        }
        return beanList;
    }

    public List<CuponsBean> getCouponsList(GymInfoBean onlyNameGym) throws NoUserFoundException, DBUnrreachableException {
        loadSelectedGym(onlyNameGym);
        List<Coupon> listOfCoupons=selectedGym.getCoupons();
        List <CuponsBean> listOfCouponsBeans=new ArrayList<>();
        for(Coupon coupon : listOfCoupons ){
            CuponsBean temp=new CuponsBean(
                    coupon.getPointsPrice(),
                    coupon.getName(),
                    coupon.getOnlyForNewUsers(),
                    !coupon.isNotCumulative(),
                    coupon.getType(),
                    coupon.getCouponsValue());
            if(!Objects.equals(coupon.getDescription(), ""))temp.setDescription(coupon.getDescription());
            listOfCouponsBeans.add(temp);
        }
        return listOfCouponsBeans;
    }

    private void loadSelectedGym(GymInfoBean name) throws NoUserFoundException, DBUnrreachableException {
            if(selectedGym!=null && Objects.equals(selectedGym.getGymName(), name.getName()))return;
        try {
            setSelectedGym(gymDao.getGymByName(name.getName()));
        } catch (DBUnrreachableException e) {
            throw new DBUnrreachableException();
        }
        MembershipDAO memDao=new MembershipDAO();
        selectedGym.setMemberships(memDao.getMembership(selectedGym.getGymName()));
        selectedGym.setCoupons(memDao.getCoupons(selectedGym.getGymName()));
    }

    public MembershipBean applyCouponsToMembership(GymInfoBean onlyName,MembershipBean selectedMembership,List<CuponsBean> selectedCoupons) throws MembershipCouponNotFoundException, DecoratorNoBaseComponentException, NoUserFoundException, CouponNotCumulativeException, DBUnrreachableException {
            loadSelectedGym(onlyName);
            List<Membership> membershipsList=selectedGym.getMemberships();
            List<Coupon> cuponsList=selectedGym.getCoupons();
            Membership membership=null;
            for(Membership temp:membershipsList){
                if(Objects.equals(selectedMembership.getName(), temp.getName())){
                    membership=temp;
                    break;
                }
            }
            if(membership==null)throw new MembershipCouponNotFoundException();
            List<Coupon> couponsToApply=new ArrayList<>();
            int i=0;
            Iterator<CuponsBean> couponBeanIterator= selectedCoupons.iterator();
            while(couponBeanIterator.hasNext()){
                Iterator<Coupon> couponIterator= cuponsList.iterator();
                CuponsBean couponBeanTemp=couponBeanIterator.next();
                while (couponIterator.hasNext()){
                    Coupon couponTemp= couponIterator.next().clone();
                    if(Objects.equals(couponBeanTemp.getName(), couponTemp.getName())){
                        couponsToApply.add(couponTemp);
                        i++;
                        break;
                    }
                }
            }
            if(i-selectedCoupons.size()!=0)throw new MembershipCouponNotFoundException();
            MembershipInterface finalMembership = decoratorBuilder(membership, couponsToApply);
            return new MembershipBean(onlyName.getName(),finalMembership.getName(),finalMembership.getPrice(),finalMembership.getDuration(), finalMembership.getPoints(), finalMembership.isForNewUsers());
    }

    protected MembershipInterface decoratorBuilder(Membership membership,List<Coupon> coupons) throws CouponNotCumulativeException, DecoratorNoBaseComponentException {
        MembershipInterface finalMembership=membership;
        for(Coupon temp:coupons){
                finalMembership=temp.setComponent(finalMembership);
        }
        return finalMembership;
       }


    public PaymentConfirmationBean payNewMembership(GymInfoBean onlyNameGym,MembershipBean membershipBean,List<CuponsBean> cupons) throws CostumException {
        if(!hasCard) throw new CostumException("donest have a saved card");
        return makePayment(onlyNameGym,membershipBean,cupons,singletonInstance.getUser().getCard());
    }

    public PaymentConfirmationBean payNewMebership(GymInfoBean onlyNameGym,MembershipBean membershipBean,List<CuponsBean> cupons,CardInfoBean cardInfoBean,boolean rememberCard) throws MembershipCouponNotFoundException, DecoratorNoBaseComponentException, FailedToSaveNewMembership, NoUserFoundException, MembershipOnlyForNewUserException, PaymentFailedException, CouponNotCumulativeException, DBUnrreachableException {
        Card card=new Card(cardInfoBean.getCardNumber(),cardInfoBean.getNameOfOwner(),cardInfoBean.getSurnameOfOwenr(),YearMonth.of(Integer.parseInt(cardInfoBean.getYearOfExpiration()),Integer.parseInt(cardInfoBean.getMonthOfExpiration())));
        if(rememberCard)new AthleteDAO().saveCard(card);
        return makePayment(onlyNameGym,membershipBean,cupons,card);
    }

    private PaymentConfirmationBean makePayment(GymInfoBean onlyNameGym,MembershipBean membershipBean,List<CuponsBean> cupons,Card card) throws PaymentFailedException, FailedToSaveNewMembership, MembershipCouponNotFoundException, DecoratorNoBaseComponentException, NoUserFoundException, CouponNotCumulativeException, MembershipOnlyForNewUserException, DBUnrreachableException {
        MembershipBean finalMembership=applyCouponsToMembership(onlyNameGym,membershipBean,cupons);
        if(Objects.equals(onlyNameGym.getName(), getActiveMembership().getGymName()) && finalMembership.isOnlyForNewUsers()) throw new MembershipOnlyForNewUserException();
        Wallet toSave=updateedUserWallet(finalMembership);
        PaymentBean paymentBean=getPaymentBean(finalMembership,card);
        PaymentSystemBoundary boundary=new PaymentSystemBoundary(paymentBean,new CardInfoBean(card.cardNumber(), String.valueOf(card.cardExpirationDate().getMonthValue()),String.valueOf(card.cardExpirationDate().getYear()), card.name(), card.surname()));
        PaymentConfirmationBean paymentConfirmationBean=boundary.pay();
        try{
            saveNewMemberShipToPersistence(toSave,LoggedAthleteSingleton.getSingleton().getUser());
        }catch (FailedToSaveNewMembership e){
            boundary.refund(paymentConfirmationBean);
            throw new FailedToSaveNewMembership();
        }
        singletonInstance.getUser().setWallet(toSave);
        return paymentConfirmationBean;
    }

    private Wallet updateedUserWallet(MembershipBean bean) throws FailedToSaveNewMembership,  NoUserFoundException, DBUnrreachableException {
        Athlete me=LoggedAthleteSingleton.getSingleton().getUser();
        if(me.getPoints()+bean.getPointsAwardedOnBuy()<0)throw new FailedToSaveNewMembership("Not Enough points for the membership");
        Date newDate;
        if(me.getExpirationOfmembership()==null || !Objects.equals(me.gymName(), bean.getGymName())) {
            newDate=new Date();
        }
        else{
            newDate=me.getExpirationOfmembership();
        }
        Calendar newDateCalender = Calendar. getInstance();
        newDateCalender.setTime(newDate);
        newDateCalender.add(Calendar.HOUR,bean.getDurationInDays()*24);
        Gym gymToAdd=new GymDAO().getGymByName(bean.getGymName());
        Trainer trainerToAdd;
        TrainerDAO trainerDAO=new TrainerDAO();
        if(me.gymName()==null || Objects.equals(me.gymName(), "") || !Objects.equals(me.gymName(), bean.getGymName())){
                 trainerToAdd=trainerDAO.loadTrainerToAdd(gymToAdd);
        }else{
            trainerToAdd=trainerDAO.loadTrainer(me.getTrainerFc(),"fc");
        }
        return new Wallet(Date.from(Instant.now()),newDateCalender.getTime(),gymToAdd,bean.getPointsAwardedOnBuy()+me.getPoints(),bean.getGymName(),bean.getPrice(),trainerToAdd);
    }

    private void saveNewMemberShipToPersistence(Wallet membership,Athlete me) throws FailedToSaveNewMembership, DBUnrreachableException {
        new AthleteDAO().saveWallet(membership,me);
    }


    @NotNull
    private PaymentBean getPaymentBean(MembershipBean membershipBean,Card card) {
        Athlete athlete=singletonInstance.getUser();
       return new PaymentBean(
                selectedGym.getIban(),
                selectedGym.getGymName(),
                membershipBean.getName(),
                membershipBean.getPrice(),
                new CardInfoBean(
                        card.cardNumber(),
                        String.valueOf(card.cardExpirationDate().getMonthValue()),
                        String.valueOf(card.cardExpirationDate().getMonthValue()),
                        card.name(),
                        card.surname()),
                athlete.getEmail());
    }


    private boolean stringExistsInText(String toBeSearched, String text) {
        return Pattern.compile(Pattern.quote(toBeSearched), Pattern.CASE_INSENSITIVE).matcher(text).find();
    }

    public List<GymInfoBean> searchGym(SearchGymBean searchBean) throws DBUnrreachableException {
        List<Gym> gyms;
        try {
            gyms = gymDao.loadAllGyms();
        } catch (SQLException e) {
            throw new DBUnrreachableException();
        }
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