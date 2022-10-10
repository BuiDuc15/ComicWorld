package com.comicworld.layer.domain.dao.comment;

import com.comicworld.layer.domain.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Long>, CustomCommentDAO {

    @Query("SELECT c FROM comments c WHERE c.id = :commentId")
    public Optional<Comment> findByIdWithAllRelationshipsLoadedLazily(@Param("commentId") Long commentId);

    @Query("SELECT c FROM comments c LEFT JOIN FETCH c.replies WHERE c.id = :commentId")
    public Optional<Comment> findByIdWithRepliesLoadedEagerly(@Param("commentId") Long commentId);

}
