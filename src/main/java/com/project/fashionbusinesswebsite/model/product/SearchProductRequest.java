package com.project.fashionbusinesswebsite.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchProductRequest {
    private int page = 0;
    private int size = 10;
    private String sort;
    private String key;
    private String keySearch;
}
