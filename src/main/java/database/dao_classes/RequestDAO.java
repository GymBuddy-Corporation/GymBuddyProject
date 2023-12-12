package database.dao_classes;

/*import database.DatabaseConnectionSingleton;
import database.queries.RequestQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;*/
import beans.AthleteBean;
import beans.CredentialsBean;
import beans.PersonalInfoBean;
import beans.RequestBean;
import model.Athlete;
import model.Gym;
import model.Request;
import model.Trainer;
import model.record.Credentials;
import model.record.PersonalInfo;

/*import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;*/
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {
    public static final String ID_REQUEST = "idRequest";
    public static final String REQUEST_DATE = "RequestDate";
    public static final String INFO = "Info";
    public static final String ATHLETE = "Athlete";

    public void deleteRequest(LocalDateTime requestDate, String athleteEmail) /*throws SQLException, DBUnreachableException*/ {

        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                RequestQueries.DELETE_REQUEST_QUERY)){
            RequestQueries.deleteRequest(preparedStatement, idRequest);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/
    }

    public List<Request> loadTrainerRequests(Trainer trainer) /*throws SQLException, DBUnreachableException */{
        List <Request> requestList = new ArrayList<>();
        PersonalInfo pi1 = new PersonalInfo("Luca", "Martorelli", LocalDate.of(2000, 9, 1), "MRTLCU00P01D612J", 'm');
        PersonalInfo pi2 = new PersonalInfo("Chiara", "Iurato", LocalDate.of(2000, 4, 15), "RTICHRU00P01D612J", 'f');
        PersonalInfo pi3 = new PersonalInfo("Marco", "Martorell", LocalDate.of(2007, 9, 6), "MRTMRC00P01D612J", 'm');
        PersonalInfo pi4 = new PersonalInfo("Alexandru", "Nazare", LocalDate.of(2002, 5, 15), "NZRLXN00P01D612J", 'm');
        PersonalInfo pi5 = new PersonalInfo("Alessandro", "Cortese", LocalDate.of(1999, 6, 1), "CRTLSS00P01D612J", 'm');



        Gym palestra1 = new Gym("palestra1", new Credentials("alecortix@gmail.com", "forzanapule1926"),
                "BBBBBBBBBBBBBBBBBBBBBB", "roma", "Piazza dei Consoli, 11");

        Credentials credentials1 = new Credentials("lucam0109@gmail.com", "LAZINESS1900");
        Credentials credentials2 = new Credentials("accroccoman@gmail.com", "megliololiodiItri");
        Credentials credentials3 = new Credentials("edoman00@gmail.com", "cyber");
        /**/
        Athlete athlete1 = new Athlete("LuX71", pi1,
                credentials1, palestra1, trainer);
        Request r1 = new Request(1, LocalDateTime.of(2023, 11, 20,
                15, 30, 0), "Ciao, vorrei una nuova scheda che" +
                " aiutasse a crescere principalmente di spalle", athlete1, trainer);
        Athlete athlete2 = new Athlete( "accroccoman", pi2,
                credentials2, palestra1, trainer);
        Request r2 = new Request(2, LocalDateTime.of(2023, 11, 20,
                15, 30, 0),"Ei vorrei un piano che mi" +
                " aiutasse a sviluppare il petto", athlete2, trainer);
        Athlete athlete3 = new Athlete("EdoMan00", pi3,
                credentials3, palestra1, trainer);
        Request r3 = new Request(3, LocalDateTime.of(2023, 11, 20,
                15, 30, 0),"Ciao, potresti organizzarmi una scheda" +
                " pesantissima che mi hanno appena lasciato? Sono disposto a venire in palestra 7/7", athlete3, trainer);
         requestList.addAll(List.of(r1, r2, r3));
        return requestList;
    }

    public void saveRequest(LocalDateTime requestDate, String info, String athleteFc, String trainer) /*throws SQLException, DBUnreachableException*/ {
        /*try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                RequestQueries.INSERT_REQUEST_QUERY)){
            RequestQueries.insertRequest(preparedStatement, requestDate, info, athleteFc, trainer);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }*/
    }
}
