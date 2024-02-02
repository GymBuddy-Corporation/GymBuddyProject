package beans;

import exceptions.dataException.DataFieldException;
import exceptions.dataException.TyperEnumerations.FieldsEnum;
import exceptions.dataException.TyperEnumerations.ProblemEnum;

import java.time.LocalDateTime;

public class RequestBean {
    private final LocalDateTime requestDate;
    private String info;
    private final AthleteBean athleteBean;
    private final String trainerFc;

    public RequestBean(String info, AthleteBean athleteBean, String trainerFc) throws DataFieldException {
        this.requestDate = LocalDateTime.now();
        setInfo(info);
        this.athleteBean = athleteBean;
        this.trainerFc = trainerFc;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public AthleteBean getAthleteBean() {
        return athleteBean;
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) throws DataFieldException {
        if(info.isEmpty()) {
            throw new DataFieldException(FieldsEnum.INFO, ProblemEnum.EMPTY);
        }
        if(info.length() > 450) {
            throw new DataFieldException(FieldsEnum.INFO, ProblemEnum.NOT_VALID);

        }
        this.info = info;
    }

    public String getTrainerFc() {
        return trainerFc;
    }
}
