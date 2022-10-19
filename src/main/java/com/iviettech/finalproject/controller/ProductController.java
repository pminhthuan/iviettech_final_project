package com.iviettech.finalproject.controller;

import com.iviettech.finalproject.entity.ProductDetailEntity;
import com.iviettech.finalproject.entity.ProductEntity;
import com.iviettech.finalproject.entity.ProductImageEntity;
import com.iviettech.finalproject.repository.*;
import com.iviettech.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @RequestMapping(method = GET)
    public String viewHome(Model model) {
        List<ProductImageEntity> productEntityList = productImageRepository.getProductListWithImage();
        model.addAttribute("productList", productEntityList);
        return "index";
    }

    @RequestMapping(value = "/shop",method = GET)
    public String viewShop(Model model) {
        List<ProductImageEntity> productEntityList = productImageRepository.getProductListWithImage();
        model.addAttribute("productList", productEntityList);
        return "product";
    }

    @RequestMapping(value = "/view/{id}",method = GET)
    public String showOrderDetail(@PathVariable("id") int id, Model model) {
        List<ProductImageEntity> productImageEntityList = productImageRepository.findByProduct_Id(id);
        List<String> productColorList = productDetailRepository.getColorByProductId(id);
        List<String> productSizeList = productDetailRepository.getSizeByProductId(id);

        model.addAttribute("productImageEntityList", productImageEntityList);
        model.addAttribute("productEntity", productRepository.findById(id));
        model.addAttribute("productColorList", productColorList);
        model.addAttribute("productSizeList", productSizeList);

        return "product_detail";
    }

    @RequestMapping(value = "/cart", method = POST)
    public String showNewBook(Model model) {
        List<ProductEntity> productList = (List<ProductEntity>) productRepository.findAll();
        model.addAttribute("productList", productList);

        return "add_edit_order";
    }





}
