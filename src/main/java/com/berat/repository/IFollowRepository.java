package com.berat.repository;

import com.berat.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFollowRepository extends JpaRepository<Follow,Long> {

    Optional<Follow> findByFollowingIdAndAndFollowerId(Long followingId, Long followerId);
}
