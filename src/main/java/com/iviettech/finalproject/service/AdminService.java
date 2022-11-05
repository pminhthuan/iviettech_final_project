package com.iviettech.finalproject.service;

import com.iviettech.finalproject.entity.ProductEntity;
import com.iviettech.finalproject.entity.ProductImageEntity;
import com.iviettech.finalproject.helper.CSVHelper;
import com.iviettech.finalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    @Autowired
    ServletContext servletContext;


    public void uploadFile(MultipartFile file,int id) {
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


    public void saveProduct(MultipartFile file) {
        try {
            List<ProductEntity> productEntityList = CSVHelper.readProductData(file.getInputStream());

            productRepository.saveAll(productEntityList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
