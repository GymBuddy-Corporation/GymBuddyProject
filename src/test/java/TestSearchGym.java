import beans.CredentialsBean;
import beans.GymInfoBean;
import beans.SearchGymBean;
import controllers.UserAccessController;
import engineering.LoggedAthleteSingleton;
import exceptions.AlreadyLoggedUserException;
import exceptions.DBUnrreachableException;
import exceptions.NoLoggedUserException;
import exceptions.NoUserFoundException;
import exceptions.dataexception.DataFieldException;
import org.junit.jupiter.api.Test;
import controllers.ManageMembershipController;

import java.util.Iterator;
import java.util.List;


class TestSearchGym {


    //    @Test
//    void testSearchGym_nameSearch() {
//        this.dao = mock(GymDAO.class);
//        ManageMembershipController controller = new ManageMembershipController();
//        List<Gym> mockedGyms = new ArrayList<>();
//        mockedGyms.add(new Gym("test1", new Credentials("alex","aaa"),"ffff","Roma", "via mario 12", "italia", "pal1"));
//        mockedGyms.add(new Gym("test2", new Credentials("bob","bbb"),"ffff","Rome", "via mario 13", "romania", "pal2"));
//        mockedGyms.add(new Gym("test3", new Credentials("charlie","ccc"),"ffff","Roma", "via marto 14", "italio", "pal3"));
//
//        when(dao.loadAllGyms()).thenReturn(mockedGyms);
//
//        SearchGymBean searchBean1 = new SearchGymBean(null, "Rom", null, null);
//        SearchGymBean searchBean2 = new SearchGymBean(null, null, "via mario", null);
//        SearchGymBean searchBean3 = new SearchGymBean(null, "Roma", "via mario", null);
//        List<GymInfoBean> result = this.searchGym(searchBean1);
//
//        assertEquals(3, result.size(), "Expected one gym to be found");
//        result = this.searchGym(searchBean2);
//        assertEquals(2, result.size(), "Expecting Test Gym to be found");
//        result = this.searchGym(searchBean3);
//        assertEquals(1, result.size(), "Expecting Test Gym to be found");
//
//    }
    @Test
    public void testSearchAllGyms() throws DataFieldException, NoUserFoundException, AlreadyLoggedUserException, NoLoggedUserException, DBUnrreachableException {
        UserAccessController accessController = new UserAccessController();
        accessController.login(CredentialsBean.ctorWithoutSyntaxCheck("lux@gmail.com", "lux"),false);
        System.out.println(LoggedAthleteSingleton.getSingleton().getUser().getName());
        ManageMembershipController controller = new ManageMembershipController();
        List<GymInfoBean> lista = controller.searchGym(new SearchGymBean("", "", "", ""));
        Iterator it = lista.iterator();
        while (it.hasNext()) {
            GymInfoBean gym = (GymInfoBean) it.next();
            System.out.println(gym.getName());
        }
    }
}
