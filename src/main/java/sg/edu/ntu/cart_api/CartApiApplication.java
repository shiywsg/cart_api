package sg.edu.ntu.cart_api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.ntu.cart_api.helper.PaymentHelper;

@SpringBootApplication
public class CartApiApplication {

	@Value("${MIN_PURCHASE}") //必须放在这里
    float minimumPurchase;
	public static void main(String[] args) {
		SpringApplication.run(CartApiApplication.class, args);
	}

	@Bean //意思是放进spring container
	public PaymentHelper paymentHelper(){//在这里new一个实例出来，把那个最低购买变量打进去

		return new PaymentHelper(minimumPurchase);

	}


}
