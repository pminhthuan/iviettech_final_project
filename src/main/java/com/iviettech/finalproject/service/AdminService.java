package com.iviettech.finalproject.service;

import com.iviettech.finalproject.entity.*;
import com.iviettech.finalproject.helper.*;
import com.iviettech.finalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminService {
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
    ServletContext servletContext;

    @Autowired
    UserRepository userRepository;

    // ------------------ check role admin
    public boolean isAdminRole(HttpSession session) {

        try {
            String email = (String) session.getAttribute("email"); // get from http session
            return "Admin".equalsIgnoreCase(userRepository.findByEmail(email).getRole().getRoleName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;

    }

    //----------ReportTotal
    public ReportTotal setTotal() {
        ReportTotal reportTotal = new ReportTotal();
        reportTotal.setTotalPrice(reportTotal.totalPrice(orderRepository.getOrder()));
        reportTotal.setTotalYear(reportTotal.totalPrice(orderRepository.getOrderYear()));
        reportTotal.setTotalMonth(reportTotal.totalPrice(orderRepository.getOrderMonth()));
        reportTotal.setTotalWeek(reportTotal.totalPrice(orderRepository.getOrderWeek()));
        reportTotal.setTotalOrder(reportTotal.countOrder(orderRepository.getOrder()));
        reportTotal.setYearOrder(reportTotal.countOrder(orderRepository.getOrderYear()));
        reportTotal.setMonthOrder(reportTotal.countOrder(orderRepository.getOrderMonth()));
        reportTotal.setWeekOrder(reportTotal.countOrder(orderRepository.getOrderWeek()));
        reportTotal.setPercentYear((int) (((reportTotal.getTotalYear()) / (reportTotal.getTotalPrice())) * 100));
        reportTotal.setPercentMonth((int) (((reportTotal.getTotalMonth()) / reportTotal.getTotalYear())) * 100);
        reportTotal.setPercentWeek((int) (((reportTotal.getTotalWeek()) / reportTotal.getTotalMonth())) * 100);

        return reportTotal;
    }

    //---------viewReprotCard
    public void viewReportCard(Model model) {
        ReportTotal reportTotal = setTotal();
        model.addAttribute("allTotal", reportTotal);
    }

    //-------------------Dashboard chart
    public void dashboard_char(Model model){
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
    }

    //-------------new Product
    public void newProduct(Model model) {
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("msg", "Add a new product");
        model.addAttribute("action", "newProduct");

        setCategoryDetailDropDownlist(model);
        setManufactorDropDownlist(model);
        setCategoryDropDownlist(model);
    }

    //-------------EditProduct
    public void editProduct(Model model, int id) {
        model.addAttribute("product", productRepository.findById(id));
        model.addAttribute("msg", "Update product information");
        model.addAttribute("type", "updateProduct");
        model.addAttribute("action", "/admin/updateProduct");

        setCategoryDetailDropDownlist(model);
        setManufactorDropDownlist(model);
        setCategoryDropDownlist(model);
    }

    //------------------ update status product

    public void updateProductStatus(int id, Model model) {
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
    }

    //-----------------newProductDetail

    public void newProductDetail(Model model, HttpSession session) {


        int id = (int) session.getAttribute("idpro");

        model.addAttribute("idpro", id);
//        productDetailEntity.getProduct().setId(id);
//        model.addAttribute("productDetail", productDetailRepository.getByProductId(id));
        model.addAttribute("productDetail", new ProductDetailEntity());
        model.addAttribute("msg", "Add a new product detail");
        model.addAttribute("type", "newProductDetail");
        model.addAttribute("action", "newProductDetail");

        setProductDropDownlist(model);

    }

    //-------------EditProductDetail
    public void editProductDetail(Model model, @PathVariable int id, HttpSession session) {
        model.addAttribute("productDetail", productDetailRepository.findById(id));
        model.addAttribute("msg", "Update product detail information");
        model.addAttribute("type", "updateProductDetail");
        model.addAttribute("action", "/admin/updateProductDetail");

        setProductDropDownlist(model);
    }

    //-------------viewProductImage
    public void viewProductImage(@PathVariable("id") int id, Model model,HttpSession session) {
        session.setAttribute("idpro",id);
        List<ProductImageEntity> productImageList =
                productImageRepository.findByProduct_Id(id);
        model.addAttribute("productImageList", productImageList);
        model.addAttribute("action", "uploadFile");
    }

    //------------uploadImage
    public void uploadImage(MultipartFile file,int id) {
        if( null != file && !file.isEmpty()){
            try {
                byte[] bytes = file.getBytes();
//                String path = "D:\\final\\iviettech_final_project\\src\\main\\webapp\\resources\\images\\products\\";
                String path = servletContext.getRealPath("/resources/images/product/");
                String name = file.getOriginalFilename();
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

//                String name = String.valueOf(new Date().getTime()+".jpg");
                String source = "/resources/images/product/" + name;
                // Create the file on server
                String fileSource = path + File.separator + file.getOriginalFilename();
                File serverFile = new File(fileSource);
                System.out.println("=====Path image to server: " + serverFile.getPath());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                ProductImageEntity imageEntity = new ProductImageEntity();
                Optional<ProductEntity> productEntity = productRepository.findById(id);
                imageEntity.setProduct(productEntity.get());
                imageEntity.setImageUrl(source);
                imageEntity.setImageAlt(name);
                productImageRepository.save(imageEntity);


            } catch (IOException e) {
                System.out.println("====== Error upload file:" + e.getMessage());
            }

        } else {
            System.out.println("====== File not exists");
        }

    }


    //---------------viewCateAndCateDetail
    public void viewCateAndCateDetail(Model model, HttpSession session) {
        List<CategoryEntity> categoryList =
                (List<CategoryEntity>) categoryRepository.findAll();

        List<CategoryDetailEntity> categoryDetailList =
                (List<CategoryDetailEntity>) categoryDetailRepository.findAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("categoryDetailList",categoryDetailList);
    }

    //--------------------newCategory
    public void newCategory(Model model) {
        model.addAttribute("category", new CategoryEntity());
        model.addAttribute("msg", "Add a category");
        model.addAttribute("action", "newCategory");
    }

    //------------------editCate
    public void editCategory(Model model,int id) {
        model.addAttribute("category", categoryRepository.findById(id));
        model.addAttribute("msg", "Update category information");
        model.addAttribute("type", "updateCategory");
        model.addAttribute("action", "/admin/updateCategory");
    }

    //--------newCategoryDetail
    public void newCateDetail(Model model) {
        model.addAttribute("categoryDetail", new CategoryDetailEntity());
        model.addAttribute("msg", "Add a category detail");
        model.addAttribute("action", "newCategoryDetail");

        setCategoryDropDownlist(model);
    }

    public void editCateDetail(Model model, int id) {
        model.addAttribute("categoryDetail", categoryDetailRepository.findById(id));
        model.addAttribute("msg", "Update category detail information");
        model.addAttribute("type", "updateCategoryDetail");
        model.addAttribute("action", "/admin/updateCategoryDetail");

        setCategoryDropDownlist(model);
    }

    //---------------viewManufactor
    public void viewManufactor(Model model) {
        List<ManufactorEntity> manufactorList =
                (List<ManufactorEntity>) manufactorRepository.findAll();
        model.addAttribute("manufactorList", manufactorList);
    }

    //--------newManufactor

    public void newManufactor(Model model) {
        model.addAttribute("manufactor", new ManufactorEntity());
        model.addAttribute("msg", "Add a manufactor");
        model.addAttribute("action", "newManufactor");
    }

    //---------------editmanufactor
    public void editManufactor(Model model, int id) {
        model.addAttribute("manufactor", manufactorRepository.findById(id));
        model.addAttribute("msg", "Update manufactor information");
        model.addAttribute("type", "updateManufactor");
        model.addAttribute("action", "/admin/updateManufactor");
    }

    //-------------viewOrder
    public void viewOrder(Model model) {
        List<OrderEntity> orderList =
                (List<OrderEntity>) orderRepository.findAll();
        model.addAttribute("orderList", orderList);
    }

    //--------------------newOrder
    public void newOrder(Model model) {
        model.addAttribute("order", new OrderEntity());
        model.addAttribute("msg", "Add a order");
        model.addAttribute("action", "neworder");
    }

    public void editOrder(Model model, int id) {
        model.addAttribute("order", orderRepository.findById(id));
        model.addAttribute("msg", "Update order information");
        model.addAttribute("type", "updateOrder");
        model.addAttribute("action", "/admin/updateOrder");
    }

    //--------------updateOrderStatus
    public void updateOrderStatus(int id, Model model) {
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
    }

    //-----------------viewOrderDetail
    public void viewOrderDetail(Model model, int id) {
        List<OrderDetailEntity> orderDetailList =
                (List<OrderDetailEntity>) orderDetailRepository.findByOrderEntityId(id);
        model.addAttribute("orderDetailList", orderDetailList);
    }

    //------------seachOrder
    public void seachOrder(Model model, String startDate, String endDate) {
        java.sql.Date date1 = java.sql.Date.valueOf(startDate);
        java.sql.Date date2 = java.sql.Date.valueOf(endDate);

        List<OrderEntity> orderList =  orderRepository.getOrderFromTo(date1, date2, 0);

        model.addAttribute("orderList", orderList);
    }

    //------------viewAccount
    public void viewAccount(Model model) {
        List<UserEntity> userList =
                (List<UserEntity>) userRepository.findAll();

        model.addAttribute("userList", userList);
    }

    //---------editAccount
    public void editAccount(Model model, int id) {
        model.addAttribute("account", userRepository.findById(id));
        model.addAttribute("msg", "Update account information");
        model.addAttribute("type", "updateAccount");
        model.addAttribute("action", "/admin/updateAccount");
    }

    //------report
    public void reportDate(Model model) {
        List<OrderEntity> orderList = orderRepository.findByRequireDate(new Date());
        model.addAttribute("orderList", orderList);
    }

    public void reportWeek(Model model) {
        List<OrderEntity> orderList = orderRepository.getOrderWeek();
        model.addAttribute("orderList", orderList);
    }

    public void reportMonth(Model model) {
        List<OrderEntity> orderList = orderRepository.getOrderMonth();
        model.addAttribute("orderList", orderList);
    }

    public void reportYear(Model model) {
        List<OrderEntity> orderList = orderRepository.getOrderYear();
        model.addAttribute("orderList", orderList);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


  //---------------------Map
    public void setCategoryDropDownlist(Model model) {
        List<CategoryEntity> cateList = (List<CategoryEntity>) categoryRepository.findAll();
        if (!cateList.isEmpty()) {
            Map<Integer, String> cateMap = new LinkedHashMap<>();
            for(CategoryEntity categoryEntity : cateList) {
                cateMap.put(categoryEntity.getId(), categoryEntity.getName());
            }
            model.addAttribute("cateList", cateMap);
        }
    }

    public void setManufactorDropDownlist(Model model) {
        List<ManufactorEntity> manufactorList = (List<ManufactorEntity>) manufactorRepository.findAll();
        if (!manufactorList.isEmpty()) {
            Map<Integer, String> manufactorMap = new LinkedHashMap<>();
            for(ManufactorEntity manufactorEntity : manufactorList) {
                manufactorMap.put(manufactorEntity.getId(), manufactorEntity.getName());
            }
            model.addAttribute("manufactorList", manufactorMap);
        }
    }

    public void setCategoryDetailDropDownlist(Model model) {
        List<CategoryDetailEntity> cateDetailList = (List<CategoryDetailEntity>) categoryDetailRepository.findAll();
        if (!cateDetailList.isEmpty()) {
            Map<Integer, String> cateDetailMap = new LinkedHashMap<>();
            for(CategoryDetailEntity categoryDetailEntity : cateDetailList) {
                cateDetailMap.put(categoryDetailEntity.getId(), categoryDetailEntity.getDescription());
            }
            model.addAttribute("categoryDetailList", cateDetailMap);
        }
    }

    public void setProductDropDownlist(Model model) {
        List<ProductEntity> productList = (List<ProductEntity>) productRepository.findAll();
        if (!productList.isEmpty()) {
            Map<Integer, String> productMap = new LinkedHashMap<>();
            for(ProductEntity productEntity : productList) {
                productMap.put(productEntity.getId(), productEntity.getName());
            }
            model.addAttribute("productList", productMap);
        }
    }




    public void saveImportProduct(MultipartFile file) {
        try {
            List<ProductEntity> productEntityList = CSVHelper.readProductData(file.getInputStream());

            productRepository.saveAll(productEntityList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportProduct(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Product_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<ProductEntity> productEntityList = (List<ProductEntity>) productRepository.findAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = { "ID", "Name", "Category_Detail_ID", "Orginal_Price", "Actual_Price", "Manufactor_ID", "Add_Date", "Status", "Description", "Infor" };
        String[] nameMapping = { "id", "name", "category_detail_id", "original_price", "actual_price", "manufactor_id", "add_date", "status", "description", "addition_info"};

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

    //---------------------ProductDetail

    public void exportProductDetail(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ProductDetail_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<ProductDetailEntity> productDetailEntityList =
                (List<ProductDetailEntity>) productDetailRepository.findAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = { "ID", "Color", "Quantity", "Size", "Product_ID" };
        String[] nameMapping = { "id", "color", "quantity", "size", "product_id" };

        csvWriter.writeHeader(csvHeader);

        for (ProductDetailEntity productDetailEntity : productDetailEntityList) {
            try {
//                BookRawExport data = new BookRawExport(bookEntity);
                ProductDetailRawExport data = new ProductDetailRawExport(productDetailEntity);
                csvWriter.write(data, nameMapping);
            } catch (Exception e) {
                System.out.println("Skip this record/data");
                continue;
            }
        }

        csvWriter.close();
    }

//    public void saveProductDetail(MultipartFile file) {
//        try {
//            List<ProductDetailEntity> productDetailEntityListEntityList = CSVHelper.readProductData(file.getInputStream());
//
//            productRepository.saveAll(productEntityList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    //-----------------------------------------Export order
    public void exportOrderDetail(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Order_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<OrderDetailEntity> orderDetailEntityList = (List<OrderDetailEntity>) orderDetailRepository.findAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = { "id", "OrderID", "First_Name", "Last_Name", "Email", "Phone_Number", "Address", "Product_Name", "Color", "Size", "Quantity", "Price", "Require_Date", "All_Total", "Payment" };
        String[] nameMapping = { "id", "order_id", "first_name", "last_name", "email", "phone", "address", "product_id", "color", "size", "quantity", "price", "req_date", "total_amount", "payment"};

        csvWriter.writeHeader(csvHeader);

        for (OrderDetailEntity orderDetail : orderDetailEntityList) {
            try {
//                BookRawExport data = new BookRawExport(bookEntity);
                OrderDetailRawExport data = new OrderDetailRawExport(orderDetail);
                csvWriter.write(data, nameMapping);
            } catch (Exception e) {
                System.out.println("Skip this record/data");
                continue;
            }
        }

        csvWriter.close();
    }
//    public void exportOrder(HttpServletResponse response) throws IOException {
//        response.setContentType("text/csv");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=Order_" + currentDateTime + ".csv";
//        response.setHeader(headerKey, headerValue);
//
//        List<OrderEntity> orderEntityList = (List<OrderEntity>) orderRepository.findAll();
//
//        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
//        String[] csvHeader = { "OrderID", "First_Name", "Last_Name", "Email", "Phone_Number", "Address", "Product_Name", "Color", "Size", "Quantity", "Price", "Require_Date", "All_Total", "Payment" };
//        String[] nameMapping = { "order_id", "first_name", "last_name", "email", "phone", "address", "product_id", "color", "size", "quantity", "price", "req_date", "total_amount", "payment"};
//
//        csvWriter.writeHeader(csvHeader);
//
//        for (OrderEntity order : orderEntityList) {
//            try {
////                BookRawExport data = new BookRawExport(bookEntity);
//                OrderRawExport data = new OrderRawExport(order);
//                csvWriter.write(data, nameMapping);
//            } catch (Exception e) {
//                System.out.println("Skip this record/data");
//                continue;
//            }
//        }
//
//        csvWriter.close();
//    }




}
