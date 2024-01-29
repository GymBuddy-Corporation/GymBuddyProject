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

    @Override
    public CredentialsBean getCredentials() {
        return credentials;
    }

    public GymInfoBean getGymInfo() {
        return gymInfo;
    }
}