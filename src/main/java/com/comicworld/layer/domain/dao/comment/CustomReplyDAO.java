package com.comicworld.layer.domain.dao.comment;

public interface CustomReplyDAO {

    public void updateUpvoteById(Long replyId, String action);

    public void updateDislikeById(Long replyId, String action);

}
