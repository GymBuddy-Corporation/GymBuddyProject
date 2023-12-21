package controllers;

import beans.GymBean;
import beans.GymInfoBean;
import beans.SearchGymBean;
import database.dao.GymDAO;
import model.Gym;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ManageMembershipController {

    protected GymDAO dao;
    public ManageMembershipController(){
        dao=new GymDAO();
    }


    private boolean searchString(String toBeSearched,String text){
        return  Pattern.compile(Pattern.quote(toBeSearched), Pattern.CASE_INSENSITIVE).matcher(text).find();

    }
    public List<GymInfoBean> searchGym(SearchGymBean searchBean){
        List<Gym> gyms=dao.loadAllGyms();
        List <GymInfoBean> beanList=new ArrayList<GymInfoBean>();
        for(Gym gym:gyms){
            if(searchBean.getName()!=null){
                if(!searchString(searchBean.getName(), gym.getGymName()))continue;
            }
            if(searchBean.getAddress()!=null){
                if (!searchString(searchBean.getAddress(), gym.getAddress())) continue;
            }
            if(searchBean.getCity()!=null){
                if(!searchString(searchBean.getCity(), gym.getCity()))continue;
            }
            if(searchBean.getCountry()!=null){
                if(!searchString(searchBean.getCountry(),gym.getCountry()))continue;
            }
            beanList.add(new GymInfoBean(gym.getGymName(),
                    gym.getAddress(),
                    gym.getCity(),
                    gym.getIban(),
                    gym.getCountry()));
        }
        return beanList;
    }

}
