package com.comicworld.layer.domain.dao.comment;

import com.comicworld.layer.domain.model.PageModel;


public interface CustomCommentDAO {

    public PageModel findByComicIdAndPage(Long comicId, Integer page);

    public PageModel findByChapterIdAndPage(Long chapterId, Integer page);

    public void updateUpvoteById(Long commentId, String action);

    public void updateDislikeById(Long commentId, String action);

}
