package com.iviettech.finalproject.controller;

import com.iviettech.finalproject.entity.*;
import com.iviettech.finalproject.pojo.CartItem;
import com.iviettech.finalproject.repository.*;
import com.iviettech.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

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
        List<ProductDetailEntity> productDetailEntityList = productDetailRepository.findProductDetailEntityByProduct_Id(id);
        model.addAttribute("productImageEntityList", productImageEntityList);
        model.addAttribute("productEntity", productRepository.findById(id));
        model.addAttribute("productColorList", productColorList);
        model.addAttribute("productSizeList", productSizeList);
        model.addAttribute("productDetailEntityList",productDetailEntityList);


        return "product_detail";
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
                String returnedValue;// 0 or 1

                // cart is empty
                if (session.getAttribute("shopping_cart") == null) {
                    List<CartItem> cart = new ArrayList<CartItem>();
                    cart.add(new CartItem(productId, quantity, imgSource, title, price, size, color));
                    session.setAttribute("shopping_cart", cart);
                    session.setAttribute("total_price_in_cart", calculateTotalPrice(cart));
                    returnedValue = "1";
                } else { // cart has items
                    List<CartItem> cart = (List<CartItem>) session.getAttribute("shopping_cart");
                    int index = checkExistingInCart(productId, cart);
                    // the product ID doesn't existing in the cart yet
                    if (index == -1) {
                        cart.add(new CartItem(productId, quantity, imgSource, title, price, size, color));
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
                    session.setAttribute("total_price_in_cart", calculateTotalPrice(cart));
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

    private int checkExistingInCart(int productId, List<CartItem> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProductId() == productId) {
                return i;
            }
        }
        return -1;
    }

    private String calculateTotalPrice(List<CartItem> cart) {
        Double totalPrice = 0.0;
        for (CartItem item: cart) {
            totalPrice += item.getTotalPriceInNumber();
        }
        return "$" + totalPrice;
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

    @RequestMapping(value = "/checkout",method = GET)
    public String viewCheckoutForm(Model model) {
        model.addAttribute("order", new OrderEntity());

        return "checkout";
    }

    @RequestMapping(value = "/checkout", method = POST, produces = "text/plain;charset=UTF-8") //produces:data type will return
    public String saveOrder(OrderEntity order, HttpSession session, Model model) {
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
        }
        orderDetailRepository.saveAll(orderDetailList);
        session.removeAttribute("shopping_cart");
        return "thankyou";
    }

}
