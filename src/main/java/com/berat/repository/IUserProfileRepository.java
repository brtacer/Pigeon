package com.berat.repository;

import com.berat.model.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<UserProfile> findByUsernameAndPassword(String username, String password);
    @Query(value = "select u from UserProfile u inner join Share s on u.id = s.userProfile.id where s.post.id = ?1")
    Page<UserProfile> getAllSharedByPostId(Long postId, Pageable pageable);
    @Query(value = "select u from UserProfile u inner join Like l on u.id = l.userProfile.id where l.post.id = ?1")
    Page<UserProfile> getAllLikedByPostId(Long postId, Pageable pageable);
    @Query(value = "select u from UserProfile u inner join Like l on u.id = l.userProfile.id where l.comment.id = ?1")
    Page<UserProfile> getAllLikedByCommentId(Long commentId, Pageable pageable);
    @Query(value = "select u from UserProfile u inner join Follow f on u.id = f.followerId where f.followingId = ?1")
    Page<UserProfile> getAllFollowedByUserProfileId(Long userProfileId,Pageable pageable);
    @Query(value = "select u from UserProfile u inner join Follow f on u.id = f.followingId where f.followerId = ?1")
    Page<UserProfile> getAllFollowersByUserProfileId(Long userProfileId,Pageable pageable);


}
