package beans;

public class GymBean {
    private String username;
    private final GymInfoBean gymInfo;
    private final CredentialsBean credentials;

    /*public GymBean(String username, GymInfoBean gymInfo, CredentialsBean credentials) {
        //This is a constructor without syntax check and is used by controller
        this.username = username;
        this.gymInfo = gymInfo;
        this.credentials = CredentialsBean.ctorWithoutSyntaxCheck(credentials.getEmail(), credentials.getPassword());
    }*/

    public GymBean(String username, GymInfoBean gymInfo, CredentialsBean credentials) /*throws InvalidUserInfoException, InvalidFiscalCodeException, InvalidCredentialsException, InvalidBirthException, EmptyFieldsException*/ {
        /*This is a constructor with syntax check and is used by view*/
        setUsername(username);
        this.gymInfo = gymInfo;
        //TODO capire come usare questo costruttore (è da qui che nascono i nullpointer)
        this.credentials = CredentialsBean.ctorWithSyntaxCheck(credentials.getEmail(), credentials.getPassword());
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