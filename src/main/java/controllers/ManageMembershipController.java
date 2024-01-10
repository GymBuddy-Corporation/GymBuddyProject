package controllers;

import beans.CuponsBean;
import beans.GymInfoBean;
import beans.MembershipBean;
import beans.SearchGymBean;
import database.dao.CouponsDAO;
import database.dao.GymDAO;
import database.dao.MembershipDAO;
import engineering.decorator.MembershipInterface;
import exceptions.CouponNotCumulativeException;
import exceptions.MembershipCouponNotFoundException;
import model.Gym;
import model.Membership;
import model.cupons.Cupon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class ManageMembershipController {
    protected GymDAO gymDao;

    public ManageMembershipController() {
        gymDao = new GymDAO();
    }

    public List<MembershipBean> getMembershipList(GymInfoBean onlyGymNameBean) {
        List<Membership> listOfMemberships = loadMemberships(onlyGymNameBean);
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
    private List<Membership> loadMemberships(GymInfoBean onlyGymNameBean){
        MembershipDAO membershipDAO=new MembershipDAO();
        return membershipDAO.getMembership(onlyGymNameBean.getName());
    }
    private List<Cupon> loadCoupons(GymInfoBean onlyGymNameBean){
        CouponsDAO couponsDAO=new CouponsDAO();
        return couponsDAO.getCoupons(onlyGymNameBean.getName());
    }

    private void applyCouponsToMembership(GymInfoBean onlyName,MembershipBean selectedMembership,List<CuponsBean> selectedCoupons) throws MembershipCouponNotFoundException {
            List<Membership> membershipsList=loadMemberships(onlyName);
            List<Cupon> cuponsList=loadCoupons(onlyName);
            Membership membership=null;
            for(Membership temp:membershipsList){
                if(selectedMembership.getCode()==temp.getCode()){
                    membership=temp;
                    break;
                }
            }
            if(membership==null)throw new MembershipCouponNotFoundException();
        List<Cupon> couponsToApply=loadCoupons(onlyName);
        for(Cupon temp:cuponsList){
                for (CuponsBean temp2:selectedCoupons){
                    if(temp2.getCode()==temp.getCode()){
                        couponsToApply.add(temp);
                        cuponsList.remove(temp);
                        selectedCoupons.remove(temp2);
                        break;
                    }
                }
            }
        if(!cuponsList.isEmpty())throw new MembershipCouponNotFoundException();
        MembershipInterface finalMembership=membership;
        for(Cupon temp:couponsToApply){
            try {
                finalMembership=temp.setComponent(finalMembership);
            } catch (CouponNotCumulativeException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public List<CuponsBean> getCouponsList(GymInfoBean onlyNameGym){
        List<Cupon> listOfCoupons=loadCoupons(onlyNameGym);
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