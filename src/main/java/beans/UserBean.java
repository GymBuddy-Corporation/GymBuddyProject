package beans;

/*import java.time.LocalDate;*/

public class UserBean {

    //Ho tolto il campo 'tipo' perchè poi gestiremo questa cosa direttmente con istance of
    protected String username;
    protected final CredentialsBean credentials;

    public UserBean( CredentialsBean credentials, String username) {
        //This is a constructor without syntax check and is used by controller
        this.username = username;
        this.credentials = CredentialsBean.ctorWithoutSyntaxCheck(credentials.getEmail(), credentials.getPassword());
    }

    public UserBean(String username, CredentialsBean credentials) /*throws InvalidUserInfoException, InvalidFiscalCodeException, InvalidCredentialsException, InvalidBirthException, EmptyFieldsException*/ {
        /*This is a constructor with syntax check and is used by view*/
        setUsername(username);
        //TODO capire come usare questo costruttore (è da qui che nascono i nullpointer)
        this.credentials = CredentialsBean.ctorWithSyntaxCheck(credentials.getEmail(), credentials.getPassword());
    }

    public String getUsernameBean() {
        return this.username;
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
}
