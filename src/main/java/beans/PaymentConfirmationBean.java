package beans;

public class PaymentConfirmationBean {
    public void setValueTransfered(float valueTransfered) {
        this.valueTransfered = valueTransfered;
    }

    private final String paymentId;

    public void setSuccessOfTransation(boolean successOfTransation) {
        this.successOfTransation = successOfTransation;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private float valueTransfered;
    private final String ibanOfBenefeicary;
    private boolean successOfTransation;
    private String errorMessage;

    public PaymentConfirmationBean(String paymentId, float valueTransfered ,String ibanOfBenefeicary) {
        this.paymentId = paymentId;
        this.ibanOfBenefeicary = ibanOfBenefeicary;
        this.successOfTransation = true;
        this.errorMessage = null;
        this.valueTransfered=valueTransfered;
    }

    public PaymentConfirmationBean(String paymentId, float valueTransfered,String ibanOfBenefeicary, String errorMessage) {
        this.paymentId = paymentId;
        this.valueTransfered=valueTransfered;
        this.ibanOfBenefeicary = ibanOfBenefeicary;
        this.successOfTransation = false;
        this.errorMessage = errorMessage;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getIbanOfBenefeicary() {
        return ibanOfBenefeicary;
    }

    public boolean isSuccessOfTransation() {
        return successOfTransation;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public float getValueTransfered() {
        return valueTransfered;
    }
}