package com.iviettech.finalproject.service;

import com.iviettech.finalproject.entity.ProductEntity;
import com.iviettech.finalproject.entity.ProductImageEntity;
import com.iviettech.finalproject.repository.ProductImageRepository;
import com.iviettech.finalproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImageRepository productImageRepository;

//    public List<ProductImageEntity> productWithImgeList(){
//        List<ProductEntity> producList =  productImageRepository.getProductListWithImage();
//
//        return productList;
//    }


}
