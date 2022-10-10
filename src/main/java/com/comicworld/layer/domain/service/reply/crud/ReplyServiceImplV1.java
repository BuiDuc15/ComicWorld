package com.comicworld.layer.domain.service.reply.crud;

import com.comicworld.layer.domain.dao.comment.ReplyDAO;
import com.comicworld.layer.domain.entity.comment.Reply;
import com.comicworld.layer.domain.service.reply.crud.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("replyServiceImplV1")
@Transactional
public class ReplyServiceImplV1 implements ReplyService {

    @Autowired
    private ReplyDAO dao;

    @Override
    public Reply saveOrUpdate(Reply reply) {
        return this.dao.save(reply);
    }

    @Override
    public List<Reply> saveOrUpdateAll(Collection<Reply> replies) {
        return this.dao.saveAll(replies);
    }

    @Override
    public void deleteAll(Collection<Reply> replies) {
        this.dao.deleteAll(replies);
    }

    @Override
    public void updateUpvoteById(Long replyId, String action) {
        this.dao.updateUpvoteById(replyId, action);
    }

    @Override
    public void updateDislikeById(Long replyId, String action) {
        this.dao.updateDislikeById(replyId, action);
    }

    @Override
    public void deleteById(Long replyId) {
        this.dao.deleteById(replyId);
    }

    @Override
    public Optional<Reply> findByIdWithAllRelationshipsLoadedLazily(Long replyId) {
        return this.dao.findByIdWithAllRelationshipsLoadedLazily(replyId);
    }
}
