package tn.esprit.msproductquery.services;

import org.springframework.data.domain.Page;
import tn.esprit.dto.ProductDto;
import tn.esprit.msproductquery.entities.Product;

import java.util.Map;

public interface IProductService {

    Product add(Product product);
    Product update(Product product);
    boolean delete(long idProduct);


    Page<ProductDto> getProducts(int pageNbr, int pageSize);

    ProductDto getProduct(long id);

    ProductDto getProductByName(String name);
}
