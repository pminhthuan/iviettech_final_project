package com.iviettech.finalproject.controller;

import com.iviettech.finalproject.entity.*;
import com.iviettech.finalproject.repository.*;
import com.iviettech.finalproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

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
    public String newProduct(Model model) {
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
    public String editProduct(Model model, @PathVariable int id) {
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
    public String updateProduct(@ModelAttribute ProductEntity product) {
        productRepository.save(product);
        return "redirect:/admin/adProduct";
    }

    @RequestMapping(value = "/updateProductStatus/{id}")
    public String updateProductStatus(@ModelAttribute ProductEntity product ,@PathVariable int id, Model model) {

        List<ProductEntity> productEntity =
                (List<ProductEntity>) productRepository.findAll();
        for (ProductEntity p : productEntity) {
            if (p.getId() == id) {
                if (p.getStatus() == 0) {
                    p.setStatus(1);
                } else {
                    p.setStatus(0);
                }
                break;
            }
        }
        model.addAttribute("product", product);
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

    @RequestMapping(value = "/newProductDetail", method = GET)
    public String newProductDetail(Model model) {
        model.addAttribute("productDetail", new ProductDetailEntity());
        model.addAttribute("msg", "Add a new product detail");
        model.addAttribute("action", "newProductDetail");

        setProductDropDownlist(model);

        return "admin/ad_edit_product_detail";
    }

    @RequestMapping(value = "/newProductDetail", method = POST, produces = "text/plain;charset=UTF-8")
    public String saveProductDetail(ProductDetailEntity productDetail, Model model) {
        productDetailRepository.save(productDetail);
        model.addAttribute("message","You are add success!");
        return "redirect:/admin/adProductDetail/" + productDetail.getProduct().getId();
    }

    @RequestMapping(value = "/editProductDetail/{id}", method = GET)
    public String editProductDetail(Model model, @PathVariable int id) {
        model.addAttribute("productDetail", productDetailRepository.findById(id));
        model.addAttribute("msg", "Update product detail information");
        model.addAttribute("type", "updateProductDetail");
        model.addAttribute("action", "/admin/updateProductDetail");

        setProductDropDownlist(model);

        return "admin/ad_edit_product_detail";
    }

    @RequestMapping(value = "/updateProductDetail", method = POST)
    public String updateProductDetail(@ModelAttribute ProductDetailEntity productDetail) {
        productDetailRepository.save(productDetail);

        return "redirect:/admin/adProductDetail/" + productDetail.getProduct().getId();
    }

    //Product Image
    @RequestMapping(value = "/adProductImage/{id}", method = GET)
    public String viewProductImage(@PathVariable("id") int id, Model model,HttpSession session) {
        session.setAttribute("idpro",id);
        List<ProductImageEntity> productImageList =
                productImageRepository.findByProduct_Id(id);
        model.addAttribute("productImageList", productImageList);
        model.addAttribute("action", "uploadFile");

        return "admin/ad_product_image";

    }

    @RequestMapping(value = "/deleteImage/{id}/{pid}", method = GET)
    public String deleteProductImage(@PathVariable("id") int id, @PathVariable("pid") int pid, Model model) {

        productImageRepository.deleteById(id);

        return "redirect:/admin/adProductImage/" + pid;
    }

    @RequestMapping(value = "/adProductImage/uploadFile", method = RequestMethod.POST)
    public String saveImage(HttpSession session,
                            @RequestParam(value = "file", required = false) MultipartFile photo ) {
        int id = (int) session.getAttribute("idpro");
        adminService.uploadFile(photo,id);
        return "redirect:/admin/adProductImage/" +id;
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

    @RequestMapping(value = "/newCategory", method = GET)
    public String newCategory(Model model) {
        model.addAttribute("category", new CategoryEntity());
        model.addAttribute("msg", "Add a category");
        model.addAttribute("action", "newCategory");

        return "admin/ad_edit_category";
    }

    @RequestMapping(value = "/newCategory", method = POST, produces = "text/plain;charset=UTF-8")
    public String saveCategory(CategoryEntity category, Model model) {
        categoryRepository.save(category);
        model.addAttribute("message","You are add success!");
        return "redirect:/admin/adCategory";
    }

    @RequestMapping(value = "/editCategory/{id}", method = GET)
    public String editCategory(Model model, @PathVariable int id) {
        model.addAttribute("category", categoryRepository.findById(id));
        model.addAttribute("msg", "Update category information");
        model.addAttribute("type", "updateCategory");
        model.addAttribute("action", "/admin/updateCategory");

        return "admin/ad_edit_category";
    }

    @RequestMapping(value = "/updateCategory", method = POST)
    public String updateCategory(@ModelAttribute CategoryEntity category) {
        categoryRepository.save(category);
        return "redirect:/admin/adCategory";
    }

    //Category detail

    @RequestMapping(value = "/newCategoryDetail", method = GET)
    public String newCategoryDetail(Model model) {
        model.addAttribute("categoryDetail", new CategoryDetailEntity());
        model.addAttribute("msg", "Add a category detail");
        model.addAttribute("action", "newCategoryDetail");

        setCategoryDropDownlist(model);

        return "admin/ad_edit_category_detail";
    }

    @RequestMapping(value = "/newCategoryDetail", method = POST, produces = "text/plain;charset=UTF-8")
    public String saveCategoryDetail(CategoryDetailEntity categoryDetail, Model model) {
        categoryDetailRepository.save(categoryDetail);
        model.addAttribute("message","You are add success!");
        return "redirect:/admin/adCategory";
    }

    @RequestMapping(value = "/editCategoryDetail/{id}", method = GET)
    public String editCategoryDetail(Model model, @PathVariable int id) {
        model.addAttribute("categoryDetail", categoryDetailRepository.findById(id));
        model.addAttribute("msg", "Update category detail information");
        model.addAttribute("type", "updateCategoryDetail");
        model.addAttribute("action", "/admin/updateCategoryDetail");

        setCategoryDropDownlist(model);

        return "admin/ad_edit_category_detail";
    }

    @RequestMapping(value = "/updateCategoryDetail", method = POST)
    public String updateCategoryDetail(@ModelAttribute CategoryDetailEntity categoryDetail) {
        categoryDetailRepository.save(categoryDetail);
        return "redirect:/admin/adCategory";
    }

    //Manufactor
    @RequestMapping(value = "/adManyfactor", method = GET)
    public String viewManufactor(Model model) {

        List<ManufactorEntity> manufactorList =
                (List<ManufactorEntity>) manufactorRepository.findAll();
        model.addAttribute("manufactorList", manufactorList);

        return "admin/ad_manufactor";
    }

    @RequestMapping(value = "/newManufactor", method = GET)
    public String newManufactor(Model model) {
        model.addAttribute("manufactor", new ManufactorEntity());
        model.addAttribute("msg", "Add a manufactor");
        model.addAttribute("action", "newManufactor");

        return "admin/ad_edit_manufactor";
    }

    @RequestMapping(value = "/newManufactor", method = POST, produces = "text/plain;charset=UTF-8")
    public String saveManufactor(ManufactorEntity manufactor, Model model) {
        manufactorRepository.save(manufactor);
        model.addAttribute("message","You are add success!");
        return "redirect:/admin/adManyfactor";
    }

    @RequestMapping(value = "/editManufactor/{id}", method = GET)
    public String editManufactor(Model model, @PathVariable int id) {
        model.addAttribute("manufactor", manufactorRepository.findById(id));
        model.addAttribute("msg", "Update manufactor information");
        model.addAttribute("type", "updateManufactor");
        model.addAttribute("action", "/admin/updateManufactor");

        return "admin/ad_edit_manufactor";
    }

    @RequestMapping(value = "/updateManufactor", method = POST)
    public String updateManufactor(@ModelAttribute ManufactorEntity manufactor) {
        manufactorRepository.save(manufactor);
        return "redirect:/admin/adManyfactor";
    }

    //Order
    @RequestMapping(value = "/adOrder", method = GET)
    public String viewOrder(Model model) {

        List<OrderEntity> orderList =
                (List<OrderEntity>) orderRepository.findAll();
        model.addAttribute("orderList", orderList);

        return "admin/ad_order";
    }

    @RequestMapping(value = "/newOrder", method = GET)
    public String newOrder(Model model) {
        model.addAttribute("order", new OrderEntity());
        model.addAttribute("msg", "Add a order");
        model.addAttribute("action", "neworder");

        return "admin/ad_edit_order";
    }

//    @RequestMapping(value = "/newOrder", method = POST, produces = "text/plain;charset=UTF-8")
//    public String saveOrder(OrderEntity order, Model model) {
//        orderRepository.save(order);
//        model.addAttribute("message","You are add success!");
//        return "redirect:/admin/adOrder";
//    }

    @RequestMapping(value = "/editOrder/{id}", method = GET)
    public String editOrder(Model model, @PathVariable int id) {
        model.addAttribute("order", orderRepository.findById(id));
        model.addAttribute("msg", "Update order information");
        model.addAttribute("type", "updateOrder");
        model.addAttribute("action", "/admin/updateOrder");

        return "admin/ad_edit_order";
    }

    @RequestMapping(value = "/updateOrder", method = POST)
    public String updateOrder(@ModelAttribute OrderEntity order) {
        orderRepository.save(order);
        return "redirect:/admin/adOrder";
    }

    //Order Detail
    @RequestMapping(value = "/adOrderDetail/{id}", method = GET)
    public String viewOrderDetail(Model model, @PathVariable("id") int id) {

        List<OrderDetailEntity> orderDetailList =
                (List<OrderDetailEntity>) orderDetailRepository.findByOrderEntityId(id);
        model.addAttribute("orderDetailList", orderDetailList);

        return "admin/ad_order_detail";
    }

    //Report during the date
//    @RequestMapping(value = "/adReportDate", method = GET)
//    public String viewReportDate(Model model) {
//        List<OrderEntity> orderList =
//                (List<OrderEntity>) orderRepository.findByRequireDateDuringTheDate();
//        model.addAttribute("orderList", orderList);
//
//        return "admin/ad_order";
//    }

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
            model.addAttribute("cateList", cateMap);
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

    private void setProductDropDownlist(Model model) {
        List<ProductEntity> productList = (List<ProductEntity>) productRepository.findAll();
        if (!productList.isEmpty()) {
            Map<Integer, String> productMap = new LinkedHashMap<>();
            for(ProductEntity productEntity : productList) {
                productMap.put(productEntity.getId(), productEntity.getName());
            }
            model.addAttribute("productList", productMap);
        }
    }
}