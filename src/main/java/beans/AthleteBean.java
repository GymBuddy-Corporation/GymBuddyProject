package beans;

import exceptions.dataException.DataFieldException;

public class AthleteBean extends PersonBean{
    private String trainerFC;
    public AthleteBean(String username, PersonalInfoBean personalInfo, CredentialsBean credentialsBean, String trainerFC) throws DataFieldException {
        super(username, personalInfo, credentialsBean);
        this.trainerFC = trainerFC;
    }
    public String getTrainerFC() {
        return trainerFC;
    }
    public void setTrainerFC(String trainerFC) {
        this.trainerFC = trainerFC;
    }
}
