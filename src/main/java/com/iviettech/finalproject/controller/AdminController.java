package com.iviettech.finalproject.controller;

import com.iviettech.finalproject.entity.*;
import com.iviettech.finalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @RequestMapping(value = "/newProduct", method = GET)
    public String showNewProduct(Model model) {
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("msg", "Add a new product");
        model.addAttribute("action", "newProduct");

        setCategoryDetailDropDownlist(model);
        setManufactorDropDownlist(model);
        setCategoryDropDownlist(model);

        return "admin/ad_edit_product";
    }

    @RequestMapping(value = "/newProduct", method = POST, produces = "text/plain;charset=UTF-8")
    public String saveProduct(ProductEntity product, Model model) {
        productRepository.save(product);
        model.addAttribute("message","You are add success!");
        return "redirect:/admin/adProduct";
    }

    @RequestMapping(value = "/editProduct/{id}", method = GET)
    public String showEditBook(Model model, @PathVariable int id) {
        model.addAttribute("product", productRepository.findById(id));
        model.addAttribute("msg", "Update product information");
        model.addAttribute("type", "updateProduct");
        model.addAttribute("action", "/admin/updateProduct");

        setCategoryDetailDropDownlist(model);
        setManufactorDropDownlist(model);
        setCategoryDropDownlist(model);
        return "admin/ad_edit_product";
    }

    @RequestMapping(value = "/updateProduct", method = POST)
    public String updateBook(@ModelAttribute ProductEntity product) {
        productRepository.save(product);
        return "redirect:/admin/adProduct";
    }

    //Product Details

    @RequestMapping(value = "/adProductDetail/{id}", method = GET)
    public String viewProductDetail(@PathVariable("id") int id, Model model) {
        List<ProductDetailEntity> productDetailsList =
                (List<ProductDetailEntity>) productDetailRepository.findProductDetailEntityByProduct_Id(id);
        model.addAttribute("productDetailsList", productDetailsList);

        return "admin/ad_product_detail";
    }

    //Product Image
    @RequestMapping(value = "/adProductImage/{id}", method = GET)
    public String viewProductImage(@PathVariable("id") int id, Model model) {

        List<ProductImageEntity> productImageList =
                productImageRepository.findByProduct_Id(id);
        model.addAttribute("productImageList", productImageList);

        return "admin/ad_product_image";

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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    //Map
    private void setCategoryDropDownlist(Model model) {
        List<CategoryEntity> cateList = (List<CategoryEntity>) categoryRepository.findAll();
        if (!cateList.isEmpty()) {
            Map<Integer, String> cateMap = new LinkedHashMap<>();
            for(CategoryEntity categoryEntity : cateList) {
                cateMap.put(categoryEntity.getId(), categoryEntity.getName());
            }
            model.addAttribute("categoryList", cateMap);
        }
    }

    private void setManufactorDropDownlist(Model model) {
        List<ManufactorEntity> manufactorList = (List<ManufactorEntity>) manufactorRepository.findAll();
        if (!manufactorList.isEmpty()) {
            Map<Integer, String> manufactorMap = new LinkedHashMap<>();
            for(ManufactorEntity manufactorEntity : manufactorList) {
                manufactorMap.put(manufactorEntity.getId(), manufactorEntity.getName());
            }
            model.addAttribute("manufactorList", manufactorMap);
        }
    }

    private void setCategoryDetailDropDownlist(Model model) {
        List<CategoryDetailEntity> cateDetailList = (List<CategoryDetailEntity>) categoryDetailRepository.findAll();
        if (!cateDetailList.isEmpty()) {
            Map<Integer, String> cateDetailMap = new LinkedHashMap<>();
            for(CategoryDetailEntity categoryDetailEntity : cateDetailList) {
                cateDetailMap.put(categoryDetailEntity.getId(), categoryDetailEntity.getDescription());
            }
            model.addAttribute("categoryDetailList", cateDetailMap);
        }
    }
}