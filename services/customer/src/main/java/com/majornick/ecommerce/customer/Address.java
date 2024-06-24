package com.majornick.ecommerce.customer;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Validated
@Getter
@Setter
@Embeddable
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
