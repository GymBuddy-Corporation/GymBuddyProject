package beans;

public class TrainerBean extends UserBean{

    public TrainerBean(String username, PersonalInfoBean personalInfo, CredentialsBean credentialsBean) {
        super(username, "Trainer", personalInfo, credentialsBean);
    }
}