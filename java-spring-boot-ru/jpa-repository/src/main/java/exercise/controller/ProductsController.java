package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> showBetween(@RequestParam (required=false,name="min") Integer startPrice,@RequestParam(required=false,name="max") Integer endPrice){

        if ((startPrice==null /*||startPrice.equals(30) */) &&( endPrice==null /*|| endPrice.equals(40)*/)){
            return productRepository.findAll(Sort.by(Sort.Order.asc("price")));
        }
        else {
        var between  = productRepository.findByPriceBetween(startPrice,endPrice);
        return  between.stream().sorted(Comparator.comparingDouble(Product::getPrice)).toList();
        }
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
