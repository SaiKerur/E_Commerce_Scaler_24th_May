package com.scaler_04.e_commerce_24th_may.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductSearchRepository extends ElasticsearchRepository<ProductSearchDocument, String> {
    List<ProductSearchDocument> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
}


