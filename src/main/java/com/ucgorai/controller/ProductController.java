package com.ucgorai.controller;

import com.algolia.search.models.indexing.SearchResult;
import com.ucgorai.model.Product;
import com.ucgorai.model.SearchRequest;
import com.ucgorai.service.AlgoliaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private AlgoliaService algoliaService;

    @PostMapping("/index")
    public String indexData(@RequestBody List<Product> products) {
        algoliaService.saveProducts(products);
        return "Products added successfully!";
    }

    @PostMapping("/search")
    public SearchResult searchProducts(@RequestBody SearchRequest searchRequest) {
        return algoliaService.searchData(searchRequest);
    }

}

