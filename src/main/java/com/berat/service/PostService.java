package com.berat.service;

import static com.berat.converter.PostConverter.*;

import com.berat.converter.UserProfileConverter;
import com.berat.dto.request.PostCreateRequestDto;
import com.berat.dto.request.PostUpdateRequestDto;
import com.berat.dto.response.PostResponseDto;
import com.berat.exception.ErrorType;
import com.berat.exception.PigeonManagerException;
import com.berat.model.Post;
import com.berat.model.UserProfile;
import com.berat.repository.IPostRepository;
import com.berat.util.ServiceManager;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService extends ServiceManager<Post,Long> {
    private final IPostRepository postRepository;
    private final UserProfileService userProfileService;

    public PostService(IPostRepository postRepository, UserProfileService userProfileService) {
        super(postRepository);
        this.postRepository = postRepository;
        this.userProfileService = userProfileService;
    }

    public void createPost(PostCreateRequestDto dto) {
        Optional<UserProfile> userProfile = userProfileService.findById(dto.getUserProfileId());
        if (userProfile.isEmpty())
            throw new PigeonManagerException(ErrorType.USER_PROFILE_NOT_FOUND);
        save(toPost(dto,userProfile.get()));
    }

    public Slice<PostResponseDto> getAllPosts(Optional<Long> userProfileId) {
        Slice<PostResponseDto> postResponseDtos;
        Sort sort = Sort.by(Sort.Direction.fromString("DESC"),"tbl_post.create_date");
        Pageable pageable = PageRequest.of(0,10,sort);
        if (userProfileId.isEmpty())
            postResponseDtos=postRepository.getAllPosts(pageable)
                    .map(post-> postResponseDto(post, UserProfileConverter.toUserProfileResponseDto(post.getUserProfile())));
        else
            postResponseDtos=postRepository.findAllByUserProfileId(userProfileId.get(),pageable)
                    .map(post-> postResponseDto(post, UserProfileConverter.toUserProfileResponseDto(post.getUserProfile())));
        return postResponseDtos;
    }

    public PostResponseDto getOnePostByPostId(Long postId) {
        Optional<Post> post = findById(postId);
        if (post.isEmpty())
            throw new PigeonManagerException(ErrorType.POST_NOT_FOUND);
        return postResponseDto(post.get(),UserProfileConverter.toUserProfileResponseDto(post.get().getUserProfile()));

    }

    public void updatePost(PostUpdateRequestDto dto) {
        Optional<Post> post = findById(dto.getPostId());
        if (post.isEmpty())
            throw new PigeonManagerException(ErrorType.POST_NOT_FOUND);
        post.get().setContent(dto.getContent());
        update(post.get());
    }

    public Boolean deleteByPostId(Long postId) {
        Optional<Post> post = findById(postId);
        if (post.isEmpty())
            throw new PigeonManagerException(ErrorType.POST_NOT_FOUND);
        delete(post.get());
        return true;

    }
}
