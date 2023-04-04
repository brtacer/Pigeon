package com.berat.converter;

import com.berat.dto.request.UserProfileRegisterRequestDto;
import com.berat.dto.response.AuthResponseDto;
import com.berat.dto.response.ProfileResponseDto;
import com.berat.dto.response.UserResponseDto;
import com.berat.model.Location;
import com.berat.model.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileConverter {

    public static UserProfile toUserProfile(UserProfileRegisterRequestDto dto){
        Location location = LocationConverter.toLocation(dto.getLocationDto());
        UserProfile userProfile = UserProfile.builder().nickname(dto.getNickname())
                .username(dto.getUsername()).email(dto.getEmail())
                .password(dto.getPassword()).build();
        location.setUserProfile(userProfile);
        userProfile.setLocation(location);
        return userProfile;
    }
    public static UserResponseDto toUserProfileResponseDto(UserProfile userProfile){
        return UserResponseDto.builder()
                .id(userProfile.getId())
                .nickname(userProfile.getNickname())
                .photo(userProfile.getPhoto())
                .about(userProfile.getAbout())
                .build();
    }
    public static ProfileResponseDto toProfileResponseDto(UserProfile userProfile,Integer follow,Integer follower){
        return ProfileResponseDto.builder()
                .nickname(userProfile.getNickname())
                .photo(userProfile.getPhoto())
                .headerPhoto(userProfile.getHeaderPhoto())
                .about(userProfile.getAbout())
                .location(userProfile.getLocation())
                .website(userProfile.getWebsite())
                .build();
    }
    public static AuthResponseDto toAuthResponseDto(Long id,String token){
        return AuthResponseDto.builder().id(id).token(token).build();
    }
}
