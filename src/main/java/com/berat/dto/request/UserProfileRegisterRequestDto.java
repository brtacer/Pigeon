package com.berat.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileRegisterRequestDto {
    @Size(min = 3,max = 20,message = "Nickname must be min 3 characters and max 20 characters!")
    @NotBlank(message = "Country cannot be blank!")
    private String nickname;
    private LocationCreateRequestDto locationDto;
    @Size(min = 3,max = 20,message = "Username must be min 3 characters and max 20 characters!")
    @NotBlank(message = "Country cannot be blank!")
    private String username;
    @Email(message = "Please enter a valid email")
    private String email;
    @NotBlank(message = "Password cannot be blank!")
    @Pattern(message = "Password must be at least 8 characters, with at least one capital letter and a special character!",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    private String password;
    private String rePassword;

}
