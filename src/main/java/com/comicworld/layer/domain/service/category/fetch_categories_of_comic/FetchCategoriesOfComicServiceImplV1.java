package com.comicworld.layer.domain.service.category.fetch_categories_of_comic;

import com.comicworld.layer.domain.dto.category.CategoryOutDTO;
import com.comicworld.layer.domain.entity.Category;
import com.comicworld.layer.domain.service.author.fetch_authors_of_comic.FetchAuthorsOfComicService;
import com.comicworld.layer.domain.service.category.crud.CategoryService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("fetchCategoriesOfComicServiceImplV1")
public class FetchCategoriesOfComicServiceImplV1 implements FetchCategoriesOfComicService {

    @Autowired
    @Qualifier("categoryServiceImplV1")
    private CategoryService categoryService;

    @Override
    public ResponseEntity<Object> fetch(Long comicId) {
        List<Category> categories = this.categoryService.findByComicId(comicId);

        List<CategoryOutDTO> categoriesOutDTO = categories.stream()
                .map(category -> new CategoryOutDTO(category))
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(
                categoriesOutDTO
        ), HttpStatus.OK);
    }
}
