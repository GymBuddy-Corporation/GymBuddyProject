import beans.CredentialsBean;
import beans.GymInfoBean;
import beans.SearchGymBean;
import controllers.ManageMembershipController;
import controllers.UserAccessController;
import exceptions.AlreadyLoggedUserException;
import exceptions.DBUnrreachableException;
import exceptions.NoLoggedUserException;
import exceptions.NoUserFoundException;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


class TestSearchGym {



    @Test
    public void testSearchAllGyms() throws NoUserFoundException, AlreadyLoggedUserException, NoLoggedUserException, DBUnrreachableException {
        UserAccessController accessController = new UserAccessController();
        accessController.login(CredentialsBean.ctorWithoutSyntaxCheck("lux@gmail.com", "Password123@"),false);
        ManageMembershipController controller = new ManageMembershipController();
        List<GymInfoBean> lista = controller.searchGym(new SearchGymBean("gym", "", "", ""),true);
        Iterator it = lista.iterator();
        while (it.hasNext()) {
            GymInfoBean gym = (GymInfoBean) it.next();
            System.out.println(gym.getName());
            assertTrue(gym.getName().toLowerCase().contains("gym"));
        }
    }
}
