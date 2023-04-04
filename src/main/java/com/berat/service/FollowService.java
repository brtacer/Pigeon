package com.berat.service;

import com.berat.converter.FollowConverter;
import com.berat.dto.request.FollowCreateRequestDto;
import com.berat.dto.request.FollowDeleteRequestDto;
import com.berat.exception.ErrorType;
import com.berat.exception.PigeonManagerException;
import com.berat.model.Follow;
import com.berat.repository.IFollowRepository;
import com.berat.util.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowService extends ServiceManager<Follow,Long> {
    private final IFollowRepository followRepository;

    public FollowService(IFollowRepository followRepository) {
        super(followRepository);
        this.followRepository = followRepository;
    }


    public void createFollow(FollowCreateRequestDto dto) {
        Optional<Follow> follow = followRepository.findByFollowingIdAndAndFollowerId(dto.getFollowingId(), dto.getFollowerId());
        if (follow.isPresent())
            throw new PigeonManagerException(ErrorType.FOLLOW_ALREADY_EXIST);
        save(FollowConverter.toFollow(dto));
    }

    public Boolean deleteFollow(FollowDeleteRequestDto dto) {
        Optional<Follow> follow = followRepository.findByFollowingIdAndAndFollowerId(dto.getFollowingId(), dto.getFollowerId());
        if (follow.isEmpty())
            throw new PigeonManagerException(ErrorType.FOLLOW_NOT_FOUND);
        delete(follow.get());
        return true;
    }
}
