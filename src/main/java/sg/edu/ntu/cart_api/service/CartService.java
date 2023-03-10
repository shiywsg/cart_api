package sg.edu.ntu.cart_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.ntu.cart_api.entity.Cart;
import sg.edu.ntu.cart_api.entity.Product;
import sg.edu.ntu.cart_api.exception.NotFoundException;
import sg.edu.ntu.cart_api.repository.CartRepository;
import sg.edu.ntu.cart_api.repository.ProductRepository;

@Service
public class CartService { //业务逻辑别写在controller里面，必须写在service里面
    
    @Autowired
    CartRepository cartRepo;

    @Autowired
    ProductRepository productRepo;

    public void add(int productId, Optional<Integer> quantity,int userId) throws NotFoundException{
        Optional<Cart> optionalCartItem = cartRepo.findByProductIdAndUserId(productId, userId);
        
        if(optionalCartItem.isPresent()){

            // Product found in cart
            Cart cartItem = optionalCartItem.get();
            int currentQuantity = cartItem.getQuantity();
            cartItem.setQuantity(quantity.orElseGet(() -> currentQuantity + 1)); // If quantity param not exist, just increment by 1
            cartRepo.save(cartItem);
        }else{

            // Product not found in cart
            Optional<Product> optionalProduct = productRepo.findById(productId);
            if(optionalProduct.isPresent()){
                Product product = optionalProduct.get();
                Cart cartItem = new Cart();
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity.orElseGet(() -> 1));
                cartRepo.save(cartItem);
            }else{
                throw new NotFoundException("ProductID is not found");
            }
        }
    }

    public void decrement(int productId, int userId) throws NotFoundException{
        Optional<Cart> optionalCartItem = cartRepo.findByProductIdAndUserId(productId, userId);        
        if(optionalCartItem.isPresent()){

            int currentQuantity = 0;
            Cart cart = optionalCartItem.get();
            if(cart.getQuantity() == 1) 
            cartRepo.deleteById(cart.getId());
            else{
                currentQuantity = cart.getQuantity();
                cart.setQuantity(currentQuantity - 1);
                cartRepo.save(cart);
            }                
        } else {
            throw new NotFoundException("ProductID is not found"); //检查期exception肯定会出的，一定要try catch，运行exception不一定发生，不强制try catch
        }
    }    

}
