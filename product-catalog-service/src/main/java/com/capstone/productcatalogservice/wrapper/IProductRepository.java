package com.capstone.productcatalogservice.wrapper;

import com.capstone.productcatalogservice.entity.Product;
import com.capstone.productcatalogservice.enumeration.Category;
import org.elasticsearch.client.Response;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;


@Service
public interface IProductRepository {

    public List<Product> findAll();

    public List<Product> findAllByCategory(Category category);

    public Optional<Product> findById(Long productId);

    public void save(Product product);

    public void deleteById(Long productId);

    public Response addIndex(List<Product> products, Category category) throws URISyntaxException, IOException, InterruptedException;

    public List<Product> search(Category category) throws IOException, InterruptedException, URISyntaxException, ParseException;
}
