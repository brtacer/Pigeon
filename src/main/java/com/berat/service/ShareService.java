package com.berat.service;

import com.berat.converter.ShareConverter;
import com.berat.dto.request.ShareCreateRequestDto;
import com.berat.dto.request.ShareDeleteRequestDto;
import com.berat.exception.ErrorType;
import com.berat.exception.PigeonManagerException;
import com.berat.model.Post;
import com.berat.model.Share;
import com.berat.model.UserProfile;
import com.berat.repository.IShareRepository;
import com.berat.util.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShareService extends ServiceManager<Share,Long> {
    private final IShareRepository shareRepository;
    private final UserProfileService userProfileService;
    private final PostService postService;

    public ShareService(IShareRepository shareRepository, UserProfileService userProfileService, PostService postService) {
        super(shareRepository);
        this.shareRepository = shareRepository;
        this.userProfileService = userProfileService;
        this.postService = postService;
    }

    public void createShare(ShareCreateRequestDto dto) {
        Optional<Share> share = shareRepository.findByUserProfileIdAndPostId(dto.getUserProfileId(), dto.getPostId());
        if(share.isPresent())
            throw new PigeonManagerException(ErrorType.SHARE_ALREADY_EXIST);
        Optional<UserProfile> userProfile = userProfileService.findById(dto.getUserProfileId());
        if (userProfile.isEmpty())
            throw new PigeonManagerException(ErrorType.USER_PROFILE_NOT_FOUND);
        Optional<Post> post = postService.findById(dto.getPostId());
        if (post.isEmpty())
            throw new PigeonManagerException(ErrorType.POST_NOT_FOUND);
        save(ShareConverter.toShare(dto,userProfile.get(),post.get()));
    }

    public Boolean deleteShare(ShareDeleteRequestDto dto) {
        Optional<Share> share = shareRepository.findByUserProfileIdAndPostId(dto.getUserProfileId(), dto.getPostId());
        if (share.isEmpty())
            throw new PigeonManagerException(ErrorType.SHARE_NOT_FOUND);
        delete(share.get());
        return true;
    }
}
