package com.berat.service;

import static com.berat.converter.UserProfileConverter.*;

import com.berat.converter.UserProfileConverter;
import com.berat.dto.request.UpdatePasswordRequestDto;
import com.berat.dto.request.UpdateProfileRequestDto;
import com.berat.dto.request.UserProfileLoginRequestDto;
import com.berat.dto.request.UserProfileRegisterRequestDto;
import com.berat.dto.response.ProfileResponseDto;
import com.berat.dto.response.UserResponseDto;
import com.berat.exception.ErrorType;
import com.berat.exception.PigeonManagerException;
import com.berat.model.UserProfile;
import com.berat.model.UserProfileStatus;
import com.berat.repository.IUserProfileRepository;
import com.berat.security.JwtTokenManager;
import com.berat.util.ServiceManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository userProfileRepository;
    private final JwtTokenManager jwtTokenManager;

    public UserProfileService(IUserProfileRepository userProfileRepository, JwtTokenManager jwtTokenManager) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public UserProfile register(UserProfileRegisterRequestDto dto) {
        if (userProfileRepository.existsByUsername(dto.getUsername()))
            throw new PigeonManagerException(ErrorType.USERNAME_ALREADY_EXIST);
        if (userProfileRepository.existsByEmail(dto.getEmail()))
            throw new PigeonManagerException(ErrorType.EMAIL_ALREADY_EXIST);
        return save(toUserProfile(dto));
    }
    public void login(UserProfileLoginRequestDto dto) {
        Optional<UserProfile> userProfile =
                userProfileRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (userProfile.isEmpty())
            throw new PigeonManagerException(ErrorType.INCORRECT_USERNAME_OR_PASSWORD);
        jwtTokenManager.createToken(userProfile.get().getId());
    }
    public Page<UserResponseDto> getAllSharedByPostId(Long postId,
                                                      Optional<Integer> currentPage){
        Pageable pageable = currentPage.isEmpty() ?
                PageRequest.of(0,10) : PageRequest.of(currentPage.get(), 10);
        return userProfileRepository.getAllSharedByPostId(postId,pageable)
                .map(UserProfileConverter::toUserProfileResponseDto);
    }
    public Page<UserResponseDto> getAllLikedByPostId(Long postId,
                                                     Optional<Integer> currentPage) {
        Pageable pageable = currentPage.isEmpty() ?
                PageRequest.of(0,10) : PageRequest.of(currentPage.get(), 10);
        return userProfileRepository.getAllLikedByPostId(postId,pageable)
                .map(UserProfileConverter::toUserProfileResponseDto);
    }
    public Page<UserResponseDto> getAllLikedByCommentId(Long commentId,
                                                        Optional<Integer> currentPage) {
        Pageable pageable = currentPage.isEmpty() ?
                PageRequest.of(0,10) : PageRequest.of(currentPage.get(), 10);
        return userProfileRepository.getAllLikedByCommentId(commentId,pageable)
                .map(UserProfileConverter::toUserProfileResponseDto);
    }
    public Page<UserResponseDto> getAllFollowedByUserProfileId(Long userProfileId,
                                                               Optional<Integer> currentPage) {
        Pageable pageable = currentPage.isEmpty() ?
                PageRequest.of(0,10) : PageRequest.of(currentPage.get(), 10);
        return userProfileRepository.getAllFollowedByUserProfileId(userProfileId,pageable)
                .map(UserProfileConverter::toUserProfileResponseDto);
    }
    public Page<UserResponseDto> getAllFollowersByUserProfileId(Long userProfileId,
                                                                Optional<Integer> currentPage) {
        Pageable pageable = currentPage.isEmpty() ?
                PageRequest.of(0,10) : PageRequest.of(currentPage.get(), 10);
        return userProfileRepository.getAllFollowersByUserProfileId(userProfileId,pageable)
                .map(UserProfileConverter::toUserProfileResponseDto);
    }
    public Boolean blockByUserProfileId(Long userProfileId) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(userProfileId);
        if (userProfile.isEmpty())
            throw new PigeonManagerException(ErrorType.USER_PROFILE_NOT_FOUND);
        userProfile.get().setStatus(UserProfileStatus.BLOCKED);
        update(userProfile.get());
        return true;
    }
    public Boolean deActivateByUserProfileId(Long userProfileId) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(userProfileId);
        if (userProfile.isEmpty())
            throw new PigeonManagerException(ErrorType.USER_PROFILE_NOT_FOUND);
        userProfile.get().setStatus(UserProfileStatus.NON_ACTIVE);
        update(userProfile.get());
        return true;
    }
    public Boolean deleteByUserProfileId(Long userProfileId) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(userProfileId);
        if (userProfile.isEmpty())
            throw new PigeonManagerException(ErrorType.USER_PROFILE_NOT_FOUND);
        delete(userProfile.get());
        return true;
    }
    public void updatePassword(UpdatePasswordRequestDto dto) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(dto.getId());
        if (userProfile.isEmpty())
            throw new PigeonManagerException(ErrorType.USER_PROFILE_NOT_FOUND);
        if (!dto.getPassword().equals(userProfile.get().getPassword()))
            throw new PigeonManagerException(ErrorType.INVALID_PASSWORD);
        userProfile.get().setPassword(dto.getNewPassword());
        update(userProfile.get());
    }
    public void updateProfile(UpdateProfileRequestDto dto) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(dto.getId());
        if (userProfile.isEmpty())
            throw new PigeonManagerException(ErrorType.USER_PROFILE_NOT_FOUND);
        UserProfile toUpdate = userProfile.get();
        toUpdate.setHeaderPhoto(dto.getHeaderPhoto());
        toUpdate.setPhoto(dto.getPhoto());
        toUpdate.setAbout(dto.getAbout());
        toUpdate.setWebsite(dto.getWebsite());
        update(toUpdate);
    }
    public ProfileResponseDto getOneProfile(Long userProfileId) {
        return toProfileResponseDto(userProfileRepository.findById(userProfileId)
                .orElseThrow(()-> new PigeonManagerException(ErrorType.USER_PROFILE_NOT_FOUND)),
                getAllFollowedByUserProfileId(userProfileId,Optional.empty()).getSize(),
                getAllFollowersByUserProfileId(userProfileId,Optional.empty()).getSize());
    }
}
