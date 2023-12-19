package beans;

/*import java.time.LocalDate;*/

import exceptions.dataException.DataFieldException;

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
        //TODO capire come usare questo costruttore (è da qui che nascono i nullpointer)
        this.credentials = CredentialsBean.ctorWithSyntaxCheck(credentials.getEmail(), credentials.getPassword());
    }

    public CredentialsBean getCredentials() {
        return this.credentials;
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

    public String getUsername() {
        return username;
    }
}
