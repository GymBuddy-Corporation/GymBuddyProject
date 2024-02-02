
import controllers.UserAccessController;
import exceptions.AlreadyLoggedUserException;
import exceptions.DBUnrreachableException;
import exceptions.NoUserFoundException;
import org.junit.jupiter.api.Test;
import beans.AthleteBean;
import beans.CredentialsBean;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class TestLogin {

    private static final String EMAIL = "lux@gmail.com";
    private static final String PASSWORD = "Password123@";

    /* Nel database è stato precedentemente registrato l'atleta
     *  con email 'lux@gmail.com' e password 'Password123@'.
     * Lo scopo del seguente test è di verificare se il login con tali credenziali
     *  vada effettivamente a buon fine e restituisca l'atleta corretto.*/

    @Test void testLogin() {
        int flag = 1;
        UserAccessController loginController = new UserAccessController();
        try {
            CredentialsBean credentialsBean = CredentialsBean.ctorWithoutSyntaxCheck(EMAIL, PASSWORD);
            AthleteBean returnedAthlete = (AthleteBean) loginController.login(credentialsBean, false);
            if(!Objects.equals(returnedAthlete.getCredentials().getEmail(), credentialsBean.getEmail())){
                flag = 0;
            }
        } catch (NoUserFoundException| AlreadyLoggedUserException | DBUnrreachableException e) {
            flag = -1;
            e.printStackTrace();
        }
        assertEquals(1, flag);
    }
}
