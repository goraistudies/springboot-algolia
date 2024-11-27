package com.ucgorai.service;

import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.algolia.search.models.synonyms.Synonym;
import com.ucgorai.model.Product;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;

public class AlgoliaSynonymConfiguration {

        private  SearchIndex<Product> index;
        @Value("${algolia.application-id}")
        String applicationId;
        @Value("${algolia.read-api-key}")
        String readApiKey;
        @Value("${algolia.write-api-key}")
        String writeApiKey;
        @Value("${algolia.index-name}")
        String indexName;

        public void synonymConfiguration() {

            try (SearchClient client = DefaultSearchClient.create(applicationId, writeApiKey)) {

                SearchIndex<?> index = client.initIndex(indexName);
                Synonym synonym = Synonym.createSynonym("multi-way-synonym-1", Arrays.asList("car", "automobile", "vehicle"));
                // Save synonym to the index
                this.index.saveSynonym(synonym);


            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
}
