package beans;

import java.util.Date;

public class ActiveMembershipBean {
    String gymName;

    public String getGymName() {
        return gymName;
    }

    public int getPoints() {
        return points;
    }

    public Date getEndOfMembership() {
        return endOfMembership;
    }

    public ActiveMembershipBean(String gymName, int points, Date endOfMembership) {
        this.gymName = gymName;
        this.points = points;
        this.endOfMembership = endOfMembership;
    }

    int points;
    Date endOfMembership;

}
