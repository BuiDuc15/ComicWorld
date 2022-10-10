package com.comicworld.layer.domain.service.reply.crud;

import com.comicworld.layer.domain.entity.comment.Reply;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReplyService {

    public Reply saveOrUpdate(Reply reply);

    public List<Reply> saveOrUpdateAll(Collection<Reply> replies);

    public void deleteAll(Collection<Reply> replies);

    public void updateUpvoteById(Long replyId, String action);

    public void updateDislikeById(Long replyId, String action);

    public void deleteById(Long replyId);

    public Optional<Reply> findByIdWithAllRelationshipsLoadedLazily(Long replyId);

}
