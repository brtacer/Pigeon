package com.berat.converter;

import com.berat.dto.request.FollowCreateRequestDto;
import com.berat.model.Follow;
import org.springframework.stereotype.Component;

@Component
public class FollowConverter {

    public static Follow toFollow(FollowCreateRequestDto dto){
        return Follow.builder()
                .followingId(dto.getFollowingId())
                .followerId(dto.getFollowerId())
                .build();
    }
}
