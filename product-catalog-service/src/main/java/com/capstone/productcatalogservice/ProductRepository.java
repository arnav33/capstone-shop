package com.capstone.productcatalogservice;

import com.capstone.productcatalogservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class ProductRepository implements JpaRepository<Product, Long> {


}
