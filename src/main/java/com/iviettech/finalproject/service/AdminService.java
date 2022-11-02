package com.iviettech.finalproject.service;

import com.iviettech.finalproject.entity.ProductEntity;
import com.iviettech.finalproject.entity.ProductImageEntity;
import com.iviettech.finalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public void updateProductStatus(int id) {
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
//        model.addAttribute("product", productEntity);
    }

    public void uploadFile(MultipartFile file,int id) {
        if( null != file && !file.isEmpty()){
            try {
                byte[] bytes = file.getBytes();
                String path = "D:\\final\\iviettech_final_project\\src\\main\\webapp\\resources\\images\\products\\";
                String name = file.getOriginalFilename();
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

//                String name = String.valueOf(new Date().getTime()+".jpg");

                // Create the file on server
                String fileSource = "/resources/images/product/" + name;
//                File serverFile = new File(fileSource);
//                System.out.println("=====Path image to server: " + serverFile.getPath());
//                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//                stream.write(bytes);
//                stream.close();
                ProductImageEntity imageEntity = new ProductImageEntity();
                Optional<ProductEntity> productEntity = productRepository.findById(id);
                imageEntity.setProduct(productEntity.get());
                imageEntity.setImageUrl(fileSource);
                imageEntity.setImageAlt(name);
                productImageRepository.save(imageEntity);


            } catch (IOException e) {
                System.out.println("====== Error upload file:" + e.getMessage());
            }

        } else {
            System.out.println("====== File not exists");
        }

    }

}
