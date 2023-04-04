package com.berat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequestDto {
    private Long id;
    private String headerPhoto;
    private String photo;
    private String about;
    private String website;

}
