/*

import org.junit.jupiter.api.Test;
import beans.AthleteBean;
import beans.CredentialsBean;

import java.sql.SQLException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class TestLogin {

    private static final String EMAIL = "lux@gmail.com";
    private static final String PASSWORD = "lux";

    */
/**todo
     * Nel database è stato precedentemente registrato l'atleta
     *  con email 'lux@gmail.com' e password 'Ciaociao00!'.
     * Lo scopo del seguente test è di verificare se il login con tali credenziali
     *  vada effettivamente a buon fine e restituisca l'atleta corretto.
     *//*

    @Test void testLogin() {
        int flag = 1;
        LoginController loginController = new LoginController();
        try {
            CredentialsBean credentialsBean = CredentialsBean.ctorWithoutSyntaxCheck(EMAIL, PASSWORD);
            AthleteBean returnedAthlete = (AthleteBean) loginController.login(credentialsBean);
            if(!Objects.equals(returnedAthlete.getEmail(), credentialsBean.getEmail()) || !Objects.equals(returnedAthlete.getPassword(), credentialsBean.getPassword())){
                flag = 0;
            }
        } catch (SQLException | DBUnreachableException | UserNotFoundException e) {
            */
/*
             * NB: DBUnreachableException si verifica in assenza di connessione internet.
             * Quindi in caso di errore (valore di flag = -1) verificare
             * di essere connessi a internet prima di rieseguire il test.
             *//*

            flag = -1;
            e.printStackTrace();
        }
        assertEquals(1, flag);
    }

}*/
