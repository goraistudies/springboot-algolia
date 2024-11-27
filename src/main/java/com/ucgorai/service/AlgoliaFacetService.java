package com.ucgorai.service;


import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.algolia.search.models.settings.IndexSettings;
import com.ucgorai.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlgoliaFacetService {

    private SearchIndex<Product> index;
    @Value("${algolia.application-id}")
    String applicationId;
    @Value("${algolia.read-api-key}")
    String readApiKey;
    @Value("${algolia.write-api-key}")
    String writeApiKey;
    @Value("${algolia.index-name}")
    String indexName;

    public void configureFacets() {

        SearchClient client = DefaultSearchClient.create(applicationId, writeApiKey);
        this.index = client.initIndex(indexName, Product.class);

        // Define settings for facets
        IndexSettings settings = new IndexSettings()
                .setAttributesForFaceting(List.of("category", "subcategory"));

        // Apply the settings to the index
        index.setSettings(settings);
    }
}

