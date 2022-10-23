package com.iviettech.finalproject.controller;

import com.iviettech.finalproject.entity.*;
import com.iviettech.finalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryDetailRepository categoryDetailRepository;

    @Autowired
    ManufactorRepository manufactorRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @RequestMapping(method = GET)
    public String viewAdmin(Model model) {

        return "admin/ad_home";
    }

    //Product

    @RequestMapping(value = "/adProduct", method = GET)
    public String viewProduct(Model model) {
        List<ProductEntity> productList = (List<ProductEntity>) productRepository.findAll();
        model.addAttribute("productList", productList);

        return "admin/ad_product";
    }

    //Product Details

    @RequestMapping(value = "/adProductDetail/{id}", method = GET)
    public String viewProductDetail(@PathVariable("id") int id, Model model) {
        List<ProductDetailEntity> productDetailsList =
                (List<ProductDetailEntity>) productDetailRepository.findProductDetailEntityByProduct_Id(id);
        model.addAttribute("productDetailsList", productDetailsList);

        return "admin/ad_product_detail";
    }

    //Category
    @RequestMapping(value = "/adCategory", method = GET)
    public String viewCategory(Model model){

        List<CategoryEntity> categoryList =
                (List<CategoryEntity>) categoryRepository.findAll();

        List<CategoryDetailEntity> categoryDetailList =
                (List<CategoryDetailEntity>) categoryDetailRepository.findAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("categoryDetailList",categoryDetailList);

        return "admin/ad_category";
    }

    //Mamufactor
    @RequestMapping(value = "/adManyfactor", method = GET)
    public String viewManufactor(Model model) {

        List<ManufactorEntity> manufactorList =
                (List<ManufactorEntity>) manufactorRepository.findAll();
        model.addAttribute("manufactorList", manufactorList);

        return "admin/ad_manufactor";
    }

    //Order
    @RequestMapping(value = "/adOrder", method = GET)
    public String viewOrder(Model model) {

        List<OrderEntity> orderList =
                (List<OrderEntity>) orderRepository.findAll();
        model.addAttribute("orderList", orderList);

        return "admin/ad_order";
    }

    //Order Detail
    @RequestMapping(value = "/adOrderDetail/{id}", method = GET)
    public String viewOrderDetail(Model model, @PathVariable("id") int id) {

        List<OrderDetailEntity> orderDetailList =
                (List<OrderDetailEntity>) orderDetailRepository.findByOrderEntityId(id);
        model.addAttribute("orderDetailList", orderDetailList);

        return "admin/ad_order_detail";
    }
}