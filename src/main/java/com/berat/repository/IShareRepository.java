package com.berat.repository;

import com.berat.model.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IShareRepository extends JpaRepository<Share,Long> {
    @Query(value = "select * from tbl_share where user_profile_id = ?1 and post_id = ?2",nativeQuery = true)
    Optional<Share> findByUserProfileIdAndPostId(Long userProfileId, Long postId);
}
