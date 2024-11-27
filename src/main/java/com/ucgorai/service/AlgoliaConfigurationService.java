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
public class AlgoliaConfigurationService {

    private  SearchIndex<Product> index;
    @Value("${algolia.application-id}")
    String applicationId;
    @Value("${algolia.read-api-key}")
    String readApiKey;
    @Value("${algolia.write-api-key}")
    String writeApiKey;
    @Value("${algolia.index-name}")
    String indexName;

    public void configureCustomRanking() {
        // Define settings for custom ranking
        IndexSettings settings = new IndexSettings()
                .setSearchableAttributes(List.of("name"))
                .setCustomRanking(List.of("asc(name)"));

        // Apply the settings to the index
        index.setSettings(settings);
    }
}
