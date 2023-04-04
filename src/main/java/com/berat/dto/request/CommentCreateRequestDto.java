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
public class CommentCreateRequestDto {
    @NotBlank(message = "Content cannot be blank!")
    private String content;
    private Long userProfileId;
    private Long postId;
}
