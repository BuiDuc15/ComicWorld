package com.comicworld.layer.domain.service.comic.create_comic_service;

import com.comicworld.layer.domain.dto.comic.ComicInDTO;
import com.comicworld.layer.domain.entity.AlternativeName;
import com.comicworld.layer.domain.entity.Author;
import com.comicworld.layer.domain.entity.Category;
import com.comicworld.layer.domain.entity.Comic;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroup;
import com.comicworld.layer.domain.service.alternative_name.crud.AlternativeNameService;
import com.comicworld.layer.domain.service.author.crud.AuthorService;
import com.comicworld.layer.domain.service.category.crud.CategoryService;
import com.comicworld.layer.domain.service.comic.crud.ComicService;
import com.comicworld.layer.domain.service.translator_group.crud.TranslatorGroupService;
import com.comicworld.utils.StringUtils;
import com.comicworld.utils.TimeUtils;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("createComicServiceImplV1")
@Transactional
public class CreateComicServiceImplV1 implements CreateComicService {

    @Autowired
    @Qualifier("comicServiceImplV1")
    private ComicService comicService;

    @Autowired
    @Qualifier("alternativeNameServiceImplV1")
    private AlternativeNameService alternativeNameService;

    @Autowired
    @Qualifier("authorServiceImplV1")
    private AuthorService authorService;

    @Autowired
    @Qualifier("categoryServiceImplV1")
    private CategoryService categoryService;

    @Autowired
    @Qualifier("translatorGroupServiceImplV1")
    private TranslatorGroupService translatorGroupService;

    @Override
    public ResponseEntity<Object> create(ComicInDTO comicInDTO) {

        Comic comic = comicInDTO.toComic();

        comic.setFakeId(StringUtils.purify(comic.getDisplayedName()));

        String cleanedDisplayedName = comic.getDisplayedName().trim().replaceAll("  *", " ");

        comic.setDisplayedName(cleanedDisplayedName);

        comic.setCreatedAt(TimeUtils.getCurrentUTCTimeInMilliseconds());
        comic.setLastUpdatedAt(TimeUtils.getCurrentUTCTimeInMilliseconds());
        comic.setView(0l);
        comic.setLove(0l);
        comic.setDislike(0l);

        comic = this.comicService.saveOrUpdate(comic);

        List<AlternativeName> alternativeNames = this.alternativeNameService.findByIdIn(comicInDTO.getAlternativeNameIds());

        List<Author> authors = this.authorService.findByIdIn(comicInDTO.getAuthorIds());

        List<Category> categories = this.categoryService.findByIdIn(comicInDTO.getCategoryIds());

        List<TranslatorGroup> translatorGroups = this.translatorGroupService.findByIdIn(comicInDTO.getTranslatorGroupIds());

        for(AlternativeName alternativeName : alternativeNames) {
            alternativeName.setComic(comic);
        }

        for(Author author : authors) {
            author.getComics().add(comic);
        }

        comic.setAuthors(Sets.newLinkedHashSet(authors));

        for(Category category : categories) {
            category.getComics().add(comic);
        }

        comic.setCategories(Sets.newLinkedHashSet(categories));

        for(TranslatorGroup translatorGroup : translatorGroups) {
            translatorGroup.getComics().add(comic);
        }

        comic.setTranslatorGroups(Sets.newLinkedHashSet(translatorGroups));

        this.alternativeNameService.saveOrUpdateAll(alternativeNames);

        this.authorService.saveOrUpdateAll(authors);

        this.categoryService.saveOrUpdateAll(categories);

        this.translatorGroupService.saveOrUpdateAll(translatorGroups);

        comic = this.comicService.saveOrUpdate(comic);

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", comic.getId().toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.createdResponseBody(), headers, HttpStatus.CREATED);
    }

}































