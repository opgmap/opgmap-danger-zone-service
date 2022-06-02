package com.example.opgmap_danger_zone_service.service.impl;

import com.example.opgmap_danger_zone_service.exception.model.EntityNotExistsException;
import com.example.opgmap_danger_zone_service.exception.utils.ExceptionMessagesGenerator;
import com.example.opgmap_danger_zone_service.model.UserVote;
import com.example.opgmap_danger_zone_service.repository.UserVoteRepository;
import com.example.opgmap_danger_zone_service.service.UserVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserVoteServiceImpl implements UserVoteService {

    private static final String ENTITY_NAME = "User Vote";

    private final UserVoteRepository userVoteRepository;

    @Override
    public UserVote getVote(UUID dangerZoneId, UUID userId) {
        return userVoteRepository.findByUserIdAndDangerZone_Id(userId, dangerZoneId)
                .orElseThrow(() -> new EntityNotExistsException(
                        String.format("%s with user_id = [%s] and danger_zone_id = [%s] not found",
                                ENTITY_NAME, userId.toString(), dangerZoneId.toString())));
    }

    @Override
    public Optional<UserVote> findVote(UUID dangerZoneId, UUID userId) {
        return userVoteRepository.findByUserIdAndDangerZone_Id(userId, dangerZoneId);
    }

    @Override
    public UserVote saveVote(UserVote userVote) {
        return userVoteRepository.save(userVote);
    }

    @Override
    public UserVote updateVote(UserVote userVote) {
        userVoteRepository.findById(userVote.getId())
                .orElseThrow(() -> new EntityNotExistsException(
                        ExceptionMessagesGenerator.generateNotFoundMessage(ENTITY_NAME, userVote.getId())));
        return userVoteRepository.save(userVote);
    }

    @Override
    public void removeVote(UserVote userVote) {
        userVoteRepository.delete(userVote);
    }

    @Override
    public void removeVoteById(UUID id) {
        userVoteRepository.deleteById(id);
    }

}
