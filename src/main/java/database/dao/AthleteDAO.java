package database.dao;


import database.SingletonConnection;
import database.query.Queries;
import exceptions.CostumException;
import exceptions.DBUnrreachableException;
import exceptions.NoUserFoundException;
import exceptions.logger.CostumeLogger;
import model.Athlete;
import model.Gym;
import model.Trainer;
import model.Wallet;
import model.record.Card;
import model.record.Credentials;
import model.record.PersonalInfo;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class AthleteDAO {

    private static final String FILE_FOR_CARD="CAR.ser";

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
            CostumeLogger.getInstance().logError(e);
            throw new DBUnrreachableException();
        }
    }

    public void removeWorkoutPlan(String athleteFC) throws DBUnrreachableException {
        try(PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.REMOVE_WORKOUT_ROUTINE_QUERY)) {
            Queries.removeWorkoutRoutine(preparedStatement, athleteFC);
        } catch (SQLException e) {
            SingletonConnection.closeConnection(SingletonConnection.getInstance().getConnection());
            CostumeLogger.getInstance().logError(e);

            throw new DBUnrreachableException();
        }
    }

    public boolean loadCard(Athlete athlete) {
        Card card;
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        try {
            fileIn = new FileInputStream(FILE_FOR_CARD);
            in = new ObjectInputStream(fileIn);
            card = (Card) in.readObject();
            athlete.setCard(card);
        } catch (IOException | ClassNotFoundException i) {
            return false;
        }finally {
            SingletonConnection.closeAll(fileIn,in);
        }

        return true;
    }

    public void saveCard(Card card){
        ObjectOutputStream out = null;
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(FILE_FOR_CARD);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(card);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            CostumeLogger.getInstance().logError(e);
        }finally {
            SingletonConnection.closeAll(out,fileOut);
        }
    }


    public void loadAthleteWallet(Athlete athlete) throws CostumException {
        try(
                PreparedStatement preparedStatement = SingletonConnection.getInstance().getConnection().prepareStatement(Queries.LOAD_USER_WALLET);
                ResultSet result=Queries.loadAndExecuteOneString(athlete.getFC(), preparedStatement)
        ) {
            Wallet wallet = null;
            if (result.next()) {
                Date dataInizio = result.getDate("starDatetMembership");
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
            CostumeLogger.getInstance().logError(e);
            throw new DBUnrreachableException();

        } catch (NoUserFoundException e) {
            throw new CostumException("Gym doesnt exits anymore");
        }
    }

    public void saveWallet(Wallet wallet, Athlete athlete) throws DBUnrreachableException {
        try(PreparedStatement statementEliminazione = SingletonConnection.getInstance().getConnection().prepareStatement(Queries.DELETE_WALLET);) {
            statementEliminazione.setString(1,athlete.getFC());
            statementEliminazione.executeUpdate();
            PreparedStatement statementInserimento = SingletonConnection.getInstance().getConnection().prepareStatement(Queries.INSERT_WALLET);
            Queries.loadAndExecuteWalletInsertion(statementInserimento, athlete.getFC(), wallet.getStartOfMembership(), wallet.getCurrentGym().getGymName(), wallet.getEndOfMembership(), wallet.getPoints(), wallet.getMembershipName(), wallet.getMembershipPrice(), wallet.getTrainer().getFC());
        }catch (SQLException e ){
                CostumeLogger.getInstance().logError(e);
                throw new DBUnrreachableException();
        }

    }




}
