package com.saras.etl.controller;

import com.saras.etl.entity.Product;
import com.saras.etl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class FileUploadController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("etl/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "redirect:/?message=No file selected";
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            List<Product> products = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    Product product = new Product();
                    product.setName(data[1]);
                    product.setPrice(Double.parseDouble(data[2]));
                    // Add more fields if needed
                    products.add(product);
                }
            }
            System.out.println("products");
            productRepository.saveAll(products);
            return "redirect:/?message=File uploaded successfully";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/?message=Error uploading file: " + e.getMessage();
        }
    }

    @GetMapping("etl/total")
    public @ResponseBody double getTotalPrice(){
        AtomicReference<Double> total = new AtomicReference<>( 0.0d);
        try{
            List<Product> products = productRepository.findAll();
            products.forEach(product -> total.updateAndGet(v ->  v + product.getPrice()));
            double totalValue = products.stream().map(r -> r.getPrice()).mapToDouble(Double::doubleValue).sum();
            System.out.println(totalValue);
            return total.get();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return total.get();
        }

    }
}

