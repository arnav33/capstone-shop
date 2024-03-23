package com.capstone.productcatalogservice.service;

import com.capstone.productcatalogservice.entity.Product;
import com.capstone.productcatalogservice.enumeration.Category;
import org.elasticsearch.client.Response;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface ElasticSearchService {

    Response addIndex() throws URISyntaxException, IOException, InterruptedException;
    List<Product> search(Category category) throws IOException, URISyntaxException, ParseException, InterruptedException;
}
