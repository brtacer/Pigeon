package com.berat.converter;

import com.berat.dto.request.CommentLikeCreateRequestDto;
import com.berat.dto.request.PostLikeCreateRequestDto;
import com.berat.model.Comment;
import com.berat.model.Like;
import com.berat.model.Post;
import com.berat.model.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class LikeConverter {

    public static Like toLike(PostLikeCreateRequestDto dto, UserProfile userProfile, Post post){
        return Like.builder()
                .userProfile(userProfile)
                .post(post)
                .build();
    }public static Like toLike(CommentLikeCreateRequestDto dto, UserProfile userProfile, Comment comment){
        return Like.builder()
                .userProfile(userProfile)
                .comment(comment)
                .build();
    }
}
