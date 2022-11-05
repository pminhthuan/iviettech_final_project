package com.iviettech.finalproject.controller;

import com.iviettech.finalproject.entity.*;
import com.iviettech.finalproject.helper.ProductRawExport;
import com.iviettech.finalproject.repository.*;
import com.iviettech.finalproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
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

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = GET)
    public String viewAdmin(Model model) {

        Double totalDay = orderRepository.getTotalDay();
        model.addAttribute("totalDay", totalDay);

        // dashboard chart
        int[] totalPricePerMonth = new int[12];
        int[] totalOrderPerMonth = new int[12];
        List<OrderEntity> allOrders = (List<OrderEntity>) orderRepository.findAll();
        for (OrderEntity order : allOrders) {
            int month = order.getRequireDate().getMonth();
            totalPricePerMonth[month] = totalPricePerMonth[month] + (int)order.getTotalAmount();
            totalOrderPerMonth[month] = totalOrderPerMonth[month] + 1;
        }
        String totalOrderPrice = Arrays.toString(totalPricePerMonth).substring(1, Arrays.toString(totalPricePerMonth).length() - 1);
        String totalOrderNumber = Arrays.toString(totalOrderPerMonth).substring(1, Arrays.toString(totalOrderPerMonth).length() - 1);
        // for bar chart
        model.addAttribute("total_order_price", totalOrderPrice);
        model.addAttribute("total_order_number", totalOrderNumber);


        // for data table
        // get latest 5 pending order (order status = 0 for example)
        model.addAttribute("order_data_list", orderRepository.findTop5ByOrderStatusOrderByIdDesc(0));

        return "admin/ad_home";
    }

    //-----------------------------Product

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
    public String updateProductStatus(@PathVariable int id, Model model) {

       Optional<ProductEntity> productEntity = productRepository.findById(id);
       if (productEntity.isPresent()) {
           ProductEntity product = productEntity.get();
           if (product.getStatus() == 0) {
               product.setStatus(1);
           } else {
               product.setStatus(0);
           }
           model.addAttribute("product", product);
           productRepository.save(product);
       }
        return "redirect:/admin/adProduct";

    }

    @GetMapping("/exportProduct")
    public void exportCSVfile(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Product_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<ProductEntity> productEntityList = (List<ProductEntity>) productRepository.findAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = { "Name", "Category_Detail_ID", "Orginal_Price", "Actual_Price", "Manufactor_ID", "Add_Date", "Status", "Description", "Infor" };
        String[] nameMapping = { "name", "category_detail_id", "original_price", "actual_price", "manufactor_id", "add_date", "status", "description", "addition_info"};

        csvWriter.writeHeader(csvHeader);

        for (ProductEntity productEntity : productEntityList) {
            try {
//                BookRawExport data = new BookRawExport(bookEntity);
                ProductRawExport data = new ProductRawExport(productEntity);
                csvWriter.write(data, nameMapping);
            } catch (Exception e) {
                System.out.println("Skip this record/data");
                continue;
            }
        }

        csvWriter.close();
    }

    // ---------------------------Product Details

    @RequestMapping(value = "/adProductDetail/{id}", method = GET)
    public String viewProductDetail(@PathVariable("id") int id, Model model, HttpSession session) {
        session.setAttribute("idpro",id);
        List<ProductDetailEntity> productDetailsList =
                (List<ProductDetailEntity>) productDetailRepository.findProductDetailEntityByProduct_Id(id);
        model.addAttribute("productDetailsList", productDetailsList);

        return "admin/ad_product_detail";
    }

    @RequestMapping(value = "/newProductDetail", method = GET)
    public String newProductDetail(Model model, HttpSession session) {
        int id = (int) session.getAttribute("idpro");

        model.addAttribute("idpro", id);
//        productDetailEntity.getProduct().setId(id);
//        model.addAttribute("productDetail", productDetailRepository.getByProductId(id));
        model.addAttribute("productDetail", new ProductDetailEntity());
        model.addAttribute("msg", "Add a new product detail");
        model.addAttribute("type", "newProductDetail");
        model.addAttribute("action", "newProductDetail");

        setProductDropDownlist(model);

        return "admin/ad_edit_product_detail";
    }

    @RequestMapping(value = "/newProductDetail", method = POST, produces = "text/plain;charset=UTF-8")
    public String saveProductDetail(ProductDetailEntity productDetail, Model model, HttpSession session) {
        int id = (int) session.getAttribute("idpro");

        productDetailRepository.save(productDetail);
        model.addAttribute("message","You are add success!");
        return "redirect:/admin/adProductDetail/" + id;
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
    public String updateOrder(@ModelAttribute("order") OrderEntity order) {
        orderRepository.save(order);
        return "redirect:/admin/adOrder";
    }

    @RequestMapping(value = "/updateOrderStatus/{id}")
    public String updateOrderStatus(@PathVariable int id, Model model) {

        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        if (orderEntity.isPresent()) {
            OrderEntity order = orderEntity.get();
            if (order.getOrderStatus() == 0) {
                order.setOrderStatus(1);
            } else {
                order.setOrderStatus(0);
            }
            model.addAttribute("order", order);
            orderRepository.save(order);
        }
        return "redirect:/admin/adProduct";

    }

    //Order Detail
    @RequestMapping(value = "/adOrderDetail/{id}", method = GET)
    public String viewOrderDetail(Model model, @PathVariable("id") int id) {

        List<OrderDetailEntity> orderDetailList =
                (List<OrderDetailEntity>) orderDetailRepository.findByOrderEntityId(id);
        model.addAttribute("orderDetailList", orderDetailList);

        return "admin/ad_order_detail";
    }

//------------------------Account------

    @RequestMapping(value = "/adAccount", method = GET)
    public String viewAccount(Model model) {
        List<UserEntity> userList =
                (List<UserEntity>) userRepository.findAll();

        model.addAttribute("userList", userList);

        return "admin/ad_account";
    }

    @RequestMapping(value = "/newAccount", method = GET)
    public String newAccount(Model model) {
        model.addAttribute("account", new UserEntity());
        model.addAttribute("msg", "Add account");
        model.addAttribute("action", "newAccount");

        return "admin/ad_edit_account";
    }

    @RequestMapping(value = "/editAccount/{id}", method = GET)
    public String editAccount(Model model, @PathVariable int id) {
        model.addAttribute("account", userRepository.findById(id));
        model.addAttribute("msg", "Update account information");
        model.addAttribute("type", "updateAccount");
        model.addAttribute("action", "/admin/updateAccount");

        return "admin/ad_edit_account";
    }

    @RequestMapping(value = "/updateAccount", method = POST)
    public String updateAccount(@ModelAttribute UserEntity user) {
        userRepository.save(user);
        return "redirect:/admin/adAccount";
    }

//    Report during the date
    @RequestMapping(value = "/adReportDate", method = GET)
    public String viewReportDate(Model model) {
//        LocalDateTime date = LocalDateTime.now();
//        String dt = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(date);
        List<OrderEntity> orderList = orderRepository.findByRequireDate(new Date());
        model.addAttribute("orderList", orderList);

        return "admin/ad_order";
    }

    // report in week
    @RequestMapping(value = "/adReportWeek", method = GET)
    public String viewReportWeek(Model model) {

        List<OrderEntity> orderList = orderRepository.getOrderWeek();
        model.addAttribute("orderList", orderList);

        return "admin/ad_order";
    }

//    Report during the month
    @RequestMapping(value = "/adReportMonth", method = GET)
    public String viewReportMonth(Model model) {
//        LocalDateTime date = LocalDateTime.now();
//        String dt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(date);
//        String dt1 = DateTimeFormatter.ofPattern("01/MM/yyyy", Locale.ENGLISH).format(date);
//        Date date1 = new SimpleDateFormat("01/MM/yyyy").parse(String.valueOf(new Date()));
//        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(new Date()));
////        Date date = new Date();

        List<OrderEntity> orderList = orderRepository.getOrderMonth();
        model.addAttribute("orderList", orderList);

        return "admin/ad_order";
    }

    // report in year
    @RequestMapping(value = "/adReportYear", method = GET)
    public String viewReportYear(Model model) {

        List<OrderEntity> orderList = orderRepository.getOrderYear();
        model.addAttribute("orderList", orderList);

        return "admin/ad_order";
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