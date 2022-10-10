package com.comicworld.layer.domain.service.comic.update_comic_service;

import com.comicworld.layer.domain.dto.comic.ComicInDTO;
import com.comicworld.layer.domain.entity.AlternativeName;
import com.comicworld.layer.domain.entity.Author;
import com.comicworld.layer.domain.entity.Category;
import com.comicworld.layer.domain.entity.Comic;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroup;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.alternative_name.crud.AlternativeNameService;
import com.comicworld.layer.domain.service.author.crud.AuthorService;
import com.comicworld.layer.domain.service.category.crud.CategoryService;
import com.comicworld.layer.domain.service.comic.crud.ComicService;
import com.comicworld.layer.domain.service.translator_group.crud.TranslatorGroupService;
import com.comicworld.utils.Env;
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

import java.util.*;
import java.util.stream.Collectors;

@Service("updateComicServiceImplV1")
@Transactional
public class UpdateComicServiceImplV1 implements UpdateComicService {

    @Autowired
    @Qualifier("comicServiceImplV1")
    private ComicService comicService;

    @Autowired
    @Qualifier("authorServiceImplV1")
    private AuthorService authorService;

    @Autowired
    @Qualifier("alternativeNameServiceImplV1")
    private AlternativeNameService alternativeNameService;

    @Autowired
    @Qualifier("categoryServiceImplV1")
    private CategoryService categoryService;

    @Autowired
    @Qualifier("translatorGroupServiceImplV1")
    private TranslatorGroupService translatorGroupService;

    private Map<Long, Long> temp = new HashMap<>();

    @Override
    public ResponseEntity<Object> update(ComicInDTO comicInDTO) {

        Optional<Comic> findingComicRs = this.comicService.findByIdWithChaptersLoadedLazily(comicInDTO.getId());

        if(findingComicRs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Comic with ID " + comicInDTO.getId() + " does not exist.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        Comic comic = findingComicRs.get();

        comic.setFakeId(StringUtils.purify(comicInDTO.getDisplayedName()));

        String cleanedDisplayedName = comicInDTO.getDisplayedName().trim().replaceAll("  *", " ");

        comic.setDisplayedName(cleanedDisplayedName);

        comic.setLastUpdatedAt(TimeUtils.getCurrentUTCTimeInMilliseconds());

        comic.setCoverLink(comicInDTO.getCoverLink());

        List<Author> newAuthors = this.authorService.findByIdIn(comicInDTO.getAuthorIds());

        List<Author> removedAuthors = new ArrayList<>();

        Long comicId = comic.getId();

        comic.getAuthors()
                .forEach(oldAuthor -> {
                    boolean isContained = comicInDTO.getAuthorIds().contains(oldAuthor.getId());
                    if(!isContained) {
                        oldAuthor.setComics(
                                oldAuthor.getComics()
                                        .stream()
                                        .filter(temp -> temp.getId() != comicId)
                                        .collect(Collectors.toSet())
                        );
                        removedAuthors.add(oldAuthor);
                    }
                });

        for(Author newAuthor : newAuthors) {
            if(!newAuthor.getComics().contains(comic))
                newAuthor.getComics().add(comic);
        }

        comic.setAuthors(Sets.newHashSet(newAuthors));

        this.authorService.saveOrUpdateAll(newAuthors);

        this.authorService.saveOrUpdateAll(removedAuthors);

        List<Category> newCategories = this.categoryService.findByIdIn(comicInDTO.getCategoryIds());

        List<Category> removedCategories = new ArrayList<>();

        comic.getCategories()
                .forEach(oldCategory -> {
                    boolean isContained = comicInDTO.getCategoryIds().contains(oldCategory.getId());
                    if(!isContained) {
                        oldCategory.setComics(
                                oldCategory.getComics()
                                        .stream()
                                        .filter(temp -> temp.getId() != comicId)
                                        .collect(Collectors.toSet())
                        );
                        removedCategories.add(oldCategory);
                    }
                });

        for(Category newCategory : newCategories) {
            if(!newCategory.getComics().contains(comic))
                newCategory.getComics().add(comic);
        }

        comic.setCategories(Sets.newHashSet(newCategories));

        this.categoryService.saveOrUpdateAll(newCategories);

        this.categoryService.saveOrUpdateAll(removedCategories);

        List<TranslatorGroup> newTranslatorGroups = this.translatorGroupService.findByIdIn(comicInDTO.getTranslatorGroupIds());

        List<TranslatorGroup> removedTranslatorGroups = new ArrayList<>();

        comic.getTranslatorGroups()
                .forEach(oldTranslatorGroup -> {
                    boolean isContained = comicInDTO.getTranslatorGroupIds().contains(oldTranslatorGroup.getId());
                    if(!isContained) {
                        oldTranslatorGroup.setComics(
                                oldTranslatorGroup.getComics()
                                        .stream()
                                        .filter(temp -> temp.getId() != comicId)
                                        .collect(Collectors.toSet())
                        );
                        removedTranslatorGroups.add(oldTranslatorGroup);
                    }
                });

        for(TranslatorGroup newTranslatorGroup : newTranslatorGroups) {
            if(!newTranslatorGroup.getComics().contains(comic))
                newTranslatorGroup.getComics().add(comic);
        }

        comic.setTranslatorGroups(Sets.newHashSet(newTranslatorGroups));

        this.translatorGroupService.saveOrUpdateAll(newTranslatorGroups);

        this.translatorGroupService.saveOrUpdateAll(removedTranslatorGroups);

        List<AlternativeName> newAlternativeNames = this.alternativeNameService.findByIdIn(comicInDTO.getAlternativeNameIds());

        List<AlternativeName> removedAlternativeNames = new ArrayList<>();

        if(!temp.isEmpty()) temp.clear();

        for(AlternativeName newAlternativeName : newAlternativeNames) {
            temp.put(newAlternativeName.getId(), newAlternativeName.getId());
            if(newAlternativeName.getComic() == null)
                newAlternativeName.setComic(comic);
        }

        for(AlternativeName oldAlternativeName : comic.getAlternativeNames()) {
            if(!temp.containsKey(oldAlternativeName.getId())) removedAlternativeNames.add(oldAlternativeName);
        }

        this.alternativeNameService.saveOrUpdateAll(newAlternativeNames);
        this.alternativeNameService.deleteAll(removedAlternativeNames);

        comic = this.comicService.saveOrUpdate(comic);

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", comic.getId().toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(), headers, HttpStatus.OK);
    }

}




































