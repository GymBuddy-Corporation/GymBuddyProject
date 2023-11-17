package beans;

import java.time.YearMonth;

public class AthleteBean extends UserBean{


    public AthleteBean(String username/*, PersonalInfoBean personalInfo, CredentialsBean credentialsBean*//*, CardInfoBean cardInfoBean*/) {
        super(username/*, personalInfo,"Athlete",  credentialsBean*/);
        /*this.cardInfoBean = cardInfoBean;*/
    }

    public String getUsername(){
        return getUsernameBean();
    }
}
