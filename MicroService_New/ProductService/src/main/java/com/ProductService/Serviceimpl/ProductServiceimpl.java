package com.ProductService.Serviceimpl;

import com.ProductService.Model.Product_model;
import com.ProductService.Repository.ProductRepo;
import com.ProductService.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceimpl implements ProductService {

    @Autowired
    ProductRepo productrepo;

    @Override
    public Product_model saveproduct(Product_model product) {
        return productrepo.save(product);
    }

    @Override
    public Product_model getproduct(Integer id) {
        Product_model res =  productrepo.findById(id).orElseThrow(()->new RuntimeException("Product id not found"));
        return res;
    }

    @Override
    public List<Product_model> getproducts(List<Integer> data) {
        ArrayList<Product_model> al = new ArrayList();
        for(Integer i:data)
        {

            Product_model obj= productrepo.findById(i).get();

            al.add(obj);
        }
        return al;
    }
}
