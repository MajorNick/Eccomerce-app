package com.majornick.ecommerce.customer;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    @NotNull(message = "Firstname is required ")
    private String firstName;
    @NotNull(message = "Lastname is required ")
    private String lastName;
    @NotNull(message = "Email required")
    @Email(message = "email is not valid")
    private String email;
    private Address address;
}
