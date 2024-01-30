package database.dao;


import database.SingletonConnection;
import database.query.Queries;
import exceptions.DBUnrreachableException;
import exceptions.NoUserFoundException;
import model.Athlete;
import model.Gym;
import model.Trainer;

import model.Wallet;
import model.record.Credentials;
import model.record.PersonalInfo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class AthleteDAO {

    public Athlete loadAthlete(String email) throws DBUnrreachableException {
        try(
                PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().
                        prepareStatement(Queries.LOAD_USER_1_QUERY);
                ResultSet rs = Queries.loadUser(email, preparedStatement)) {
            if (rs.next()) {
                PersonalInfo personalInfoAthlete = new PersonalInfo(
                        rs.getString("trainerName"),
                        rs.getString("trainerSurname"),
                        rs.getDate("traineDateOfBirth").toLocalDate(),
                        rs.getString("athleteFC"),
                        rs.getString("athelteGender").charAt(0)
                );
                Credentials credentialsAthlete = new Credentials(
                        rs.getString("athleteEmail"),
                        ""
                );
                return new Athlete(
                        rs.getString("athleteUsername"),
                        personalInfoAthlete,
                        credentialsAthlete);
            } else {
                return null;
            }
        } catch (SQLException e) {
            SingletonConnection.closeConnection(SingletonConnection.getInstance().getConnection());
            throw new DBUnrreachableException();
        }
    }

    public void removeWorkoutPlan(String athleteFC) {
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.REMOVE_WORKOUT_ROUTINE_QUERY)) {
            Queries.removeWorkoutRoutine(preparedStatement, athleteFC);
        } catch (SQLException e) {
            SingletonConnection.closeConnection(SingletonConnection.getInstance().getConnection());
            System.out.println("Unreachable DB Exception.");
            //todo handle exception
        }
    }

    public void loadAthleteWallet(Athlete athlete){
        try(
                PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(Queries.LOAD_USER_WALLET);
                ResultSet result=Queries.loadAndExecuteOneString(athlete.getFC(), preparedStatement);
            ) {
            Wallet wallet = null;
            if (result.next()) {
                // DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date dataInizio = result.getDate("starDateMembership");
                Date dataFine = result.getDate("endDateMembership");
                GymDAO dao = new GymDAO();
                Gym gym = dao.getGymByName(result.getString("nameGym"));
                int point = result.getInt("points");
                String membershipName = result.getString("membershipName");
                float membershipPrice = result.getFloat("membershipPrice");
                TrainerDAO daoT = new TrainerDAO();
                Trainer trainer = daoT.loadTrainer(result.getString("trainers_fc"), "fc");
                wallet = new Wallet(dataInizio, dataFine, gym, point, membershipName, membershipPrice, trainer);
            }
            athlete.setWallet(wallet);
        } catch (SQLException e) {
            SingletonConnection.closeConnection(SingletonConnection.getInstance().getConnection());
            //throw e;
            //return null;
        } catch (NoUserFoundException e) {
            //return  null;
        }
    }




}
