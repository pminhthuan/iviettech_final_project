package com.iviettech.finalproject.service;

import com.iviettech.finalproject.entity.CategoryEntity;
import com.iviettech.finalproject.entity.ProductDetailEntity;
import com.iviettech.finalproject.entity.ProductEntity;
import com.iviettech.finalproject.entity.ProductImageEntity;
import com.iviettech.finalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



}
