package com.capstone.productcatalogservice.controller;

import com.capstone.productcatalogservice.dto.ProductRequest;
import com.capstone.productcatalogservice.dto.ProductResponse;
import com.capstone.productcatalogservice.entity.Product;
import com.capstone.productcatalogservice.enumeration.Category;
import com.capstone.productcatalogservice.service.ProductService;
import org.elasticsearch.client.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return this.productService.getAllProducts().stream().map(ProductResponse::new).toList();
    }

    @GetMapping("{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable Long productId) {
        return new ProductResponse(this.productService.getProductById(productId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) throws URISyntaxException, IOException, InterruptedException {
        return new ProductResponse(this.productService.createProduct(productRequest));
    }

    @PutMapping("{productId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProduct(@RequestBody ProductRequest productRequest, @PathVariable Long productId) {
        this.productService.updateProduct(productRequest, productId);
    }

    @DeleteMapping("{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long productId) {
        this.productService.deleteProduct(productId);
    }

    @GetMapping("/add-index")
    public JSONObject addIndex() throws URISyntaxException, IOException, InterruptedException {
        Response stringHttpResponse = this.productService.addIndex();
        JSONObject jsonHttpResponse = new JSONObject();
        jsonHttpResponse.put("res", jsonHttpResponse.toString());
        return jsonHttpResponse;
    }

    @GetMapping("/search/{category}")
    public List<Product> search(@PathVariable String category) throws IOException, URISyntaxException, InterruptedException, ParseException {
        return this.productService.search(Category.valueOf(category.toUpperCase()));
    }
}
