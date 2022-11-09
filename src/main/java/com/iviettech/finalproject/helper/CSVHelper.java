package com.iviettech.finalproject.helper;

import com.iviettech.finalproject.entity.CategoryDetailEntity;
import com.iviettech.finalproject.entity.ManufactorEntity;
import com.iviettech.finalproject.entity.ProductEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] PRODUCTS = { "ID", "Name", "Category_Detail_ID", "Orginal_Price", "Actual_Price", "Manufactor_ID", "Add_Date", "Status", "Description", "Infor" };
    static String[] PRODUCTDETAILS = { "ID", "Name", "Category_Detail_ID", "Orginal_Price", "Actual_Price", "Manufactor_ID", "Add_Date", "Status", "Description", "Infor" };

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<ProductEntity> readProductData(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<ProductEntity> productEntityList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                ProductEntity product = new ProductEntity();

//                product.setId(Integer.parseInt(csvRecord.get(PRODUCTS[0])));
                product.setName(csvRecord.get(PRODUCTS[1]));

                CategoryDetailEntity categoryDetail = new CategoryDetailEntity();
                categoryDetail.setId(Integer.parseInt(csvRecord.get(PRODUCTS[2])));
                product.setCategoryDetail(categoryDetail);

                product.setOriginal_price(Double.parseDouble(csvRecord.get(PRODUCTS[3])));
                product.setActual_price(Double.parseDouble(csvRecord.get(PRODUCTS[4])));

                ManufactorEntity manufactor = new ManufactorEntity();
                manufactor.setId(Integer.parseInt(csvRecord.get(PRODUCTS[5])));
                product.setManufactor(manufactor);

//                product.setAddDate(new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord.get(PRODUCTS[6])));
                product.setStatus(Integer.parseInt(csvRecord.get(PRODUCTS[7])));
                product.setDescription(csvRecord.get(PRODUCTS[8]));
                product.setAdditionInfo(csvRecord.get(PRODUCTS[9]));

                productEntityList.add(product);


            }

            return productEntityList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
