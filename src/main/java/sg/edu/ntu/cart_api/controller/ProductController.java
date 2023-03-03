package sg.edu.ntu.cart_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.cart_api.entity.Product;
import sg.edu.ntu.cart_api.service.ProductService;
import sg.edu.ntu.cart_api.repository.ProductRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    ProductRepository repo;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = (List<Product>)repo.findAll(); // fetch all products from database 
        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> findById(@PathVariable int id){
        Optional<Product> optional = repo.findById(id);
        if(optional.isPresent()){ // Check if a product is returned
            Product product = optional.get(); // Get the product from Optional
            return ResponseEntity.ok().body(product);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product create(@RequestBody Product product){
        return this.service.create();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public Product update(@RequestBody Product product, @PathVariable int id){
        product.setId(id);
        return product;
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id){
        System.out.println("DELETE /products/"+id+" is being called");        
    }

}