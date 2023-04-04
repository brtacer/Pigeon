package com.berat.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequestDto {
    private Long id;
    private String password;
    @NotBlank(message = "Password cannot be blank!")
    @Pattern(message = "Password must be at least 8 characters, with at least one capital letter and a special character!",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    private String newPassword;
    private String rePassword;
}
