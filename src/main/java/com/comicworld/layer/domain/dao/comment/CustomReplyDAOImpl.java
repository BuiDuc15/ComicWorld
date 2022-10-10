package com.comicworld.layer.domain.dao.comment;

import com.comicworld.layer.domain.entity.comment.Comment;
import com.comicworld.layer.domain.entity.comment.Reply;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Repository
@Transactional
public class CustomReplyDAOImpl implements CustomReplyDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void updateUpvoteById(Long replyId, String action) {
        TypedQuery<Reply> typedQuery = this.entityManager.createQuery("SELECT r FROM replies r WHERE r.id = :replyId", Reply.class);

        typedQuery.setParameter("replyId", replyId);
        typedQuery.setLockMode(LockModeType.PESSIMISTIC_WRITE);

        try {
            Reply reply = typedQuery.getSingleResult();

            if(action.equalsIgnoreCase("ADD")) reply.setUpvote(reply.getUpvote() + 1);
            else reply.setUpvote(reply.getUpvote() - 1);
        }
        catch (NoResultException e) {
            throw new IllegalArgumentException("Reply with ID " + replyId + " does not exist.");
        }
    }

    @Override
    public void updateDislikeById(Long replyId, String action) {
        TypedQuery<Reply> typedQuery = this.entityManager.createQuery("SELECT r FROM replies r WHERE r.id = :replyId", Reply.class);

        typedQuery.setParameter("replyId", replyId);
        typedQuery.setLockMode(LockModeType.PESSIMISTIC_WRITE);

        try {
            Reply reply = typedQuery.getSingleResult();

            if(action.equalsIgnoreCase("ADD")) reply.setDislike(reply.getDislike() + 1);
            else reply.setDislike(reply.getDislike() - 1);
        }
        catch (NoResultException e) {
            throw new IllegalArgumentException("Reply with ID " + replyId + " does not exist.");
        }
    }
}
