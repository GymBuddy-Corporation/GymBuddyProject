package beans;


import exceptions.dataException.DataFieldException;
import exceptions.dataException.TyperEnumerations.FieldsEnum;
import exceptions.dataException.TyperEnumerations.ProblemEnum;

public class UserBean {
    private String username;
    private final CredentialsBean credentials;

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

    public CredentialsBean getCredentials() {
        return this.credentials;
    }

    public void setUsername(String username) throws DataFieldException {
        if(isValidLength(username)){
            this.username = username;
        }else {
           throw new DataFieldException(FieldsEnum.Username, ProblemEnum.NotValid);
        }
    }

    private boolean isValidLength(String str) {
        return str.length() <= 20;
    }

    public String getUsername() {
        return username;
    }
}
