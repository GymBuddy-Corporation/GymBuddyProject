package beans;

public class GymBean extends UserBean {
    private final GymInfoBean gymInfo;
    private final CredentialsBean credentials;

    public GymBean(String username, CredentialsBean credentials, GymInfoBean gymInfo) {
        //This is a constructor without syntax check and is used by controller
        super(credentials, username);
        this.gymInfo = gymInfo;
        this.credentials = credentials;
    }

    public GymBean(String username, GymInfoBean gymInfo, CredentialsBean credentials) /*throws InvalidUserInfoException, InvalidFiscalCodeException, InvalidCredentialsException, InvalidBirthException, EmptyFieldsException*/ {
        /*This is a constructor with syntax check and is used by view*/
        super(username, credentials);
        setUsername(username);
        this.gymInfo = gymInfo;
        //TODO capire come usare questo costruttore (è da qui che nascono i nullpointer)
        this.credentials = CredentialsBean.ctorWithSyntaxCheck(credentials.getEmail(), credentials.getPassword());
    }

    private boolean isValidLength(String str) {
        return str.length() <= 20;
    }

    @Override
    public CredentialsBean getCredentials() {
        return credentials;
    }

    public GymInfoBean getGymInfo() {
        return gymInfo;
    }
}