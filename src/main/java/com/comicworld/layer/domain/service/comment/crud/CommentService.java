package com.comicworld.layer.domain.service.comment.crud;

import com.comicworld.layer.domain.entity.comment.Comment;
import com.comicworld.layer.domain.model.PageModel;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CommentService {

    public Comment saveOrUpdate(Comment comment);

    public List<Comment> saveOrUpdateAll(Collection<Comment> comments);

    public PageModel findByComicIdAndPage(Long comicId, Integer page);

    public PageModel findByChapterIdAndPage(Long chapterId, Integer page);

    public void updateUpvoteById(Long commentId, String action);

    public void updateDislikeById(Long commentId, String action);

    public Optional<Comment> findByIdWithAllRelationshipsLoadedLazily(Long commentId);

    public Optional<Comment> findByIdWithRepliesLoadedEagerly(Long commentId);

    public void deleteById(Long commentId);

}
