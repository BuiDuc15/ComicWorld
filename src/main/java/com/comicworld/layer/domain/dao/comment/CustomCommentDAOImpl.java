package com.comicworld.layer.domain.dao.comment;

import com.comicworld.layer.domain.entity.chapter.Chapter;
import com.comicworld.layer.domain.entity.comment.Comment;
import com.comicworld.layer.domain.model.PageModel;
import com.comicworld.utils.Env;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class CustomCommentDAOImpl implements CustomCommentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageModel findByComicIdAndPage(Long comicId, Integer page) {
        Integer offset = page * Env.DEFAULT_COMMENT_PAGE_SIZE;

        Integer limit = Env.DEFAULT_COMMENT_PAGE_SIZE;

        TypedQuery<Comment> fetchCommentPageOfComicQuery = this.entityManager.createQuery("SELECT c FROM comments c" +
                                        " LEFT JOIN FETCH c.replies JOIN c.comic cs WHERE cs.id = :comicId ORDER BY c.createdAt DESC",
                                        Comment.class);

        fetchCommentPageOfComicQuery.setParameter("comicId", comicId);
        fetchCommentPageOfComicQuery.setFirstResult(offset);
        fetchCommentPageOfComicQuery.setMaxResults(limit);

        List<Comment> content = fetchCommentPageOfComicQuery.getResultList();

        TypedQuery<Long> countTotalCommentsOfComicQuery = this.entityManager.createQuery("SELECT COUNT(c.id) FROM comments c" +
                                                " JOIN c.comic cs WHERE cs.id = :comicId",
                                                Long.class);

        countTotalCommentsOfComicQuery.setParameter("comicId", comicId);

        Long totalCommentsOfComic = countTotalCommentsOfComicQuery.getSingleResult();

        Long totalPages = totalCommentsOfComic % Env.DEFAULT_COMMENT_PAGE_SIZE == 0 ?
                            totalCommentsOfComic / Env.DEFAULT_COMMENT_PAGE_SIZE :
                            totalCommentsOfComic / Env.DEFAULT_COMMENT_PAGE_SIZE + 1;

        return new PageModel(page, totalPages, content, totalCommentsOfComic);
    }

    @Override
    public PageModel findByChapterIdAndPage(Long chapterId, Integer page) {
        Integer offset = page * Env.DEFAULT_COMMENT_PAGE_SIZE;

        Integer limit = Env.DEFAULT_COMMENT_PAGE_SIZE;

        TypedQuery<Comment> fetchCommentPageOfChapterQuery = this.entityManager.createQuery("SELECT c FROM comments c" +
                        " LEFT JOIN FETCH c.replies JOIN c.chapter cs WHERE cs.id = :chapterId ORDER BY c.createdAt DESC",
                Comment.class);

        fetchCommentPageOfChapterQuery.setParameter("chapterId", chapterId);
        fetchCommentPageOfChapterQuery.setFirstResult(offset);
        fetchCommentPageOfChapterQuery.setMaxResults(limit);

        List<Comment> content = fetchCommentPageOfChapterQuery.getResultList();

        TypedQuery<Long> countTotalCommentsOfChapterQuery = this.entityManager.createQuery("SELECT COUNT(c.id) FROM comments c" +
                        " JOIN c.chapter cs WHERE cs.id = :chapterId",
                Long.class);

        countTotalCommentsOfChapterQuery.setParameter("chapterId", chapterId);

        Long totalCommentsOfChapter = countTotalCommentsOfChapterQuery.getSingleResult();

        Long totalPages = totalCommentsOfChapter % Env.DEFAULT_COMMENT_PAGE_SIZE == 0 ?
                totalCommentsOfChapter / Env.DEFAULT_COMMENT_PAGE_SIZE :
                totalCommentsOfChapter / Env.DEFAULT_COMMENT_PAGE_SIZE + 1;

        return new PageModel(page, totalPages, content, totalCommentsOfChapter);
    }

    @Override
    public void updateUpvoteById(Long commentId, String action) {
        TypedQuery<Comment> typedQuery = this.entityManager.createQuery("SELECT c FROM comments c WHERE c.id = :commentId", Comment.class);

        typedQuery.setParameter("commentId", commentId);
        typedQuery.setLockMode(LockModeType.PESSIMISTIC_WRITE);

        try {
            Comment comment = typedQuery.getSingleResult();

            if(action.equalsIgnoreCase("ADD")) comment.setUpvote(comment.getUpvote() + 1);
            else comment.setUpvote(comment.getUpvote() - 1);
        }
        catch (NoResultException e) {
            throw new IllegalArgumentException("Comment with ID " + commentId + " does not exist.");
        }
    }

    @Override
    public void updateDislikeById(Long commentId, String action) {
        TypedQuery<Comment> typedQuery = this.entityManager.createQuery("SELECT c FROM comments c WHERE c.id = :commentId", Comment.class);

        typedQuery.setParameter("commentId", commentId);
        typedQuery.setLockMode(LockModeType.PESSIMISTIC_WRITE);

        try {
            Comment comment = typedQuery.getSingleResult();

            if(action.equalsIgnoreCase("ADD")) comment.setDislike(comment.getDislike() + 1);
            else comment.setDislike(comment.getDislike() - 1);
        }
        catch (NoResultException e) {
            throw new IllegalArgumentException("Comment with ID " + commentId + " does not exist.");
        }
    }

}




























