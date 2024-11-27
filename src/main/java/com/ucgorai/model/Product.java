package com.ucgorai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @JsonProperty("objectID") // Algolia's unique identifier
    private String objectID;
    private String name;
    private String category;
    private String subcategory;
    private String description;

}
