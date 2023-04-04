package com.berat.repository;

import com.berat.model.Comment;
import com.berat.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment,Long> {
    @Query(value = "select * from tbl_comment where post_id = ?1",nativeQuery = true)
    Page<Comment> findAllByPostId(Long userProfileId, Pageable pageable);
}
