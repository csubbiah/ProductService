package com.tdd.productservice.model;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    private long id;
    private String name;
    private long price;
    private long quantity;
}