package tn.esprit.msproductcommand.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.dto.ProductDto;
import tn.esprit.msproductcommand.services.IProductService;

import java.util.Map;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductRestController{
    private final IProductService productService;

    @PostMapping
    public ProductDto add(@RequestBody ProductDto productDto) {
        return productService.add(productDto);
    }

    @PatchMapping("{id}")
    public ProductDto patchUpdate(@RequestBody Map<Object,Object> fields, @PathVariable long id){
        return productService.update(id,fields);
    }

    @DeleteMapping("{id}")
    public boolean delete( @PathVariable long id){
        return productService.delete(id);
    }







}
