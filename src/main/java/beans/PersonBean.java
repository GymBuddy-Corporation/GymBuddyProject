package beans;


import exceptions.dataexception.DataFieldException;

public class PersonBean extends UserBean{

    private final PersonalInfoBean personalInfo;

    public PersonBean(String username, PersonalInfoBean personalInfo, CredentialsBean credentials) {
        super(credentials, username);
        //This is a constructor without syntax check and is used by controller
        this.personalInfo = personalInfo;
    }
    public PersonBean( PersonalInfoBean personalInfo,String username, CredentialsBean credentials) throws DataFieldException {
        super(username, credentials);
        //This is a constructor without syntax check and is used by controller
        this.personalInfo = personalInfo;
    }

    public PersonalInfoBean getPersonalInfo(){
        return this.personalInfo;
    }
}
