package beans;

/*import java.time.LocalDate;*/

public class PersonBean extends UserBean{

    private final PersonalInfoBean personalInfo;

    public PersonBean(String username, PersonalInfoBean personalInfo, CredentialsBean credentials) {
        super(username, credentials);
        //This is a constructor without syntax check and is used by controller
        this.personalInfo = personalInfo;
    }

    public PersonalInfoBean getPersonalInfo(){
        return this.personalInfo;
    }
}
