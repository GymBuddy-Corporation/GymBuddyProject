package exceptions;

public class DecoratorNoBaseComponentException extends CostumException{
   public  DecoratorNoBaseComponentException(){
        super("Decorator cant have an empty base");
    }
}
