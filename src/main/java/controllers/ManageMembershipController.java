package controllers;

import beans.*;
import boundaries.PaymentSystemBoundary;
import database.dao.AthleteDAO;
import database.dao.GymDAO;
import database.dao.MembershipDAO;
import database.dao.TrainerDAO;
import engineering.LoggedAthleteSingleton;
import engineering.decorator.MembershipBuilder;
import engineering.decorator.MembershipInterface;
import exceptions.*;
import exceptions.dataexception.DataFieldException;
import exceptions.logger.CostumeLogger;
import model.*;
import model.cupons.Coupon;
import model.record.Card;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.time.Instant;
import java.time.YearMonth;
import java.util.*;
import java.util.regex.Pattern;
//UseCase Made by Alexandru Nazare
public class ManageMembershipController {
    private static Gym selectedGym;
    protected final GymDAO gymDao;
    private final boolean hasCard;
    LoggedAthleteSingleton singletonInstance;


    //Constructor checks if the user is logged and load his card if he has it

    public ManageMembershipController() throws NoLoggedUserException {
        singletonInstance =LoggedAthleteSingleton.getSingleton();
        if(singletonInstance==null)throw new NoLoggedUserException();
        hasCard=new AthleteDAO().loadCard(singletonInstance.getUser());
        gymDao = new GymDAO();
    }

    //returns a list of membership Beans by passing a GymInfoBean that has the field gymname set
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

    //returns a gym information in a bean by passing a GymInfoBean that has the field gymname set
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


    //it's used just to use Gym Model in a more smart way
    private static void setSelectedGym(Gym gym){
        selectedGym=gym;
    }

    //returns a list of coupons beans by passing a GymInfoBean that has the field gymname set

