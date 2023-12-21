import beans.GymInfoBean;
import beans.SearchGymBean;
import database.dao.GymDAO;
import model.Gym;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ManageMembershipControllerTest {

    @Test
    void testSearchGym_nameSearch() {
        GymDAO mockedDao = mock(GymDAO.class);
        ManageMembershipController controller = new ManageMembershipController(mockedDao);
        List<Gym> mockedGyms = new ArrayList<>();
        mockedGyms.add(new Gym("Test Gym", "Some Address", "Some City", "IBAN", "Country"));
        when(mockedDao.loadAllGyms()).thenReturn(mockedGyms);

        SearchGymBean searchBean = new SearchGymBean("Test", null, null, null);
        List<GymInfoBean> result = controller.searchGym(searchBean);

        assertEquals(1, result.size(), "Expected one gym to be found");
        assertEquals("Test Gym", result.get(0).getName(), "Expecting Test Gym to be found");
    }

    // similarly we can write tests for citySearch, addressSearch and countrySearch

    // test when no search criteria match
    @Test
    void testSearchGym_noMatch() {
        GymDAO mockedDao = mock(GymDAO.class);
        ManageMembershipController controller = new ManageMembershipController(mockedDao);
        when(mockedDao.loadAllGyms()).thenReturn(Collections.emptyList());

        SearchGymBean searchBean = new SearchGymBean("Test", null, null, null);
        List<GymInfoBean> result = controller.searchGym(searchBean);

        assertEquals(0, result.size(), "Expected no gyms to be found");
    }
}
