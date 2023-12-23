package database.dao;

import model.Membership;

import java.util.ArrayList;
import java.util.List;

public class MembershipDAO {
    public List<Membership> getMembership(String gymName){
        List<Membership> membershipList=new ArrayList<Membership>();
        membershipList.add(new Membership("1 Month packet","A good membership for starting workout",45.0f,30,10));
        membershipList.add(new Membership("3 Months packet","Good for those committed to regular workout",120.0f,90,20));
        membershipList.add(new Membership("6 Months packet","Motivate yourself to keep up for half a year",220.0f,180,30));
        membershipList.add(new Membership("12 Months packet","Perfect deal for workout enthusiasts",400.0f,360,50));

        return membershipList;
    }
}