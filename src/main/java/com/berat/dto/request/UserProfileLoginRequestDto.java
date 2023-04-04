package com.berat.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileLoginRequestDto {
    @NotNull(message = "Username cannot be null!")
    private String username;
    @NotNull(message = "Password cannot be null!")
    private String password;
}
