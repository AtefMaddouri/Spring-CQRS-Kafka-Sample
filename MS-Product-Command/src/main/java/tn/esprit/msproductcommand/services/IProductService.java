package tn.esprit.msproductcommand.services;

import tn.esprit.dto.ProductDto;

import java.util.Map;

public interface IProductService {

    ProductDto add(ProductDto productDto);

    ProductDto update(long idProduct, Map<Object,Object> fields);

    boolean delete(long idProduct);


}
