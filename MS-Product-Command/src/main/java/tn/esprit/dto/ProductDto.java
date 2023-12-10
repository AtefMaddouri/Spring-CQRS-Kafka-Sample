package tn.esprit.dto;

import tn.esprit.msproductcommand.entities.Product;


import java.time.LocalDateTime;

public record ProductDto(long id, String name, int quantity, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public static Product mapToProduct(ProductDto productDto) {
        return new Product(productDto.id(), productDto.name(), productDto.quantity(), productDto.createdAt(), productDto.updatedAt());
    }

    public static ProductDto mapToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getQuantity(), product.getCreatedAt(), product.getUpdatedAt());
    }
}
