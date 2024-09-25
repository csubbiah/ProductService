package com.tdd.productservice.model;

import lombok.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    @NotNull(message = "ID cannot be null")
    private Integer id;

    @NotEmpty(message = "Product name cannot be empty")
    private String name;

    @Min(value = 0, message = "Price cannot be negative")
    private double price;

    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;
}