    public List<CouponsBean> getCouponsList(GymInfoBean onlyNameGym) throws NoUserFoundException, DBUnrreachableException {
        loadSelectedGym(onlyNameGym);
        List<Coupon> listOfCoupons=selectedGym.getCoupons();
        List <CouponsBean> listOfCouponsBeans=new ArrayList<>();
        for(Coupon coupon : listOfCoupons ){
            CouponsBean temp=new CouponsBean(
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

    //if its saved it returns a string with the first 4 digits of the credit card saved by the athlete
    public String fetchSavedCardStub() {
        if(!hasCard)return null;
        return  singletonInstance.getUser().getCard().cardNumber().replaceFirst("\\d\\d\\d\\d\\d\\d\\d\\d","*********");
    }

    // this function lets the Athlete pay with saved credit card if exists
    public PaymentConfirmationBean payNewMembership(GymInfoBean onlyNameGym,MembershipBean membershipBean,List<CouponsBean> cupons) throws NoCardFoundException, DBUnrreachableException, MembershipCouponNotFoundException, DecoratorNoBaseComponentException, FailedToSaveNewMembership, NoUserFoundException, MembershipOnlyForNewUserException, CouponNotCumulativeException, DataFieldException, PaymentFailedException {
        if(!hasCard) throw new NoCardFoundException();
        return makePaymentAndSave(onlyNameGym,membershipBean,cupons,singletonInstance.getUser().getCard());
    }
    //if the payment is successful the new membership(Wallet) is saved to persistence and set to the athlete, if the save is not successful the payment is reverted
    private PaymentConfirmationBean makePaymentAndSave(GymInfoBean onlyNameGym, MembershipBean membershipBean, List<CouponsBean> cupons, Card card) throws FailedToSaveNewMembership, MembershipCouponNotFoundException, DecoratorNoBaseComponentException, NoUserFoundException, CouponNotCumulativeException, MembershipOnlyForNewUserException, DBUnrreachableException, DataFieldException, PaymentFailedException {
        MembershipBean finalMembership=applyCouponsToMembership(onlyNameGym,membershipBean,cupons);
        if(Objects.equals(onlyNameGym.getName(), getActiveMembership().getGymName()) && finalMembership.isOnlyForNewUsers()) throw new MembershipOnlyForNewUserException();
        Wallet toSave=updateedUserWallet(finalMembership);
        PaymentBean paymentBean=getPaymentBean(finalMembership,card);
        PaymentSystemBoundary boundary=new PaymentSystemBoundary(paymentBean,new CardInfoBean(card.cardNumber(), String.valueOf(card.cardExpirationDate().getMonthValue()),String.valueOf(card.cardExpirationDate().getYear()), card.name(), card.surname()));
        PaymentConfirmationBean paymentConfirmationBean=boundary.pay();
        if(!paymentConfirmationBean.isSuccessOfTransation()) throw new PaymentFailedException();
        try{
            saveNewMemberShipToPersistence(toSave,LoggedAthleteSingleton.getSingleton().getUser());
        }catch (DBUnrreachableException e){
            boundary.refund(paymentConfirmationBean);
            throw new FailedToSaveNewMembership();
        }
        singletonInstance.getUser().setWallet(toSave);
        return paymentConfirmationBean;
    }
    //core function of the membership mechanism, uses the decorator pattern to generate a membership from a starting membership and a list of coupons
    public MembershipBean applyCouponsToMembership(GymInfoBean onlyName,MembershipBean selectedMembership,List<CouponsBean> selectedCoupons) throws MembershipCouponNotFoundException, DecoratorNoBaseComponentException, NoUserFoundException, CouponNotCumulativeException, DBUnrreachableException {
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
            Iterator<CouponsBean> couponBeanIterator= selectedCoupons.iterator();
            while(couponBeanIterator.hasNext()){
                Iterator<Coupon> couponIterator= cuponsList.iterator();
                CouponsBean couponBeanTemp=couponBeanIterator.next();
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
            MembershipInterface finalMembership = buildMembership(membership, couponsToApply);
            return new MembershipBean(onlyName.getName(),finalMembership.getBuildedName(),finalMembership.getPrice(),finalMembership.getDuration(), finalMembership.getPoints(), finalMembership.isForNewUsers());
    }
    //fetches the active memberships and returns a bean
    public static ActiveMembershipBean getActiveMembership(){
        Athlete me=LoggedAthleteSingleton.getSingleton().getUser();
        return new ActiveMembershipBean(me.gymName(), me.getPoints(), me.getExpirationOfmembership());
    }
    //This function checks if a wallet already exists for the gym that the user is subscribing to and updates it, if not we create a new wallet deleting the old one,
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
    @NotNull
    private PaymentBean getPaymentBean(MembershipBean membershipBean,Card card) throws DataFieldException {
        Athlete athlete=singletonInstance.getUser();
       return new PaymentBean(
                selectedGym.getIban(),
                selectedGym.getGymName(),
                membershipBean.getName(),
                membershipBean.getPrice(),
                new CardInfoBean(
                        card.cardNumber(),
                        String.valueOf(card.cardExpirationDate().getMonthValue()),
                        String.valueOf(card.cardExpirationDate().getYear()),
                        card.name(),
                        card.surname()),
                athlete.getEmail());
    }

    private void saveNewMemberShipToPersistence(Wallet membership,Athlete me) throws  DBUnrreachableException {
        new AthleteDAO().saveWallet(membership,me);
    }

    private  MembershipInterface buildMembership(Membership membership, List<Coupon> coupons) throws CouponNotCumulativeException, DecoratorNoBaseComponentException {
        return MembershipBuilder.buildMembership(membership,coupons);
       }
    // this function lets the Athlete pay with a card passed by bean

    public PaymentConfirmationBean payNewMebership(GymInfoBean onlyNameGym, MembershipBean membershipBean, List<CouponsBean> cupons, CardInfoBean cardInfoBean, boolean rememberCard) throws MembershipCouponNotFoundException, DecoratorNoBaseComponentException, FailedToSaveNewMembership, NoUserFoundException, MembershipOnlyForNewUserException, PaymentFailedException, CouponNotCumulativeException, DBUnrreachableException, DataFieldException {
        Card card=new Card(cardInfoBean.getCardNumber(),cardInfoBean.getNameOfOwner(),cardInfoBean.getSurnameOfOwenr(),YearMonth.of(Integer.parseInt(cardInfoBean.getYearOfExpiration()),Integer.parseInt(cardInfoBean.getMonthOfExpiration())));
        if(rememberCard)new AthleteDAO().saveCard(card);
        return makePaymentAndSave(onlyNameGym,membershipBean,cupons,card);
    }
    //returns a list of gym by filtering using the searchBean, if fullMatch the match happens only if all the not null fields of the searchBean match with the gym otherwise it just need one match from all the fields
    public List<GymInfoBean> searchGym(SearchGymBean searchBean,boolean fullMatch) throws DBUnrreachableException {
        List<Gym> gyms;
        try {
            gyms = gymDao.loadAllGyms();
        } catch (SQLException e) {
            CostumeLogger.getInstance().logError(e);
            throw new DBUnrreachableException();
        }
        List<GymInfoBean> beanList = new ArrayList<>();
        for (Gym gym : gyms) {
            boolean isNameValid = (searchBean.getName() == null) || stringExistsInText(searchBean.getName(), gym.getGymName());
            boolean isAddressValid = (searchBean.getAddress() == null) || stringExistsInText(searchBean.getAddress(), gym.getAddress());
            boolean isCityValid = (searchBean.getCity() == null) || stringExistsInText(searchBean.getCity(), gym.getCity());
            boolean isCountryValid = (searchBean.getCountry() == null) || stringExistsInText(searchBean.getCountry(), gym.getCountry());
            boolean atLeastOneMatch=stringExistsInText(searchBean.getName(), gym.getGymName()) || stringExistsInText(searchBean.getAddress(), gym.getAddress())||stringExistsInText(searchBean.getCity(), gym.getCity())||stringExistsInText(searchBean.getCountry(), gym.getCountry());
            if ((isNameValid && isAddressValid && isCityValid && isCountryValid)||(!fullMatch && atLeastOneMatch)) {
                beanList.add(new GymInfoBean(gym.getGymName(),
                        gym.getAddress(),
                        gym.getCity(),
                        gym.getIban(),
                        gym.getCountry()));
            }
        }
        return beanList;
    }
    //support for the searchGym function
    private boolean stringExistsInText(String toBeSearched, String text) {
        return Pattern.compile(Pattern.quote(toBeSearched), Pattern.CASE_INSENSITIVE).matcher(text).find();
    }
}