package sg.edu.ntu.cart_api.helper;

import java.text.DecimalFormat;

public class PaymentHelper {

    //@value() will not work because not managed by sprint container
    float minimumPurchase;
    final DecimalFormat df = new DecimalFormat("0.00");

    public PaymentHelper(float minimumPurchase){
        this.minimumPurchase = minimumPurchase;
    }

    public boolean hasMinimumPayment(float payable) throws Exception {
        
        if(payable < 0) throw new Exception("Payable cannot be a negative value"); 

    if(payable < minimumPurchase) throw new Exception("Payable must be greater than $"+df.format(minimumPurchase));

    return true; //正确200

    }
}





