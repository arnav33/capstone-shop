package com.capstone.productcatalogservice.service;

import com.capstone.productcatalogservice.entity.Product;
import com.capstone.productcatalogservice.enumeration.Category;
import com.capstone.productcatalogservice.wrapper.ProductRepository;
import org.elasticsearch.client.Response;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    ProductRepository productRepository;

    ElasticSearchServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Response addIndex() throws URISyntaxException, IOException, InterruptedException {
        List<Product> products = this.productRepository.findAllByCategory(Category.CLOTHES);
        return this.productRepository.addIndex(products, Category.CLOTHES);
    }

    public List<Product> search(Category category) throws IOException, URISyntaxException, ParseException, InterruptedException {
        return this.productRepository.search(category);
    }
}
