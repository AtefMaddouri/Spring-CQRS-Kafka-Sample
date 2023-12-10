package tn.esprit.msproductquery.events;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.dto.ProductDto;
import tn.esprit.msproductquery.services.IProductService;

@Service
@RequiredArgsConstructor
public class ProductEventHandler {
    private final IProductService productService;

    public void handleProductCreatedEvent(ProductDto productDto) {
        productService.add(ProductDto.mapToProduct(productDto));
    }

    public void handleProductUpdatedEvent(ProductDto productDto) {
        productService.update(ProductDto.mapToProduct(productDto));
    }

    public void handleProductDeletedEvent(long idProduct) {
        productService.delete(idProduct);
    }
}
