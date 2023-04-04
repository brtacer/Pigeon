package com.berat.controller;

import com.berat.dto.request.CommentCreateRequestDto;
import com.berat.dto.request.CommentUpdateRequestDto;
import com.berat.dto.response.CommentResponseDto;
import com.berat.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.berat.constant.EndPoint.*;
@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping(CREATE)
    public ResponseEntity<Void> createComment(@RequestBody @Valid CommentCreateRequestDto dto){
        commentService.createComment(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping(GETALL+BYPOSTID)
    public ResponseEntity<Page<CommentResponseDto>> getAllByPostId(@PathVariable Long postId,
                                                                   @RequestParam Integer currentPage){
        return ResponseEntity.ok(commentService.getAllByPostId(postId,currentPage));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<Void> updateComment(@RequestBody @Valid CommentUpdateRequestDto dto){
        commentService.updateComment(dto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(DELETE+BYCOMMENTID)
    public ResponseEntity<Boolean> deleteByCommentId(@PathVariable Long commentId){
        return ResponseEntity.ok(commentService.deleteByCommentId(commentId));
    }
}
