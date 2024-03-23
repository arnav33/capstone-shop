package com.capstone.productcatalogservice.repository;

import com.capstone.productcatalogservice.elastic_model.EProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticProductRepository extends ElasticsearchRepository<EProduct, Long> {
}
