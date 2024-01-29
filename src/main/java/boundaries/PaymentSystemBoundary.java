package boundaries;

import beans.PaymentBean;
import beans.PaymentConfirmationBean;

import java.util.Objects;
import java.util.Random;

public class PaymentSystemBoundary {

    private PaymentBean bean;
    public  PaymentSystemBoundary(PaymentBean bean){
        this.bean=bean;
    }

    public PaymentConfirmationBean pay(){
        if(Objects.equals(bean.getBeneficiary(), "testGym")){
            return new PaymentConfirmationBean("-1",bean.getValue(), bean.getIban(),"fallito perche è un test");
        }
        return new PaymentConfirmationBean("1", bean.getValue(), bean.getIban());
    }
}
