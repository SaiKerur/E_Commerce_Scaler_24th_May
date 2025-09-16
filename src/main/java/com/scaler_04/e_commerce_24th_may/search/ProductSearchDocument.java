package com.scaler_04.e_commerce_24th_may.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "products")
public class ProductSearchDocument {
    @Id
    private String id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryTitle;
}


