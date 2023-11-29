package beans;

/*import java.time.LocalDate;*/

public class PersonBean extends UserBean{

    private final PersonalInfoBean personalInfo;

    public PersonBean(String username, PersonalInfoBean personalInfo, CredentialsBean credentials) {
        super(username, credentials);
        //This is a constructor without syntax check and is used by controller
        this.personalInfo = personalInfo;
    }

    public String getUsernameBean() {
        return this.username;
    }

    public CredentialsBean getCredentials() {
        return this.credentials;
    }

    public PersonalInfoBean getPersonalInfo(){
        return this.personalInfo;
    }

    public void setUsername(String username) {
        if(isValidLength(username)){
            this.username = username;
        }else {
            System.out.println("ERRORE CAMPO USERNAME");
        }
    }

    private boolean isValidLength(String str) {
        return str.length() <= 20;
    }

    public String getName() {
        return this.getPersonalInfo().getName();
    }
}
