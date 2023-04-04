package com.berat.controller;
import com.berat.dto.request.CommentLikeCreateRequestDto;
import com.berat.dto.request.CommentLikeDeleteRequestDto;
import com.berat.dto.request.PostLikeCreateRequestDto;
import com.berat.dto.request.PostLikeDeleteRequestDto;
import com.berat.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.berat.constant.EndPoint.*;
@RestController
@RequestMapping(LIKE)
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping(POST_LIKE)
    public ResponseEntity<Void> createPostLikes(@RequestBody PostLikeCreateRequestDto dto){
        likeService.createPostLikes(dto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(COMMENT_LIKE)
    public ResponseEntity<Void> createCommentLikes(@RequestBody CommentLikeCreateRequestDto dto){
        likeService.createCommentLikes(dto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(DELETE+COMMENT_LIKE)
    public ResponseEntity<Boolean> deleteCommentLikes(@RequestBody CommentLikeDeleteRequestDto dto){
        return ResponseEntity.ok(likeService.deleteCommentLikes(dto));
    }
    @DeleteMapping(DELETE+POST_LIKE)
    public ResponseEntity<Boolean> deletePostLikes(@RequestBody PostLikeDeleteRequestDto dto){
        return ResponseEntity.ok(likeService.deletePostLikes(dto));
    }
}
