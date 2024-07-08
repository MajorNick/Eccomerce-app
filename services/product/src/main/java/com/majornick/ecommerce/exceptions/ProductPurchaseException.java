package com.majornick.ecommerce.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class ProductPurchaseException extends RuntimeException{
    private String message;
}
