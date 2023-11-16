package beans;

public class ExerciseForWorkoutRoutineBean extends ExerciseBean{
    private final int day;
    private final String name;
    private final int repetitions;
    private final int sets;
    private final String rest;

    public ExerciseForWorkoutRoutineBean(int day, String name, int repetitions, int sets, String rest) {
        super(name);
        this.day = day;
        this.name = name;
        this.rest = rest;
        this.sets = sets;
        this.repetitions = repetitions;
    }

    public int getDay() {
        return day;
    }

    public String getName() {
        return name;
    }

    public int getRepetitions() {return repetitions;}

    public int getSets() {return sets;}

    public String getRest() {return rest;}
}
