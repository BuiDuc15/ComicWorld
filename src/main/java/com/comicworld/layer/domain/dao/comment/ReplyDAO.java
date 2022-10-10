package com.comicworld.layer.domain.dao.comment;

import com.comicworld.layer.domain.entity.comment.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReplyDAO extends JpaRepository<Reply, Long>, CustomReplyDAO {

    @Query("SELECT r FROM replies r WHERE r.id = :replyId")
    public Optional<Reply> findByIdWithAllRelationshipsLoadedLazily(@Param("replyId") Long replyId);

}
