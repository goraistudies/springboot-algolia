package com.ucgorai.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchRequest {
    private String searchTerm;
    private boolean facetSearch;
    private String facetValue;
    private boolean paginationEnabled;
    private int pageSize;
    private int pageNumber;
    private String sortBy;

}
