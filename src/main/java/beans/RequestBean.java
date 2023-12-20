package beans;

import java.time.LocalDateTime;

public class RequestBean {
    private int id;  /*TODO Da chiarire a cosa mi può servire l'id?*/
    private final LocalDateTime requestDate;
    private String info;
    private final AthleteBean athleteBean;
    private final String trainerFc;

    public RequestBean(int id, LocalDateTime requestDate, String info, AthleteBean athleteBean, String trainer) {
        this.id = id;
        this.requestDate = requestDate;
        this.info = info;
        this.athleteBean = athleteBean;
        this.trainerFc = trainer;
    }

    public RequestBean(String info, AthleteBean athleteBean, String trainerFc) {
        this.requestDate = LocalDateTime.now();
        setInfo(info);
        this.athleteBean = athleteBean;
        this.trainerFc = trainerFc;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public AthleteBean getAthleteBean() {
        return athleteBean;
    }

    public String getTrainerFc() {
        return trainerFc;
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

}
