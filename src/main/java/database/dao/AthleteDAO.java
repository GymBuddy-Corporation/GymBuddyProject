package database.dao;


import database.SingletonConnection;
import database.query.Queries;
import model.Athlete;
import model.Gym;
import model.Trainer;

import model.record.Credentials;
import model.record.PersonalInfo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AthleteDAO {

    public Athlete loadAthlete(String email) {
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
                PersonalInfo personalInfoTrainer = new PersonalInfo(
                        rs.getString("athleteName"),
                        rs.getString("athleteSurname"),
                        rs.getDate("athleteDateofBirth").toLocalDate(),
                        rs.getString("trainerFC"),
                        rs.getString("trainerGender").charAt(0)
                );
                Credentials credentialsAthlete = new Credentials(
                        rs.getString("athleteEmail"),
                        rs.getString("athletePassword")
                );
                Credentials credentialsTrainer = new Credentials( //this is done for a security reason
                        "noEmail",
                        "noPassword"
                );
                Gym gym = new Gym(
                        "noUsername", //did for a security reason
                        rs.getString("gymIban"),
                        rs.getString("gymCity"),
                        rs.getString("gymAddress"),
                        rs.getString("nameGym"));
                Trainer trainer = new Trainer(
                        "noUsername",
                        personalInfoTrainer,
                        credentialsTrainer,
                        gym);
                return new Athlete(
                        rs.getString("athleteUsername"),
                        personalInfoAthlete,
                        credentialsAthlete,
                        gym,
                        trainer);
            } else {
                return null;
            }
        } catch (SQLException e) {
            SingletonConnection.closeConnection(SingletonConnection.getInstance().getConnection());
            System.out.println("Unreachable DB Exception.");
            return null;
            //todo handle exception
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


}
