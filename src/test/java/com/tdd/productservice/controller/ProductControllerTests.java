package com.tdd.productservice.controller;

import com.tdd.productservice.controller.ProductController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenAProductWhen_NameisnotnullAndPricegreaterThanZeroAndQuantityGreaterThanZero_ThenAddProduct() throws Exception {
        String productJson = "{\"name\":\"Test Product\",\"price\":10.0,\"quantity\":5}";

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .content(productJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Test Product")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(10.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", Matchers.is(5)));
    }

    @Test
    void givenAProductWhen_NameisNull_ThenReturnBadRequest() throws Exception {
        String productJson = "{\"name\":null,\"price\":10.0,\"quantity\":5}";

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .content(productJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
