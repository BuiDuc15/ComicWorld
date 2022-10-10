package com.comicworld.layer.application.controller.client;

import com.comicworld.layer.domain.service.category.fetch_all_categories.FetchAllCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientCategoryControllerV1 {

    @Autowired
    @Qualifier("fetchAllCategoriesServiceImplV1")
    private FetchAllCategoriesService fetchAllCategoriesService;

    @GetMapping(path = "/v1/categories")
    public ResponseEntity<Object> fetchAll() {
        return this.fetchAllCategoriesService.fetch();
    }

}
