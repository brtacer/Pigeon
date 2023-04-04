package com.berat.controller;
import com.berat.dto.request.PostCreateRequestDto;
import com.berat.dto.request.PostUpdateRequestDto;
import com.berat.dto.response.PostResponseDto;
import com.berat.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.berat.constant.EndPoint.*;
@RestController
@RequestMapping(POST)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(CREATE)
    public ResponseEntity<Void> createPost(@RequestBody @Valid PostCreateRequestDto dto){
        postService.createPost(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping(GETALL)
    public ResponseEntity<Slice<PostResponseDto>> getAllPosts(@RequestParam Optional<Long> userProfileId){
        return ResponseEntity.ok(postService.getAllPosts(userProfileId));
    }
    @GetMapping(GETONE+BYPOSTID)
    public ResponseEntity<PostResponseDto> getOnePostByPostId(@PathVariable Long postId){
        return ResponseEntity.ok(postService.getOnePostByPostId(postId));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<Void> updatePost(@RequestBody @Valid PostUpdateRequestDto dto){
        postService.updatePost(dto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(DELETE+BYPOSTID)
    public ResponseEntity<Boolean> deleteByPostId(@PathVariable Long postId){
        return ResponseEntity.ok(postService.deleteByPostId(postId));
    }
}
