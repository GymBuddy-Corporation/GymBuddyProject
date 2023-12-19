package beans;

import java.time.LocalDateTime;
import java.util.List;

public interface RequestBean {
    LocalDateTime getRequestDate();

    AthleteBean getAthleteBean();
}
