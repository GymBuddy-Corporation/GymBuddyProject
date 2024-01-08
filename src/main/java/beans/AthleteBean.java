package beans;

import exceptions.dataException.DataFieldException;

public class AthleteBean extends PersonBean{
    private String trainerFC;
    public AthleteBean(String username, PersonalInfoBean personalInfo, CredentialsBean credentialsBean, String trainerFC) {
        super(username, personalInfo, credentialsBean);
        this.trainerFC = trainerFC;
    }

    public AthleteBean(String username, PersonalInfoBean personalInfo, CredentialsBean credentialsBean)  {
        super(username, personalInfo, credentialsBean);
    }
    public String getTrainerFC() {
        return trainerFC;
    }
    public void setTrainerFC(String trainerFC) {
        this.trainerFC = trainerFC;
    }
}
