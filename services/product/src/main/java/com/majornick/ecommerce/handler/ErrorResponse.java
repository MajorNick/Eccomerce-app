package com.majornick.ecommerce.handler;

import lombok.Data;

import java.util.Map;


public record ErrorResponse(Map<String, String> errors) {
}
