package com.majornick.ecommerce.category;

import com.majornick.ecommerce.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "category")
    private List<Product> products;
}
