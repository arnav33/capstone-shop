package com.capstone.productcatalogservice.service;

import com.capstone.productcatalogservice.dto.ProductRequest;
import com.capstone.productcatalogservice.entity.Product;
import com.capstone.productcatalogservice.enumeration.Category;
import com.capstone.productcatalogservice.wrapper.IProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.elasticsearch.client.Response;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    IProductRepository iProductRepository;

    @Autowired
    ProductService(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }

    public List<Product> getAllProducts() {
        return this.iProductRepository.findAll();
    }

    public List<Product> getProducts() {
        return null;
    }

    public Product getProductById(Long productId) {
        Optional<Product> op = this.iProductRepository.findById(productId);
        return op.orElseThrow(() -> new EntityNotFoundException("Product with the id " + productId + " not found in the system"));
    }

    public Product createProduct(ProductRequest productRequest) throws URISyntaxException, IOException, InterruptedException {
        Product product = new Product(productRequest);
        product.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        product.getProductAttributes().forEach(productAttribute -> productAttribute.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE));
        product.getProductReviews().forEach(productReview -> productReview.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE));
        product.getProductTags().forEach(productTag -> productTag.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE));
        this.iProductRepository.save(product);
        return product;
    }

    public void updateProduct(ProductRequest productRequest, Long productId) {
        Product product = new Product(productRequest);
        Optional<Product> op = iProductRepository.findById(productId);
        Product savedProduct = op.orElseThrow(() -> new EntityNotFoundException("Product with the id " + productId + " not found in the system"));
        product.setId(savedProduct.getId());
        this.iProductRepository.save(product);
    }

    public Response addIndex() throws URISyntaxException, IOException, InterruptedException {
        List<Product> products = this.iProductRepository.findAllByCategory(Category.CLOTHES);
        return this.iProductRepository.addIndex(products, Category.CLOTHES);
    }

    public List<Product> search(Category category) throws IOException, URISyntaxException, InterruptedException, ParseException {
        return this.iProductRepository.search(category);
    }

    public void deleteProduct(Long id) {
        this.iProductRepository.deleteById(id);
    }
}
