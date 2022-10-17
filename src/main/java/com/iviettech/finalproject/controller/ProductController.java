package com.iviettech.finalproject.controller;

import com.iviettech.finalproject.entity.ProductEntity;
import com.iviettech.finalproject.entity.ProductImageEntity;
import com.iviettech.finalproject.repository.CategoryRepository;
import com.iviettech.finalproject.repository.ManufactorRepository;
import com.iviettech.finalproject.repository.ProductImageRepository;
import com.iviettech.finalproject.repository.ProductRepository;
import com.iviettech.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @RequestMapping(method = GET)
    public String home(Model model) {
        List<ProductEntity> productEntityList = productRepository.getProductListWithImage();
        model.addAttribute("productList", productEntityList);
        return "index";
    }
}
