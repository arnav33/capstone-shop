package com.capstone.productcatalogservice.repository;

import com.capstone.productcatalogservice.entity.Product;
import com.capstone.productcatalogservice.enumeration.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMysqlProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);
}
