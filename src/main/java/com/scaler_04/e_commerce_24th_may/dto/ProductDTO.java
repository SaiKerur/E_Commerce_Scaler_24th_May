package com.scaler_04.e_commerce_24th_may.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String categoryName; // assuming you want to return the category name

    @Override
    public String toString() {
        return "ProductDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}

