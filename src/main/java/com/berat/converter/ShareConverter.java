package com.berat.converter;

import com.berat.dto.request.ShareCreateRequestDto;
import com.berat.model.Post;
import com.berat.model.Share;
import com.berat.model.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class ShareConverter {

    public static Share toShare(ShareCreateRequestDto dto, UserProfile userProfile, Post post){
        return Share.builder().userProfile(userProfile).post(post).build();
    }
}
