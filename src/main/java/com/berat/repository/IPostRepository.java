package com.berat.repository;

import com.berat.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {
    @Query(value = "select * from tbl_post where user_profile_id = ?1",nativeQuery = true)
    Slice<Post> findAllByUserProfileId(Long userProfileId, Pageable pageable);

    /**
     *  Written to avoid getting PropertyReferenceException !
     *  ( No property 'tbl' found for type 'Post') -> tbl_post.
     */
    @Query(value = "select * from tbl_post",nativeQuery = true)
    Slice<Post> getAllPosts(Pageable pageable);
}
