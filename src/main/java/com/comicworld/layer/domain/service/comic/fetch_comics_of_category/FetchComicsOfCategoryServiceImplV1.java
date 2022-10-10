package com.comicworld.layer.domain.service.comic.fetch_comics_of_category;

import com.comicworld.layer.domain.dto.chapter.ChapterOutDTO;
import com.comicworld.layer.domain.dto.comic.ComicOutDTO;
import com.comicworld.layer.domain.entity.Comic;
import com.comicworld.layer.domain.entity.chapter.Chapter;
import com.comicworld.layer.domain.model.PageModel;
import com.comicworld.layer.domain.service.comic.crud.ComicService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("fetchComicsOfCategoryServiceImplV1")
public class FetchComicsOfCategoryServiceImplV1 implements FetchComicsOfCategoryService {

    @Autowired
    @Qualifier("comicServiceImplV1")
    private ComicService comicService;

    @Override
    public ResponseEntity<Object> fetch(String fakeId, Map<String, String[]> params) {
        Map<String, Object> queryParams = new HashMap<>();

        queryParams.put("fakeId", fakeId);

        if(params.containsKey("page")) {
            try {
                Integer page = Integer.valueOf(params.get("page")[0]);
                queryParams.put("page", page);
            }
            catch (Exception e) {
                e.printStackTrace();
                queryParams.put("page", 0);
            }
        }

        if(params.containsKey("view")) {
            String view = (String) params.get("view")[0];

            if(view.equalsIgnoreCase("ASC") || view.equalsIgnoreCase("DESC"))
                queryParams.put("view", view);
        }

        if(params.containsKey("name")) {
            String name = (String) params.get("name")[0];

            if(name.equalsIgnoreCase("ASC") || name.equalsIgnoreCase("DESC"))
                queryParams.put("name", name);
        }

        if(params.containsKey("lastUpdate")) {
            String lastUpdate = (String) params.get("lastUpdate")[0];

            if(lastUpdate.equalsIgnoreCase("ASC") || lastUpdate.equalsIgnoreCase("DESC"))
                queryParams.put("lastUpdate", lastUpdate);
        }

        PageModel pageModel = this.comicService.findByCategoryFakeIdWithChaptersLoadedEagerly(queryParams);

        List<Comic> comics = (List<Comic>) pageModel.getContent();

        List<ComicOutDTO> comicsOutDTO = new ArrayList<>();

        comics.forEach(comic -> {
            Chapter latestChapter = comic.getChapters().iterator().next();
            latestChapter.setComic(null);
            comic.setChapters(null);
            ComicOutDTO comicOutDTO = new ComicOutDTO(comic);
            comicOutDTO.setLatestChapter(new ChapterOutDTO(latestChapter));
            comicsOutDTO.add(comicOutDTO);
        });

        pageModel.setContent(comicsOutDTO);

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(pageModel), HttpStatus.OK);
    }
}






































