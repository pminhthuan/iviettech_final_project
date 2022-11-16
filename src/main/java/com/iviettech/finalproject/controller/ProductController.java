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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

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
    CategoryDetailRepository categoryDetailRepository;


    @RequestMapping(method = GET)
    public String viewHome(Model model) {
        List<ProductImageEntity> productEntityList = productService.getProductListWithImage();
        List<ProductImageEntity> productFeaturedList = productService.getProductListFeatured();
        List<ProductImageEntity> productBestSellerList = productService.getProductListBestSeller();
        List<ProductImageEntity> productLastedUpdateList = productService.getProductListLastedUpdate();
        List<ProductImageEntity> productHighRateList = productService.getProductListHighRating();
        List<CategoryEntity> categoryEntityList = productService.getCategoryList();
        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("productList", productEntityList);
        model.addAttribute("productFeaturedList", productFeaturedList);
        model.addAttribute("productBestSellerList", productBestSellerList);
        model.addAttribute("productLastedUpdateList", productLastedUpdateList);
        model.addAttribute("productHighRateList", productHighRateList);
        return "index";
    }

    @GetMapping("/shop")
    public String paginate(Model model, @RequestParam(value = "p", required = false, defaultValue = "0") Integer p){
        Pageable pageable = PageRequest.of(p, 12);
        Page<ProductImageEntity> productEntityList =  productService.getProductListWithImagePage(pageable);
        List<CategoryEntity> categoryEntityList = productService.getCategoryList();
        //System.out.println(p.get());

        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("productListP", productEntityList);
        model.addAttribute("activeLink", "how-active1");
        model.addAttribute("tagPages", p);
        model.addAttribute("url", "/shop/?");
        return "product";
    }


    @RequestMapping(value = "/shop/category/{id}",method = GET)
    public String showProductByCategory(@PathVariable("id") int id, Model model,
                                        @RequestParam(value = "p", required = false, defaultValue = "0") Integer p) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ProductImageEntity> productEntityList = productService.getProductListWithImageByCategory(id,pageable);
        List<CategoryEntity> categoryEntityList = productService.getCategoryList();
        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("productListP", productEntityList);
        model.addAttribute("tag", id);
        model.addAttribute("tagPages", p);
        model.addAttribute("url", "/shop/category/"+id+"/?");

        return "product";
    }

    @RequestMapping(value = "/shop/category/categorydetail/{id}",method = GET)
    public String showProductByCategoryDetail(@PathVariable("id") int id, Model model,
                                              @RequestParam(value = "p", required = false, defaultValue = "0") Integer p) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ProductImageEntity> productEntityList = productService.getProductListWithImageByCategoryDetail(id, pageable);
        List<CategoryEntity> categoryEntityList = productService.getCategoryList();

        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("productListP", productEntityList);
        model.addAttribute("tag", productService.getCategoryIdByCateDetail(id));
        model.addAttribute("tagPages", p);
        model.addAttribute("url", "/shop/category/categorydetail/"+id+"/?");

        return "product";
    }


    @RequestMapping(value = "/shop/view/{id}",method = GET)
    public String showProductDetail(@PathVariable("id") int id, Model model) {
        List<ProductImageEntity> productImageEntityList = productService.findByProduct_Id(id);
        List<String> productColorList = productService.getColorByProductId(id);
        List<String> productSizeList = productService.getSizeByProductId(id);
        List<ProductDetailEntity> productDetailEntityList = productService.findProductDetailEntityByProductId(id);
        int categoryDetailId = productService.getCategoryDetailIdByProductId(id);

        List<ProductImageEntity> relatedProductList = productService.getRelatedProductByCategoryDetail(categoryDetailId, id); //related product
        model.addAttribute("relatedProductList", relatedProductList); //related product
        model.addAttribute("productImageEntityList", productImageEntityList);
        model.addAttribute("productEntity", productRepository.findById(id));
        model.addAttribute("productColorList", productColorList);
        model.addAttribute("productSizeList", productSizeList);
        model.addAttribute("productDetailEntityList",productDetailEntityList);
        model.addAttribute("categoryDetailEntity",categoryDetailRepository.findAllByCategoryDetailId(categoryDetailId));
        model.addAttribute("rating", new RatingEntity());
        return "product_detail";
    }


    @RequestMapping(value = "/shop/search", method = GET)
    public String search(@RequestParam("searchInput")String searchInput, Model model,
                         @RequestParam(value = "p", required = false, defaultValue = "0") Integer p) {

        Pageable pageable = PageRequest.of(p, 12);
        Page<ProductImageEntity> resultList = productService.search(searchInput, searchInput, pageable);
        String msg = productService.checkSearchResult(searchInput, searchInput, pageable);
        List<CategoryEntity> categoryEntityList = productService.getCategoryList();

        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("searchInput",searchInput);
        model.addAttribute("productListP", resultList);
        model.addAttribute("msg", msg);
        model.addAttribute("tagPages", p);
        model.addAttribute("url", "/shop/search?searchInput="+searchInput+"&");
        return "product";
    }


    @RequestMapping(value = "/shop/filter/bestseller",method = GET)
    public String showProductBestSeller(Model model, @RequestParam(value = "p", required = false, defaultValue = "0") Integer p) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ProductImageEntity> productEntityList = productService.getProductListBestSeller(pageable);
        List<CategoryEntity> categoryEntityList = productService.getCategoryList();
        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("productListP", productEntityList);
        model.addAttribute("tagPages", p);
        model.addAttribute("url", "/shop/filter/bestseller/?");

        return "product";
    }

    @RequestMapping(value = "/shop/filter/rating",method = GET)
    public String showProductHighRating(Model model, @RequestParam(value = "p", required = false, defaultValue = "0") Integer p) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ProductImageEntity> productEntityList = productService.getProductListHighRating(pageable);
        List<CategoryEntity> categoryEntityList = productService.getCategoryList();
        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("productListP", productEntityList);
        model.addAttribute("tagPages", p);
        model.addAttribute("url", "/shop/filter/rating/?");

        return "product";
    }

    @RequestMapping(value = "/shop/filter/newness",method = GET)
    public String showProductNewness(Model model, @RequestParam(value = "p", required = false, defaultValue = "0") Integer p) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ProductImageEntity> productEntityList = productService.getProductListNewness(pageable);
        List<CategoryEntity> categoryEntityList = productService.getCategoryList();
        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("productListP", productEntityList);
        model.addAttribute("tagPages", p);
        model.addAttribute("url", "/shop/filter/newness/?");

        return "product";
    }

    @RequestMapping(value = "/shop/filter/asc",method = GET)
    public String showProductLowToHighPrice(Model model, @RequestParam(value = "p", required = false, defaultValue = "0") Integer p) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ProductImageEntity> productEntityList = productService.getProductListLowToHighPrice(pageable);
        List<CategoryEntity> categoryEntityList = productService.getCategoryList();
        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("productListP", productEntityList);
        model.addAttribute("tagPages", p);
        model.addAttribute("url", "/shop/filter/asc/?");

        return "product";
    }


    @RequestMapping(value = "/shop/filter/desc",method = GET)
    public String showProductHighToLowPrice(Model model, @RequestParam(value = "p", required = false, defaultValue = "0") Integer p) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ProductImageEntity> productEntityList = productService.getProductListHighToLowPrice(pageable);
        List<CategoryEntity> categoryEntityList = productService.getCategoryList();
        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("productListP", productEntityList);
        model.addAttribute("tagPages", p);
        model.addAttribute("url", "/shop/filter/desc/?");

        return "product";
    }


    @RequestMapping(value = "/shop/filter/{fromPrice}/{toPrice}", method = GET)
    public String showProductByPrice(@PathVariable("fromPrice")double fromPrice,@PathVariable("toPrice")double toPrice, Model model,
                                     @RequestParam(value = "p", required = false, defaultValue = "0") Integer p) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ProductImageEntity> productEntityList = productService.getProductListByPriceBetween(fromPrice, toPrice, pageable);
        List<CategoryEntity> categoryEntityList = productService.getCategoryList();

        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("productListP", productEntityList);
        model.addAttribute("tagPages", p);
        model.addAttribute("url", "/shop/filter/"+fromPrice+"/"+toPrice+"/?");
        return "product";
    }

    @RequestMapping(value = "/shop/filter/{color}", method = GET)
    public String showProductByColor(@PathVariable("color")String color, Model model,
                                     @RequestParam(value = "p", required = false, defaultValue = "0") Integer p) {
        Pageable pageable = PageRequest.of(p, 12);
        Page<ProductImageEntity> productEntityList = productService.getProductListByColor(color, pageable);
        List<CategoryEntity> categoryEntityList = productService.getCategoryList();

        model.addAttribute("categories", categoryEntityList);
        model.addAttribute("productListP", productEntityList);
        model.addAttribute("tagPages", p);
        model.addAttribute("url", "/shop/filter/"+color+"/?");

        return "product";
    }


    @PostMapping(value = "/add2cart", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String add2cart(@RequestBody String body, HttpSession session){
        String bodyResponse = productService.doAdd2cart(body, session);
        return bodyResponse;
    }

    @GetMapping(value = "/cart")
    public String showCart(Model model, HttpSession session){
        List<CartItem> cart = productService.viewCart(session);
        model.addAttribute("shopping_cart_list", cart);
        model.addAttribute("cart_size", cart.size());
        model.addAttribute("total_price_in_cart", productService.calculateTotalPrice(cart));
        return "shopping_cart";
    }

    @RequestMapping(value = "/delete/{productDetailId}", method = GET)
    public String deleteItemInCart(@PathVariable int productDetailId, HttpSession session) {
        List<CartItem> cart = productService.deleteItemCart(productDetailId, session);
        session.setAttribute("shopping_cart", cart);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/checkout",method = RequestMethod.GET)
    public String viewCheckoutForm(Model model, @RequestParam(value = "data", required = false) String data, HttpSession session) {
        if (productService.checkQuantityBeforeCheckout(data, session) == 0){
            productService.updateCartBeforeCheckout(data, session);
            Map<Integer, String> provinceMap = productService.getProvinces();
            model.addAttribute("order", new OrderEntity());
            model.addAttribute("province",provinceMap);
            return "checkout";
        } else {
            List<CartItem> cart = productService.viewCart(session);
            model.addAttribute("shopping_cart_list", cart);
            model.addAttribute("msg", "Sorry, we do not have enough quantity in stock!");
            model.addAttribute("total_price_in_cart", productService.calculateTotalPrice(cart));
            return "shopping_cart";
        }
    }

    @RequestMapping(value = "/checkout", method = POST, produces = "text/plain;charset=UTF-8") //produces:data type will return
    public String saveOrder(OrderEntity order, HttpSession session, HttpServletResponse response,
                            @RequestParam(value = "saveInfo", required = false) String saveInfo) {
        //productService.saveInfo(saveInfo, response, order);
        productService.doCheckout(order, session);
        return "thankyou";
    }


    @GetMapping("/getDistricts")
    public @ResponseBody String getDistricts(@RequestParam Integer provinceId) {
        return productService.getDistricts(provinceId);
    }

    @GetMapping("/getWards")
    public @ResponseBody String getWards(@RequestParam Integer districtId) {
        return productService.getWards(districtId);
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

    @RequestMapping(value = "/404",method = GET)
    public String view404Page(Model model) {

        return "404";
    }

    @RequestMapping(value = "/shop/wishlist/{id}",method = GET)
    public String addWishList(@PathVariable("id") int id, Model model, HttpSession session) {
        if (session.getAttribute("user") == null){
            if (session.getAttribute("wish_list") == null) {
                List<FavouriteEntity> wishList = new ArrayList();
                wishList.add(new FavouriteEntity(id));
            } else {
                List<FavouriteEntity> wishList = (List<FavouriteEntity>) session.getAttribute("wish_list");
            }
        }

        return "product_detail";
    }

}
