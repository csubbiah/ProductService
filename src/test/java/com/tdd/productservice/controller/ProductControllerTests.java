package com.tdd.productservice.controller;



import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(value = ProductController.class)
@AutoConfigureMockMvc
class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenAProductSearchWhen_NameisnotnullAndPricegreaterThanZeroAndQuantityGreaterThanZero_ThenAddProduct() throws Exception {
        // Validate if product is not null

        // Generate Assertion to check if product is returned successfully
        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName", Matchers.is("Test Product")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", Matchers.is(5)));
    }

    @Test
    void givenAProductSearchWhen_NameisNull_ThenReturnBadRequest() throws Exception {
        //Generate Bad request exception using MockMVCResultWatchers
         mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}