package com.berat.converter;

import com.berat.dto.request.CommentCreateRequestDto;
import com.berat.dto.response.CommentResponseDto;
import com.berat.dto.response.UserResponseDto;
import com.berat.model.Comment;
import com.berat.model.Post;
import com.berat.model.UserProfile;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CommentConverter {

    public static Comment toComment(String content, UserProfile userProfile, Post post){
        return Comment.builder().content(content).userProfile(userProfile).post(post).build();
    }
    public static CommentResponseDto fromComment(Comment comment, UserResponseDto userProfile){
        return CommentResponseDto.builder().id(comment.getId()).content(comment.getContent())
                .userResponseDto(userProfile).createDate(new Date(comment.getCreateDate())).build();
    }
}
