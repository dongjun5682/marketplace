package com.coupang.marketplace.product.controller;

import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.product.controller.dto.GetProductRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public SuccessResponse getProducts(@Valid @ModelAttribute GetProductRequest dto) {
        return SuccessResponse.builder().build();
    }
}
