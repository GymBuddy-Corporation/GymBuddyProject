package beans;

public class PaymentBean {

    private String iban;
    private String beneficiary;
    private String membership;
    private float value;
    private CardInfoBean infoBean;
    private String email;

    public PaymentBean(String iban, String beneficiary, String membership, float value, CardInfoBean infoBean, String email) {
        this.iban = iban;
        this.beneficiary = beneficiary;
        this.membership = membership;
        this.value = value;
        this.infoBean = infoBean;
        this.email = email;
    }

    public String getIban() {
        return iban;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public String getMembership() {
        return membership;
    }

    public float getValue() {
        return value;
    }

    public CardInfoBean getInfoBean() {
        return infoBean;
    }

    public String getEmail() {
        return email;
    }
}