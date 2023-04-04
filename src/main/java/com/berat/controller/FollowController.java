package com.berat.controller;
import com.berat.dto.request.FollowCreateRequestDto;
import com.berat.dto.request.FollowDeleteRequestDto;
import com.berat.exception.ErrorType;
import com.berat.exception.PigeonManagerException;
import com.berat.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.berat.constant.EndPoint.*;
@RestController
@RequestMapping(FOLLOW)
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping(CREATE)
    public ResponseEntity<Void> createFollow(@RequestBody FollowCreateRequestDto dto){
        if (dto.getFollowingId().equals(dto.getFollowerId()))
            throw new PigeonManagerException(ErrorType.INVALID_PARAMETER);
        followService.createFollow(dto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(DELETE)
    public ResponseEntity<Boolean> deleteFollow(@RequestBody FollowDeleteRequestDto dto){
        return ResponseEntity.ok(followService.deleteFollow(dto));
    }
}
