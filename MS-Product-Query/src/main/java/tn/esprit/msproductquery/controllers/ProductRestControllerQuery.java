package tn.esprit.msproductquery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import tn.esprit.dto.ProductDto;
import tn.esprit.msproductquery.services.IProductService;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductRestControllerQuery {

    private final IProductService productService;

    @GetMapping
    public Page<ProductDto> getProducts(int pageNbr, int pageSize){
        return productService.getProducts(pageNbr,pageSize);
    }

    @GetMapping("{id}")
    public ProductDto getProduct(@PathVariable long id){
        return productService.getProduct(id);
    }

    @GetMapping("name/{name}")
    public ProductDto getProduct(@PathVariable String name){
        return productService.getProductByName(name);
    }







}
