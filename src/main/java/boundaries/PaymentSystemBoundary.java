package boundaries;

import beans.PaymentBean;
import beans.PaymentConfirmationBean;

import java.util.Objects;

public class PaymentSystemBoundary {

    private final PaymentBean bean;
    public  PaymentSystemBoundary(PaymentBean bean){
        this.bean=bean;
    }

    public PaymentConfirmationBean pay(){
        if(Objects.equals(bean.getBeneficiary(), "testGym")){
            return new PaymentConfirmationBean("-1",bean.getValue(), bean.getIban(),"fallito perche è un test");
        }
        return new PaymentConfirmationBean("1", bean.getValue(), bean.getIban());
    }

    public PaymentConfirmationBean refund(PaymentConfirmationBean bean){
        PaymentConfirmationBean confBean=new PaymentConfirmationBean(bean.getPaymentId(), bean.getValueTransfered(), bean.getIbanOfBenefeicary());
        confBean.setValueTransfered(0);
        confBean.setSuccessOfTransation(false);
        confBean.setErrorMessage("Transation revoked");
        return confBean;
    }
}
