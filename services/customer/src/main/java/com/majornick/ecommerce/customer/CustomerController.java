package com.majornick.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping
    public ResponseEntity<?> createCustomer(
            @RequestBody @Valid CustomerDTO customerDTO
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(customerDTO));
    }
    @PutMapping
    public ResponseEntity<?> updateCustomer(
            @RequestBody @Valid CustomerDTO customerDTO
    ) {
        customerService.updateCustomer(customerDTO);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> findById(
            @PathVariable("customerId") Long customerId
    ) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> delete(
            @PathVariable("customerId") Long customerId
    ) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }

}
