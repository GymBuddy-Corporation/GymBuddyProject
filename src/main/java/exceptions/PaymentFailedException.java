package exceptions;

public class PaymentFailedException extends CostumException{
    public PaymentFailedException(String message){
        super(message);
    }

    public PaymentFailedException(){
        super(
                "Paymnet failed"
        );
    }
}
