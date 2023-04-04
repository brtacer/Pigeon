package com.berat.controller;
import com.berat.dto.request.UpdatePasswordRequestDto;
import com.berat.dto.request.UpdateProfileRequestDto;
import com.berat.dto.request.UserProfileLoginRequestDto;
import com.berat.dto.request.UserProfileRegisterRequestDto;
import com.berat.dto.response.ProfileResponseDto;
import com.berat.dto.response.UserResponseDto;
import com.berat.exception.ErrorType;
import com.berat.exception.PigeonManagerException;
import com.berat.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.berat.constant.EndPoint.*;
@RestController
@RequestMapping(USER_PROFILE)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping(REGISTER)
    public ResponseEntity<Void> register(@RequestBody @Valid UserProfileRegisterRequestDto dto){
        if (!dto.getPassword().equals(dto.getRePassword()))
            throw new PigeonManagerException(ErrorType.PASSWORD_NOT_MATCH);
        userProfileService.register(dto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(LOGIN)
    public ResponseEntity<Void> login(@RequestBody @Valid UserProfileLoginRequestDto dto){
        userProfileService.login(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping(GETALL+ POST_SHARE +BYPOSTID)
    public ResponseEntity<Page<UserResponseDto>> getAllSharedByPostId(@PathVariable Long postId,
                                                                      @RequestParam Optional<Integer> currentPage){
        return ResponseEntity.ok(userProfileService.getAllSharedByPostId(postId, currentPage));
    }
    @GetMapping(GETALL+ POST_LIKE +BYPOSTID)
    public ResponseEntity<Page<UserResponseDto>> getAllLikedByPostId(@PathVariable Long postId,
                                                                     @RequestParam Optional<Integer> currentPage){
        return ResponseEntity.ok(userProfileService.getAllLikedByPostId(postId,currentPage));
    }
    @GetMapping(GETALL+ COMMENT_LIKE +BYCOMMENTID)
    public ResponseEntity<Page<UserResponseDto>> getAllLikedByCommentId(@PathVariable Long commentId,
                                                                        @RequestParam Optional<Integer> currentPage){
        return ResponseEntity.ok(userProfileService.getAllLikedByCommentId(commentId,currentPage));
    }
    @GetMapping(GETALL+ FOLLOWS +BYUSERPROFILEID)
    public ResponseEntity<Page<UserResponseDto>> getAllFollowedByUserProfileId(@PathVariable Long userProfileId,
                                                                               @RequestParam Optional<Integer> currentPage){
        return ResponseEntity.ok(userProfileService.getAllFollowedByUserProfileId(userProfileId, currentPage));
    }
    @GetMapping(GETALL+FOLLOWERS+BYUSERPROFILEID)
    public ResponseEntity<Page<UserResponseDto>> getAllFollowersByUserProfileId(@PathVariable Long userProfileId,
                                                                                @RequestParam Optional<Integer> currentPage){
        return ResponseEntity.ok(userProfileService.getAllFollowersByUserProfileId(userProfileId,currentPage));
    }
    @GetMapping(GETONE+BYUSERPROFILEID)
    public ResponseEntity<ProfileResponseDto> getOneProfile(@PathVariable Long userProfileId){
        return ResponseEntity.ok(userProfileService.getOneProfile(userProfileId));
    }
    @PatchMapping(BLOCK+BYUSERPROFILEID)
    public ResponseEntity<Boolean> blockByUserProfileId(@PathVariable Long userProfileId){
        return ResponseEntity.ok(userProfileService.blockByUserProfileId(userProfileId));
    }
    @PatchMapping(DEACTIVATE+BYUSERPROFILEID)
    public ResponseEntity<Boolean> deActivateByUserProfileId(@PathVariable Long userProfileId){
        return ResponseEntity.ok(userProfileService.deActivateByUserProfileId(userProfileId));
    }
    @DeleteMapping(DELETE+BYUSERPROFILEID)
    public ResponseEntity<Boolean> deleteByUserProfileId(@PathVariable Long userProfileId){
        return ResponseEntity.ok(userProfileService.deleteByUserProfileId(userProfileId));
    }
    @PutMapping(UPDATE+PASSWORD)
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid UpdatePasswordRequestDto dto){
        if (!dto.getNewPassword().equals(dto.getRePassword()))
            throw new PigeonManagerException(ErrorType.PASSWORD_NOT_MATCH);
        userProfileService.updatePassword(dto);
        return ResponseEntity.ok().build();
    }
    @PutMapping(UPDATE+PROFILE)
    public ResponseEntity<Void> updateProfile(@RequestBody UpdateProfileRequestDto dto){
        userProfileService.updateProfile(dto);
        return ResponseEntity.ok().build();
    }


}
