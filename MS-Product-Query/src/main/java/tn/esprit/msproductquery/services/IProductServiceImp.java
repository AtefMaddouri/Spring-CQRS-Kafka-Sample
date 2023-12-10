package tn.esprit.msproductquery.services;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tn.esprit.dto.ProductDto;
import tn.esprit.msproductquery.entities.Product;
import tn.esprit.msproductquery.repositories.ProductRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class IProductServiceImp implements IProductService{
    private final ProductRepository productRepository;

    @Override
    public Product add(Product product) {
       product.setCreatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean delete(long idProduct) {
         productRepository.deleteById(idProduct);
        return productRepository.existsById(idProduct);
    }

    @Override
    public Page<ProductDto> getProducts(int pageNbr, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNbr,pageSize)).map(ProductDto::mapToProductDto);
    }

    @Override
    public ProductDto getProduct(long id) {
        Product product=  productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("product not found"));
        return ProductDto.mapToProductDto(product);
    }

    @Override
    public ProductDto getProductByName(String name) {
        Product product= productRepository.findByName(name)
                .orElseThrow(() ->new IllegalArgumentException("product not found with this name"));
        return ProductDto.mapToProductDto(product);
    }


}
