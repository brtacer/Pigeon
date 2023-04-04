package com.berat.repository;

import com.berat.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILikeRepository extends JpaRepository<Like,Long> {

    @Query(value = "select * from tbl_like where user_profile_id = ?1 and post_id = ?2",nativeQuery = true)
    Optional<Like> findByUserProfileIdAndPostId(Long userProfileId, Long postId);
    @Query(value = "select * from tbl_like where user_profile_id = ?1 and comment_id = ?2",nativeQuery = true)
    Optional<Like> findByUserProfileIdAndCommentId(Long userProfileId, Long commentId);
}
