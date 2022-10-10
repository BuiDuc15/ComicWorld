package com.comicworld.layer.domain.service.comment.crud;

import com.comicworld.layer.domain.dao.comment.CommentDAO;
import com.comicworld.layer.domain.entity.comment.Comment;
import com.comicworld.layer.domain.model.PageModel;
import com.comicworld.layer.domain.service.comment.crud.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("commentServiceImplV1")
@Transactional
public class CommentServiceImplV1 implements CommentService {

    @Autowired
    private CommentDAO dao;

    @Override
    public Comment saveOrUpdate(Comment comment) {
        return this.dao.save(comment);
    }

    @Override
    public List<Comment> saveOrUpdateAll(Collection<Comment> comments) {
        return this.dao.saveAll(comments);
    }

    @Override
    public PageModel findByComicIdAndPage(Long comicId, Integer page) {
        return this.dao.findByComicIdAndPage(comicId, page);
    }

    @Override
    public PageModel findByChapterIdAndPage(Long chapterId, Integer page) {
        return this.dao.findByChapterIdAndPage(chapterId, page);
    }

    @Override
    public void updateUpvoteById(Long commentId, String action) {
        this.dao.updateUpvoteById(commentId, action);
    }

    @Override
    public void updateDislikeById(Long commentId, String action) {
        this.dao.updateDislikeById(commentId, action);
    }

    @Override
    public Optional<Comment> findByIdWithAllRelationshipsLoadedLazily(Long commentId) {
        return this.dao.findByIdWithAllRelationshipsLoadedLazily(commentId);
    }

    @Override
    public Optional<Comment> findByIdWithRepliesLoadedEagerly(Long commentId) {
        return this.dao.findByIdWithRepliesLoadedEagerly(commentId);
    }

    @Override
    public void deleteById(Long commentId) {
        this.dao.deleteById(commentId);
    }
}
