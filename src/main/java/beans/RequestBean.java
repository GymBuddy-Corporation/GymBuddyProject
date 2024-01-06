package beans;

import java.time.LocalDateTime;

public class RequestBean {
    private final LocalDateTime requestDate;
    private String info;
    private final AthleteBean athleteBean;
    private final String trainerFc;

    public RequestBean(String info, AthleteBean athleteBean, String trainerFc) {
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

    public void setInfo(String info) /*throws TextOutOfBoundException, EmptyFieldsException*/ {
        if(info.isEmpty()) {
            //throw new EmptyFieldsException();
            System.out.println("Campo vuoto\n\n");
        }
        if(info.length() > 450) {
            //throw new TextOutOfBoundException();
            System.out.println("Lunghezza eccessiva\n\n");
        }
        this.info = info;
    }

    public String getTrainerFc() {
        return trainerFc;
    }
}
