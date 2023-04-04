package com.berat.dto.response;

import com.berat.model.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDto {
    private String nickname;
    private String headerPhoto;
    private String photo;
    private String about;
    private Location location;
    private String website;
    private Integer follow;
    private Integer followers;
}
