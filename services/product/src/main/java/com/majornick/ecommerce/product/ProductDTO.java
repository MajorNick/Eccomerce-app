package com.majornick.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    @NotNull
    private String name;

    private String description;
    @Positive(message ="quantity should be positive")
    private Double availableQuantity;
    @Positive(message ="price should be positive")
    private BigDecimal price;
    private Long categoryId;
}
