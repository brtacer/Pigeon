package com.berat.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationCreateRequestDto {
    @NotBlank(message = "Country cannot be blank!")
    private String country;
    @NotBlank(message = "City cannot be blank!")
    private String city;
}
