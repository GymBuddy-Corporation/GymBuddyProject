package exceptions;

public class FailedToSaveNewMembership extends CostumException{
    public FailedToSaveNewMembership(){
        super("Failed to save Memebership");
    }
    public FailedToSaveNewMembership(String error){
        super(error);
    }
}
