package com.majornick.ecommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long productId){
        return ResponseEntity.ok(productService.findById(productId));
    }
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDTO));
    }
    @PostMapping("/purchase")
    public ResponseEntity<List<ProductDTO>> purchaseProducts(@RequestBody @Valid List<ProductPurchaseRequest> products){
        productService.purchaseProducts(products);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
