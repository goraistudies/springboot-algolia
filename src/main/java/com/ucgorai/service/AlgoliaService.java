package com.ucgorai.service;

import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.algolia.search.models.indexing.Query;
import com.algolia.search.models.indexing.SearchResult;
import com.algolia.search.models.settings.IndexSettings;
import com.ucgorai.model.Product;
import com.ucgorai.model.SearchRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlgoliaService {

    @Value("${algolia.application-id}")
    String applicationId;
    @Value("${algolia.read-api-key}")
    String readApiKey;
    @Value("${algolia.write-api-key}")
    String writeApiKey;
    @Value("${algolia.index-name}")
    String indexName;
    private SearchIndex searchIndex;
    private SearchIndex<Product> createIndex;

    public void saveProduct(Product product) {
        this.createIndex.saveObject(product);
    }

    public void saveProducts(List<Product> products) {
        this.getSearchClient(writeApiKey);
        this.createIndex.saveObjects(products);
    }


    public SearchResult searchData(SearchRequest searchRequest) {
        this.getSearchClient(writeApiKey);


        IndexSettings settings = new IndexSettings()
                .setSearchableAttributes(List.of("name"))
                .setCustomRanking(List.of(searchRequest.getSortBy()));
        this.searchIndex.setSettings(settings);

        Query query = new Query()
                .setQuery(StringUtils.isNotEmpty(searchRequest.getSearchTerm())
                        ? searchRequest.getSearchTerm() : "")
                .setFacets(StringUtils.isNotEmpty(searchRequest.getFacetValue())
                        ? Arrays.stream(searchRequest.getFacetValue().split(","))
                        .map(String::trim)
                        .collect(Collectors.toList()) : new ArrayList<>())
                .setPage(searchRequest.getPageNumber())
                .setHitsPerPage(searchRequest.getPageSize());
                //.setSortFacetValuesBy("alpha");
        return this.searchIndex.search(query);
    }

    private void getSearchClient(String apiKey) {

        SearchClient client = DefaultSearchClient.create(applicationId, apiKey);
        this.searchIndex = client.initIndex(indexName);
        this.createIndex = client.initIndex(indexName, Product.class);

    }

}

