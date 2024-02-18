package com.capstone.productcatalogservice.wrapper;

import co.elastic.clients.util.ContentType;
import com.capstone.productcatalogservice.entity.Product;
import com.capstone.productcatalogservice.enumeration.Category;
import com.capstone.productcatalogservice.repository.IMysqlProductRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.json.simple.JSONObject;


@Service
public class ProductRepository implements IProductRepository {

    private final IMysqlProductRepository iMysqlProductRepository;

    @Autowired
    ProductRepository(IMysqlProductRepository iMysqlProductRepository) {
        this.iMysqlProductRepository = iMysqlProductRepository;
    }

    public List<Product> findAll() {
        return this.iMysqlProductRepository.findAll();
    }

    public List<Product> findAllByCategory(Category category) {
        return this.iMysqlProductRepository.findAllByCategory(category);
    }

    public Optional<Product> findById(Long productId) {
        return this.iMysqlProductRepository.findById(productId);
    }

    public void save(Product product) {
        this.iMysqlProductRepository.save(product);
    }

    public void deleteById(Long productId) {
        this.iMysqlProductRepository.deleteById(productId);
    }

    public Response addIndex(List<Product> products, Category category) throws URISyntaxException, IOException, InterruptedException {
        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "elastic"));

        RestClientBuilder builder = RestClient.builder(
                        new HttpHost("localhost", 9200))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(
                            HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder
                                .setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

        RestClient restClient = builder.build();

        JSONObject body = new JSONObject();
        body.put("products", new GsonBuilder().create().toJsonTree(products).getAsJsonArray());
        var bodyParser = HttpRequest.BodyPublishers.ofString(body.toString());
        Request request = new Request("POST", "/products/_doc/clothes");
        request.addParameter("pretty", "true");
        request.setJsonEntity(body.toJSONString());
        return restClient.performRequest(request);
    }

    public JSONArray search(Category category) throws IOException, InterruptedException, URISyntaxException, ParseException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("http://localhost:9200/products/_doc/" + category.toString().toLowerCase() + "?pretty"))
                .header("Authorization", "Basic ZWxhc3RpYzplbGFzdGlj")
                .GET()
                .build();
        String productString = HttpClient
                .newBuilder()
                .authenticator(getAuthenticator())
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString()).body();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(productString);
        JSONObject source = (JSONObject) json.get("_source");
        return (JSONArray) source.get("products");
    }

    private Authenticator getAuthenticator() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "elastic",
                        "elastic".toCharArray());
            }

        };
    }
}
