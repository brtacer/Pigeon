package com.berat.service;

import static com.berat.converter.CommentConverter.*;

import com.berat.converter.UserProfileConverter;
import com.berat.dto.request.CommentCreateRequestDto;
import com.berat.dto.request.CommentUpdateRequestDto;
import com.berat.dto.response.CommentResponseDto;
import com.berat.exception.ErrorType;
import com.berat.exception.PigeonManagerException;
import com.berat.model.Comment;
import com.berat.model.Post;
import com.berat.model.UserProfile;
import com.berat.repository.ICommentRepository;
import com.berat.util.ServiceManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService extends ServiceManager<Comment,Long> {
    private final ICommentRepository commentRepository;
    private final UserProfileService userProfileService;
    private final PostService postService;

    public CommentService(ICommentRepository commentRepository, UserProfileService userProfileService, PostService postService) {
        super(commentRepository);
        this.commentRepository = commentRepository;
        this.userProfileService = userProfileService;
        this.postService = postService;
    }

    public void createComment(CommentCreateRequestDto dto) {
        Optional<UserProfile> userProfile = userProfileService.findById(dto.getUserProfileId());
        if (userProfile.isEmpty())
            throw new PigeonManagerException(ErrorType.USER_PROFILE_NOT_FOUND);
        Optional<Post> post = postService.findById(dto.getPostId());
        if (post.isEmpty())
            throw new PigeonManagerException(ErrorType.POST_NOT_FOUND);
        save(toComment(dto.getContent(), userProfile.get(), post.get()));
    }

    public Page<CommentResponseDto> getAllByPostId(Long postId,Integer currentPage) {
        Sort sort = Sort.by(Sort.Direction.fromString("ASC"),"tbl_comment.create_date");
        Pageable pageable = PageRequest.of(currentPage,10,sort);
        return commentRepository.findAllByPostId(postId,pageable)
                .map(c-> fromComment(c, UserProfileConverter.toUserProfileResponseDto(c.getUserProfile())));
    }

    public void updateComment(CommentUpdateRequestDto dto) {
        Optional<Comment> comment = findById(dto.getCommentId());
        if (comment.isEmpty())
            throw new PigeonManagerException(ErrorType.COMMENT_NOT_FOUND);
        comment.get().setContent(dto.getContent());
        update(comment.get());
    }

    public Boolean deleteByCommentId(Long commentId) {
        Optional<Comment> comment = findById(commentId);
        if (comment.isEmpty())
            throw new PigeonManagerException(ErrorType.COMMENT_NOT_FOUND);
        delete(comment.get());
        return true;
    }
}
