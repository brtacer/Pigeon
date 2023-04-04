package com.berat.service;

import static com.berat.converter.LikeConverter.*;
import com.berat.dto.request.CommentLikeCreateRequestDto;
import com.berat.dto.request.CommentLikeDeleteRequestDto;
import com.berat.dto.request.PostLikeCreateRequestDto;
import com.berat.dto.request.PostLikeDeleteRequestDto;
import com.berat.exception.ErrorType;
import com.berat.exception.PigeonManagerException;
import com.berat.model.Comment;
import com.berat.model.Like;
import com.berat.model.Post;
import com.berat.model.UserProfile;
import com.berat.repository.ILikeRepository;
import com.berat.util.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService extends ServiceManager<Like,Long> {
    private final ILikeRepository likeRepository;
    private final UserProfileService userProfileService;
    private final PostService postService;
    private final CommentService commentService;

    public LikeService(ILikeRepository likeRepository, UserProfileService userProfileService,
                       PostService postService, CommentService commentService) {
        super(likeRepository);
        this.likeRepository = likeRepository;
        this.userProfileService = userProfileService;
        this.postService = postService;
        this.commentService = commentService;
    }
    public void createPostLikes(PostLikeCreateRequestDto dto) {
        Optional<Like> like = likeRepository.findByUserProfileIdAndPostId(dto.getUserProfileId(), dto.getPostId());
        if (like.isPresent())
            throw new PigeonManagerException(ErrorType.LIKE_ALREADY_EXIST);
        Optional<UserProfile>  userProfile = userProfileService.findById(dto.getUserProfileId());
        if (userProfile.isEmpty())
            throw new PigeonManagerException(ErrorType.USER_PROFILE_NOT_FOUND);
        Optional<Post> post = postService.findById(dto.getPostId());
        if (post.isEmpty())
            throw new PigeonManagerException(ErrorType.POST_NOT_FOUND);
        save(toLike(dto,userProfile.get(),post.get()));
    }
    public void createCommentLikes(CommentLikeCreateRequestDto dto) {
        Optional<Like> like = likeRepository.findByUserProfileIdAndCommentId(dto.getUserProfileId(), dto.getCommentId());
        if (like.isPresent())
            throw new PigeonManagerException(ErrorType.LIKE_ALREADY_EXIST);
        Optional<UserProfile>  userProfile = userProfileService.findById(dto.getUserProfileId());
        if (userProfile.isEmpty())
            throw new PigeonManagerException(ErrorType.USER_PROFILE_NOT_FOUND);
        Optional<Comment> comment = commentService.findById(dto.getCommentId());
        if (comment.isEmpty())
            throw new PigeonManagerException(ErrorType.COMMENT_NOT_FOUND);
        save(toLike(dto,userProfile.get(),comment.get()));
    }
    public Boolean deleteCommentLikes(CommentLikeDeleteRequestDto dto) {
        Optional<Like> like = likeRepository.findByUserProfileIdAndCommentId(dto.getUserProfileId(), dto.getCommentId());
        if (like.isEmpty())
            throw new PigeonManagerException(ErrorType.LIKE_NOT_FOUND);
        delete(like.get());
        return true;
    }   public Boolean deletePostLikes(PostLikeDeleteRequestDto dto) {
        Optional<Like> like = likeRepository.findByUserProfileIdAndPostId(dto.getUserProfileId(), dto.getPostId());
        if (like.isEmpty())
            throw new PigeonManagerException(ErrorType.LIKE_NOT_FOUND);
        delete(like.get());
        return true;
    }
}
