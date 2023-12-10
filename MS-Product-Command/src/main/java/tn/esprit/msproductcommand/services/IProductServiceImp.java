package tn.esprit.msproductcommand.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import tn.esprit.dto.EventType;
import tn.esprit.dto.ProductDto;
import tn.esprit.dto.Event;
import tn.esprit.msproductcommand.entities.Product;
import tn.esprit.msproductcommand.events.KafkaProducer;
import tn.esprit.msproductcommand.repositories.ProductRepository;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IProductServiceImp implements IProductService{
    private final ProductRepository productRepository;
    private final KafkaProducer kafkaProducer;

    @Override
    public ProductDto add(ProductDto productDto) {
        Product product = ProductDto.mapToProduct(productDto);
        product.setCreatedAt(LocalDateTime.now());
        productDto = ProductDto.mapToProductDto(productRepository.save(product));
        kafkaProducer.produceEvent(new Event(EventType.CREATED_PRODUCT_EVENT, productDto,LocalDateTime.now()));
        return productDto;
    }

    @Override
    public ProductDto update(long idProduct, Map<Object, Object> fields) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + idProduct));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Product.class, (String) key);
            field.setAccessible(true);

            if(field.getType().equals(LocalDate.class)){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
                LocalDate localDate = LocalDate.parse((String) value, formatter);
                ReflectionUtils.setField(field, product , localDate);
            }else {
                ReflectionUtils.setField(field, product , value);
            }

        });
        product.setUpdatedAt(LocalDateTime.now());
        ProductDto productDto = ProductDto.mapToProductDto(productRepository.save(product));
        kafkaProducer.produceEvent(new Event(EventType.UPDATED_PRODUCT_EVENT, productDto,LocalDateTime.now()));
        return productDto;
    }

    @Override
    public boolean delete(long idProduct) {

        ProductDto productDto = ProductDto.mapToProductDto(productRepository.findById(idProduct)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + idProduct)));

        productRepository.deleteById(idProduct);
        kafkaProducer.produceEvent(new Event(EventType.DELETED_PRODUCT_EVENT, productDto,LocalDateTime.now()));

        return productRepository.existsById(idProduct);
    }



}
