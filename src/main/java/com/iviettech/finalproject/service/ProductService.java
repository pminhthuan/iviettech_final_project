package com.iviettech.finalproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iviettech.finalproject.entity.*;
import com.iviettech.finalproject.helper.GmailSender;
import com.iviettech.finalproject.pojo.CartItem;
import com.iviettech.finalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class ProductService {
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

    public List<ProductImageEntity> getProductListWithImage(){
        return productImageRepository.getProductListWithImage();
    }

    public List<CategoryEntity> getCategoryList(){
        List<CategoryEntity> categoryEntityList = (List<CategoryEntity>) categoryRepository.findAll();
        return categoryEntityList;
    }

    public Page<ProductImageEntity> getProductListWithImagePage(Pageable pageable){
        return productImageRepository.getProductListWithImagePageable(pageable);
    }

    public Page<ProductImageEntity> getProductListWithImageByCategory(int id, Pageable pageable){
        return productImageRepository.getProductListWithImageByCategory(id,pageable);
    }

    public Page<ProductImageEntity> getProductListWithImageByCategoryDetail(int id, Pageable pageable){
        return productImageRepository.getProductListWithImageByCategoryDetail(id, pageable);
    }

    public int getCategoryIdByCateDetail(int id){
        return categoryDetailRepository.getCategoryIdByCateDetail(id);
    }

    public Page<ProductImageEntity> getProductListBestSeller(Pageable pageable){
        return productImageRepository.getProductListBestSeller(pageable);
    }

    public Page<ProductImageEntity> getProductListHighRating(Pageable pageable){
        return productImageRepository.getProductListHighRating(pageable);
    }

    public Page<ProductImageEntity> getProductListNewness(Pageable pageable){
        return productImageRepository.getProductListNewness(pageable);
    }

    public Page<ProductImageEntity> getProductListLowToHighPrice(Pageable pageable){
        return productImageRepository.getProductListLowToHighPrice(pageable);
    }

    public Page<ProductImageEntity> getProductListHighToLowPrice(Pageable pageable){
        return productImageRepository.getProductListHighToLowPrice(pageable);
    }

    public Page<ProductImageEntity> getProductListByPriceBetween(double fromPrice, double toPrice,Pageable pageable){
        return productImageRepository.getProductListByPriceBetween(fromPrice,toPrice,pageable);
    }

    public Page<ProductImageEntity> getProductListByColor(String color,Pageable pageable){
        return productImageRepository.getProductListByColor(color,pageable);
    }

    public Page<ProductImageEntity> search(String cateName,String productName, Pageable pageable){
        Page<ProductImageEntity> resultList;
        if (cateName.isEmpty()&&productName.isEmpty()) {
            resultList = productImageRepository.getProductListWithImagePageable(pageable);
        } else {
            resultList = productImageRepository.getProductBySearch(cateName, productName, pageable);
        }
        return resultList;
    }

    public String checkSearchResult(String cateName,String productName, Pageable pageable){
        String msg = "";
        if (search(cateName, productName, pageable).isEmpty()) {
            msg = "Search results not available!";
        }
        return msg;
    }

    public List<ProductImageEntity> getProductListFeatured() {
        List<ProductImageEntity> allProducts = productImageRepository.getProductListWithImage();

        Map<Integer, ProductImageEntity> allFeaturedProducts = new HashMap();
        for (ProductImageEntity p : allProducts) {
            if (!allFeaturedProducts.containsKey(p.getProduct().getCategoryDetail().getId())) {
                allFeaturedProducts.put(p.getProduct().getCategoryDetail().getId(), p);
                continue;
            }
        }

        List<ProductImageEntity> allFeatureProductList = new ArrayList<>(allFeaturedProducts.values());
        return allFeatureProductList;
    }

    public List<ProductImageEntity> findByProduct_Id(int id){
        return productImageRepository.findByProduct_Id(id);
    }

    public List<String> getColorByProductId(int id){
        return productDetailRepository.getColorByProductId(id);
    }

    public List<String> getSizeByProductId(int id){
        return productDetailRepository.getSizeByProductId(id);
    }

    public List<ProductDetailEntity> findProductDetailEntityByProductId(int id){
        return productDetailRepository.findProductDetailEntityByProduct_Id(id);
    }

    public int getCategoryDetailIdByProductId(int id){
        return productRepository.getCategoryDetailIdByProductId(id);
    }

    public List<ProductImageEntity> getRelatedProductByCategoryDetail(int categoryDetailId, int id){
        return productImageRepository.getRelatedProductByCategoryDetail(categoryDetailId, id);
    }


    public String doAdd2cart(String body, HttpSession session) {
        // return 1 -> there is a new item has been added to cart
        // return 0 -> item already existing in the cart, just update its quantity
        // return 2 -> quantity not enough
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
                // check quantity in stock
                if (quantity <= productDetailRepository.findQuantity(proDetailId)){
                    // cart is empty
                    if (session.getAttribute("shopping_cart") == null) {
                        List<CartItem> cart = new ArrayList();
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
                    return "2";
                }

            } else {
                return "ERROR||ERROR";
            }
        } catch (Exception e) {
            return "ERROR||ERROR";
        }
    }

    public int checkExistingInCart(int productDetailId, List<CartItem> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProductDetailId() == productDetailId) {
                return i;
            }
        }
        return -1;
    }

    public double calculateTotalPrice(List<CartItem> cart) {
        Double totalPrice = 0.0;
        for (CartItem item: cart) {
            totalPrice += item.getTotalPriceInNumber();
        }
        return totalPrice;
    }

    public List<CartItem> viewCart (HttpSession session){
        List<CartItem> cart = new ArrayList();
        if (session.getAttribute("shopping_cart") != null) {
            cart = (List<CartItem>) session.getAttribute("shopping_cart");
        }
        return cart;
    }

    public List<CartItem> deleteItemCart (int productDetailId, HttpSession session){
        List<CartItem> cart = (List<CartItem>) session.getAttribute("shopping_cart");
        CartItem delItem = null;
        for (CartItem t: cart){
            if(t.getProductDetailId()==productDetailId){
                delItem = t;
                break;
            }
        }
        cart.remove(delItem);
        return cart;
    }


    public int updateCartBeforeCheckout(String data, HttpSession session) {
        // data: 1-2--4-1
        // product detail id = 1, quantity = 2
        // product detail id = 4, quantity = 19999

        if (!data.isEmpty()) {
            String[] tmpData = data.split("__");
            List<CartItem> cart;
            if (session.getAttribute("shopping_cart") != null) {
                cart = (List<CartItem>) session.getAttribute("shopping_cart");
                for (CartItem item : cart) {
                    for (int i = 0; i < tmpData.length; i++) {
                        if (item.getProductDetailId() == Integer.valueOf(tmpData[i].split("_")[0])) {
                            if (Integer.valueOf(tmpData[i].split("_")[1])<=productDetailRepository.findQuantity(item.getProductDetailId())){
                                // update the product quantity and then save to http session shopping_cart
                                item.setQuantity(Integer.valueOf(tmpData[i].split("_")[1]));
                                item.updateTotalPrice();
                                return 0;
                            }
                        }
                    }
                }
                session.setAttribute("shopping_cart", cart);
                session.setAttribute("total_price_in_cart", calculateTotalPrice(cart));
            }
        }
        return 1;
    }

    public void doCheckout(OrderEntity order, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user != null){
            order.setUser(user);
        }
        order.setRequireDate(Date.valueOf(LocalDate.now()));
        orderRepository.save(order);
        List<CartItem> cart = (List<CartItem>) session.getAttribute("shopping_cart");
        List<OrderDetailEntity> orderDetailList = new ArrayList<>();
        for (CartItem item : cart) {
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

            productDetailRepository.decreaseProductQuantity(item.getQuantity(), item.getProductId(), item.getColor(), item.getSize());
        }
        orderDetailRepository.saveAll(orderDetailList);
        session.removeAttribute("shopping_cart");
        sendConfirmationEmail(order);
    }


    private void sendConfirmationEmail(OrderEntity order)  {
        String subject = "Thanks for your order";
        String mailBody = "<h3> Dear " + order.getFirstName()+" "+order.getLastName() + ",<h3>"
                + "<p>Thank you for your order! Your product will be shipped soon!</p>"
                + "<p>Your order refererence number is: "+order.getId()+"</p>"
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


    public Map<Integer, String> getProvinces(){
        List<ProvinceEntity> provinceEntityList = provinceRepository.getProvinceOrderByName();

        Map<Integer, String> provinceMap = new LinkedHashMap<>();

        for(ProvinceEntity provinceEntity : provinceEntityList) {
            provinceMap.put(provinceEntity.getId(), provinceEntity.getFullNameEn());
        }
        return provinceMap;
    }


    public String getDistricts(Integer provinceId) {
        String json = null;
        List<Object[]> list = provinceRepository.getDistrictByProvince(provinceId);
        try {
            json = new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getWards(Integer districtId) {
        String json = null;
        List<Object[]> list = districtRepository.getWardByDistrict(districtId);
        try {
            json = new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }



}
