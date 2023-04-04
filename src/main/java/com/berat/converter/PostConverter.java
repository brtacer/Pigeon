package com.berat.converter;

import com.berat.dto.request.PostCreateRequestDto;
import com.berat.dto.response.PostResponseDto;
import com.berat.dto.response.UserResponseDto;
import com.berat.model.Post;
import com.berat.model.UserProfile;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PostConverter {

    public static Post toPost(PostCreateRequestDto dto, UserProfile userProfile){
        return Post.builder().content(dto.getContent()).userProfile(userProfile).build();
    }
    public static PostResponseDto postResponseDto(Post post, UserResponseDto userResponseDto){
        return PostResponseDto.builder().postId(post.getId()).content(post.getContent())
                .userResponseDto(userResponseDto).createDate(new Date(post.getCreateDate())).build();
    }
}
