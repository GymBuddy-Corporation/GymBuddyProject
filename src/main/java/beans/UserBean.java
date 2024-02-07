package beans;


import exceptions.dataexception.DataFieldException;
import exceptions.dataexception.typeenumerations.FieldsEnum;
import exceptions.dataexception.typeenumerations.ProblemEnum;

public class UserBean {
    private final CredentialsBean credentials;
    private String username;

    public UserBean( CredentialsBean credentials, String username) {
        //This is a constructor without syntax check and is used by controller
        this.username = username;
        this.credentials = CredentialsBean.ctorWithoutSyntaxCheck(credentials.getEmail(), credentials.getPassword());
    }

    public UserBean(String username, CredentialsBean credentials) throws DataFieldException {
        /*This is a constructor with syntax check and is used by view*/
        setUsername(username);
        this.credentials = CredentialsBean.ctorWithSyntaxCheck(credentials.getEmail(), credentials.getPassword());
    }

    private boolean isValidLength(String str) {
        return str.length() <= 20;
    }

    public CredentialsBean getCredentials() {
        return this.credentials;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws DataFieldException {
        if(isValidLength(username)){
            this.username = username;
        }else {
           throw new DataFieldException(FieldsEnum.USERNAME, ProblemEnum.NOT_VALID);
        }
    }
}
