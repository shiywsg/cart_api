package sg.edu.ntu.cart_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.cart_api.helper.PaymentHelper;

@RestController
public class PaymentController {
    
    @Autowired PaymentHelper helper;

    // Path Variable - /payment/sgd
    // Request Parameter - ?payable=5.2
    @RequestMapping(value="/payment", method=RequestMethod.POST)
    public ResponseEntity<ResponseMessage> payment(float payable){

        try{
            if(helper.hasMinimumPayment(payable)){
                return ResponseEntity.ok().body(new ResponseMessage("Payment successful"));
            }
            return ResponseEntity.badRequest().body(new ResponseMessage("Payable is below minimum purchase."));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
        }
        
    }
}

class ResponseMessage{
    String message;

    // Constructor
    public ResponseMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}