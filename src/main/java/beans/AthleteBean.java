package beans;

import exceptions.dataException.DataFieldException;

public class AthleteBean extends PersonBean{

    public AthleteBean(String username, PersonalInfoBean personalInfo, CredentialsBean credentialsBean/*, CardInfoBean cardInfoBean*/) throws DataFieldException {
        super(username, personalInfo, credentialsBean);
    }
}
