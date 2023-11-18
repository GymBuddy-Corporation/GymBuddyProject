package beans;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Occhio a come si fa la seconda interfaccia, perchè se si usa un altro metodo di
//input per i dati (non più bottoni semplici), dovrà essere trasformata in un'interfaccia che poi successivamente,
//a seconda dei casi, verrà implementata dai diversi Bean
public class ExerciseForWorkoutRoutineBean extends ExerciseBean{
    private final String day;
    private final String name;
    private int repetitions;
    private int sets;
    private String rest;

    public ExerciseForWorkoutRoutineBean(String day, String name, int repetitions, int sets, String rest) {
        super(name);
        this.day = day;
        this.name = name;
        this.rest = rest;
        this.sets = sets;
        this.repetitions = repetitions;
    }

    public ExerciseForWorkoutRoutineBean(String day, String name) {
        super(name);
        this.day = day;
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    @Override public String getName() {
        return name;
    }

    public int getRepetitions() {return repetitions;}

    public int getSets() {return sets;}

    public String getRest() {return rest;}

    public boolean setRest(String timeString){
        if(isValidTime(timeString)) {
            this.rest = timeString;
            return true;
            //poi gestisci le eccezioni
        } else {
            System.out.println("Recupero inappropriato: " + timeString + " scegli tra le opzioni proposte.\n");
            return false;
        }
    }

    public void setRepetitions(int reps) {this.repetitions = reps;}
    public void setSets(int sets) {this.sets = sets;}

    public static boolean isValidTime(String timeString) {
        if (timeString == null) {
            return false;  // or handle the null case differently
        }

        String timeRegex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern pattern = Pattern.compile(timeRegex);
        Matcher matcher = pattern.matcher(timeString);
        return matcher.matches();
    }
}