package com.majornick.ecommerce.product;

import com.majornick.ecommerce.exceptions.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductModelMapper productModelMapper;
    public ProductDTO findById(Long productId) {
        return productModelMapper
                .toDTO(productRepo
                .findById(productId)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Product with Id : %d not found",productId))
                ));
    }

    public List<ProductDTO> findAll() {
        return productRepo
                .findAll()
                .stream()
                .map(productModelMapper::toDTO)
                .toList();
    }

    public ProductDTO save(@Valid ProductDTO productDTO) {
        Product product = productModelMapper.toProduct(productDTO);
        return productModelMapper.toDTO(productRepo.save(product));
    }

    @Transactional
    public void purchaseProducts(@Valid List<ProductPurchaseRequest> purchaseList) {
        List<Product> products = new ArrayList<>();
        for(int i=0;i<purchaseList.size();i++){
            int finalI = i;
            Product product = productRepo
                    .findById(purchaseList.get(i).getProductId())
                    .orElseThrow(() -> new ProductPurchaseException(String.format("Product with Id : %d not found", purchaseList.get(finalI).getProductId())));
            if(product.getAvailableQuantity() < purchaseList.get(i).getQuantity()){
                throw new ProductPurchaseException(String.format("Not Enough quantity for product with id: %d ",product.getId()));
            }
            products.add(product);
        }
        for(int i=0;i<products.size();i++){
            ProductPurchaseRequest purchaseRequest = purchaseList.get(i);
            Product product = products.get(i);
            product.setAvailableQuantity(product.getAvailableQuantity() - purchaseRequest.getQuantity());
        }
    }
}
