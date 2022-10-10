package com.comicworld.layer.domain.service.category.search;

import com.comicworld.layer.domain.dto.category.CategoryOutDTO;
import com.comicworld.layer.domain.entity.Category;
import com.comicworld.layer.domain.service.category.crud.CategoryService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("categorySearchServiceImplV1")
public class CategorySearchServiceImplV1 implements CategorySearchService {

    @Autowired
    @Qualifier("categoryServiceImplV1")
    private CategoryService categoryService;

    @Override
    public ResponseEntity<Object> search(String keyword) {
        String temp = "";

        if(keyword.length() > 0) {
            keyword = keyword.trim();
            String[] keywords = keyword.split("  *");
            int length = keywords.length - 1;
            for(int i = 0; i < length; ++i) {
                temp += "+" + keywords[i] + " ";
            }
            temp += keywords[keywords.length - 1] + "*";
        }

        keyword = temp;

        List<Category> categories = this.categoryService.searchByKeyword(keyword, Env.CATEGORY_SEARCH_LIMIT);

        List<CategoryOutDTO> categoriesOutDTO = categories.stream()
                                                    .map(category -> new CategoryOutDTO(category))
                                                    .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(
                categoriesOutDTO
        ), HttpStatus.OK);
    }
}
