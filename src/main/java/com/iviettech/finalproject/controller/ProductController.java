package com.iviettech.finalproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.iviettech.finalproject.entity.*;
import com.iviettech.finalproject.helper.GmailSender;
import com.iviettech.finalproject.pojo.CartItem;
import com.iviettech.finalproject.repository.*;
import com.iviettech.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.iviettech.finalproject.helper.PasswordEncoder.createHash;
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

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    WardRepository wardRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryDetailRepository categoryDetailRepository;


    @RequestMapping(method = GET)
    public String viewHome(Model model) {
        List<ProductImageEntity> productEntityList = productImageRepository.getProductListWithImage();
        List<CategoryEntity> categoryEntityList = (List<CategoryEntity>) categoryRepository.findAll();
        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("productList", productEntityList);
        return "index";
    }

    @RequestMapping(value = "/shop",method = GET)
    public String viewShop(Model model) {
        List<ProductImageEntity> productEntityList = productImageRepository.getProductListWithImage();
        List<CategoryEntity> categoryEntityList = (List<CategoryEntity>) categoryRepository.findAll();
        //List<CategoryDetailEntity> categoryDetailEntityList = categoryDetailRepository.findAllByCategory_Id()
        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("productList", productEntityList);
        model.addAttribute("activeLink", "how-active1");
        return "product";
    }


    @RequestMapping(value = "/shop/category/{id}",method = GET)
    public String showProductByCategory(@PathVariable("id") int id, Model model) {
        List<ProductImageEntity> productEntityList = productImageRepository.getProductListWithImageAndCategory(id);
        List<CategoryEntity> categoryEntityList = (List<CategoryEntity>) categoryRepository.findAll();
        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("productList", productEntityList);
        model.addAttribute("tag", id);

        return "product";
    }


    @RequestMapping(value = "/shop/view/{id}",method = GET)
    public String showOrderDetail(@PathVariable("id") int id, Model model) {
        List<ProductImageEntity> productImageEntityList = productImageRepository.findByProduct_Id(id);
        List<String> productColorList = productDetailRepository.getColorByProductId(id);
        List<String> productSizeList = productDetailRepository.getSizeByProductId(id);
        List<ProductDetailEntity> productDetailEntityList = productDetailRepository.findProductDetailEntityByProduct_Id(id);
        int categoryDetailId = productRepository.getCategoryDetailIdByProductId(id);

        List<ProductImageEntity> relatedProductList = productImageRepository.getRelatedProductByCategoryDetail(categoryDetailId, id); //related product
        model.addAttribute("relatedProductList", relatedProductList); //related product
        model.addAttribute("productImageEntityList", productImageEntityList);
        model.addAttribute("productEntity", productRepository.findById(id));
        model.addAttribute("productColorList", productColorList);
        model.addAttribute("productSizeList", productSizeList);
        model.addAttribute("productDetailEntityList",productDetailEntityList);
        model.addAttribute("categoryDetailEntity",categoryDetailRepository.findAllByCategoryDetailId(categoryDetailId));
        return "product_detail";
    }


    @RequestMapping(value = "/shop/search", method = GET)
    public String search(@RequestParam("searchInput")String searchInput, Model model) {
        List<ProductImageEntity> resultList;
        if (searchInput.isEmpty()) {
            resultList = productImageRepository.getProductListWithImage();
        } else {
            resultList = productImageRepository.getProductBySearch(searchInput, searchInput);
        }
        List<CategoryEntity> categoryEntityList = (List<CategoryEntity>) categoryRepository.findAll();

        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("searchInput",searchInput);
        model.addAttribute("productList", resultList);
        return "product";
    }


    @PostMapping(value = "/add2cart", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    // return 1 -> there is a new item has been added to cart
    // return 0 -> item already existing in the cart, just update its quantity
    public String add2cart(@RequestBody String body, HttpSession session) {
        try {
            String[] data = body.split(",,");
            // data submitted from frontend must be qualified
            if (data.length >= 7) {
                int productId = Integer.parseInt(data[0]);
                String imgSource = data[1];
                String title = data[2];
                String price = data[3];
                String size = data[4];
                String color = data[5];
                int quantity = Integer.parseInt(data[6]);
                int proDetailId = productDetailRepository.findProductDetailId(Integer.parseInt(data[0]),data[5],data[4]);
                String returnedValue;// 0 or 1

                // cart is empty
                if (session.getAttribute("shopping_cart") == null) {
                    List<CartItem> cart = new ArrayList<CartItem>();
                    cart.add(new CartItem(productId, quantity, imgSource, title, price, size, color, proDetailId));
                    session.setAttribute("shopping_cart", cart);
                    returnedValue = "1";
                } else { // cart has items
                    List<CartItem> cart = (List<CartItem>) session.getAttribute("shopping_cart");
                    int index = checkExistingInCart(proDetailId, cart);
                    // the product ID doesn't existing in the cart yet
                    if (index == -1) {
                        cart.add(new CartItem(productId, quantity, imgSource, title, price, size, color, proDetailId));
                        returnedValue = "1";
                    } else {
                        // the product ID is existing in the cart yet
                        // then update the quantity by the new one
                        int newQuantity = cart.get(index).getQuantity() + quantity;
                        cart.get(index).setQuantity(newQuantity);
                        cart.get(index).updateTotalPrice();
                        returnedValue = "0";
                    }
                    session.setAttribute("shopping_cart", cart);
                }
                // save comment to DB
                return returnedValue;
            } else {
                return "ERROR||ERROR";
            }
        } catch (Exception e) {
            return "ERROR||ERROR";
        }
    }

    private int checkExistingInCart(int productDetailId, List<CartItem> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProductDetailId() == productDetailId) {
                return i;
            }
        }
        return -1;
    }

    private double calculateTotalPrice(List<CartItem> cart) {
        Double totalPrice = 0.0;
        for (CartItem item: cart) {
            totalPrice += item.getTotalPriceInNumber();
        }
        return totalPrice;
    }

    @GetMapping(value = "/cart")
    public String showCart(Model model, HttpSession session){
        List<CartItem> cart = new ArrayList<CartItem>();
        if (session.getAttribute("shopping_cart") != null) {
            cart = (List<CartItem>) session.getAttribute("shopping_cart");
        }
        model.addAttribute("shopping_cart_list", cart);
        model.addAttribute("cart_size", cart.size());
        model.addAttribute("total_price_in_cart", calculateTotalPrice(cart));
        return "shopping_cart";
    }

    @RequestMapping(value = "/delete/{productDetailId}", method = GET)
    public String deleteItemInCart(@PathVariable int productDetailId, HttpSession session, Model model) {

        List<CartItem> cart = (List<CartItem>) session.getAttribute("shopping_cart");
        CartItem delItem = null;
        for (CartItem t: cart){
            if(t.getProductDetailId()==productDetailId){
                delItem = t;
                break;
            }
        }
        cart.remove(delItem);
        session.setAttribute("shopping_cart", cart);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/checkout",method = RequestMethod.GET)
    public String viewCheckoutForm(Model model, @RequestParam(value = "data", required = false) String data,
                                   HttpSession session) {
        // data: 1-2--4-1
        // product detail id = 1, quantity = 2
        // product detail id = 4, quantity = 19999

        if (!data.isEmpty()) {
            String[] tmpData = data.split("__");
            List<CartItem> cart;
            if (session.getAttribute("shopping_cart") != null) {
                cart = (List<CartItem>) session.getAttribute("shopping_cart");
                for(CartItem item : cart) {
                    for (int i = 0; i < tmpData.length; i++) {
                        if (item.getProductDetailId() == Integer.valueOf(tmpData[i].split("_")[0])) {
                            // update the product quantity and then save to http session shopping_cart
                            item.setQuantity(Integer.valueOf(tmpData[i].split("_")[1]));
                            item.updateTotalPrice();
                        }
                    }
                }
                session.setAttribute("shopping_cart", cart);
                session.setAttribute("total_price_in_cart", calculateTotalPrice(cart));
            }
        }
        List<ProvinceEntity> provinceEntityList = provinceRepository.getProvinceOrderByName();

        Map<Integer, String> provinceMap = new LinkedHashMap<>();
        for(ProvinceEntity provinceEntity : provinceEntityList) {
            provinceMap.put(provinceEntity.getId(), provinceEntity.getFullNameEn());
        }
        model.addAttribute("order", new OrderEntity());
        model.addAttribute("province",provinceMap);
        return "checkout";
    }

    @RequestMapping(value = "/checkout", method = POST, produces = "text/plain;charset=UTF-8") //produces:data type will return
    public String saveOrder(OrderEntity order, HttpSession session, Model model) {
        order.setRequireDate(Date.valueOf(LocalDate.now()));
        orderRepository.save(order);
        List<CartItem> cart = (List<CartItem>) session.getAttribute("shopping_cart");
        List<OrderDetailEntity> orderDetailList = new ArrayList<>();
        for (CartItem item: cart) {
            OrderDetailEntity orderDetail = new OrderDetailEntity();
            orderDetail.setSize(item.getSize());
            orderDetail.setColor(item.getColor());
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setPrice(Double.parseDouble(item.getPrice()));
            ProductEntity product = new ProductEntity();
            product.setId(item.getProductId());
            orderDetail.setProduct(product);
            orderDetail.setOrderEntity(order);
            orderDetailList.add(orderDetail);

            productDetailRepository.decreaseProductQuantity(item.getQuantity(),item.getProductId(),item.getColor(),item.getSize());
        }
        orderDetailRepository.saveAll(orderDetailList);
        session.removeAttribute("shopping_cart");
        sendConfirmationEmail(order);
        return "thankyou";
    }


    private void sendConfirmationEmail(OrderEntity order)  {
        String subject = "Thanks for your order";
        String mailBody = "<h3> Dear " + order.getFirstName()+" "+order.getLastName() + ",<h3>"
                + "<p>Thank you for your order! Your product will be shipped soon!</p>"
                + "<p>Here is your order number: "+order.getId()+"</p>"
                + "<p>If you have any questions or concerns about your order, feel free to reach out to our Customer Service anytime 9AM-5PM, Monday-Friday. Be sure to have the order number handy so we can help you even faster!</p>"
                + "<p>We look forward to your feedback on your purchase! Thank you again!</p>"
                + "<p>Kind regards,</p>"
                + "<p>T&T Fashion</p>";

        try {
            GmailSender.send(order.getEmail(), subject, mailBody, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }


    @GetMapping("/getDistricts")
    public @ResponseBody String getDistricts(@RequestParam Integer provinceId)
    {
        String json = null;
        List<Object[]> list = provinceRepository.getDistrictByProvince(provinceId);
        try {
            json = new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @GetMapping("/getWards")
    public @ResponseBody String getWards(@RequestParam Integer districtId)
    {
        String json = null;
        List<Object[]> list = districtRepository.getWardByDistrict(districtId);
        try {
            json = new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }


    @RequestMapping(value = "/contact",method = GET)
    public String viewContact(Model model) {

        return "contact";
    }


    @RequestMapping(value = "/blog",method = GET)
    public String viewBlog(Model model) {

        return "blog";
    }


    @RequestMapping(value = "/about",method = GET)
    public String viewAbout(Model model) {

        return "about";
    }

}
