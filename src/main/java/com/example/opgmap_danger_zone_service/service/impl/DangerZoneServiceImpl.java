package com.example.opgmap_danger_zone_service.service.impl;

import com.example.opgmap_danger_zone_service.dto.DangerZoneDto;
import com.example.opgmap_danger_zone_service.exception.model.EntityNotExistsException;
import com.example.opgmap_danger_zone_service.exception.utils.ExceptionMessagesGenerator;
import com.example.opgmap_danger_zone_service.mapper.DangerZoneMapper;
import com.example.opgmap_danger_zone_service.model.DangerZone;
import com.example.opgmap_danger_zone_service.model.UserVote;
import com.example.opgmap_danger_zone_service.repository.DangerZoneRepository;
import com.example.opgmap_danger_zone_service.service.DangerZoneService;
import com.example.opgmap_danger_zone_service.service.UserVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class DangerZoneServiceImpl implements DangerZoneService {

    private static final String ENTITY_NAME = "Danger Zone";

    private final DangerZoneRepository dangerZoneRepository;
    private final DangerZoneMapper dangerZoneMapper;
    private final UserVoteService userVoteService;

    @Override
    public DangerZone createDangerZone(UUID userId, DangerZoneDto dangerZoneDto) {
        DangerZone dangerZone = dangerZoneMapper.fromDto(dangerZoneDto);
        dangerZone.setCreated(LocalDateTime.now());
        dangerZone.setUserId(userId);
        dangerZone.setRating(0L);
        return dangerZoneRepository.save(dangerZone);
    }

    @Override
    public DangerZoneDto getDangerZoneById(UUID id) {
        DangerZone dangerZone = dangerZoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(
                        ExceptionMessagesGenerator.generateNotFoundMessage(ENTITY_NAME, id)));
        return dangerZoneMapper.toDto(dangerZone);
    }

    @Override
    public DangerZone changeDangerZoneRating(UUID id, UUID userId, boolean vote) {
        DangerZone dangerZone = dangerZoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(
                        ExceptionMessagesGenerator.generateNotFoundMessage(ENTITY_NAME, id)));

        // check the user has already voted
        Optional<UserVote> userVoteOptional = userVoteService.findVote(id, userId);

        if (userVoteOptional.isEmpty()) {
            UserVote userVote = UserVote.builder()
                    .userId(userId)
                    .dangerZone(dangerZone)
                    .value(vote)
                    .created(LocalDateTime.now())
                    .build();

            dangerZone.setRating(dangerZone.getRating() + (vote ? 1 : -1));
            userVoteService.saveVote(userVote);
        } else {
            UserVote userVote = userVoteOptional.get();
            if (userVote.isValue() == vote) {
                userVoteService.removeVote(userVote);
                dangerZone.setRating(dangerZone.getRating() + (!vote ? 1 : -1));
            } else {
                userVote.setValue(vote);
                dangerZone.setRating(dangerZone.getRating() + (vote ? 2 : -2));
                userVoteService.updateVote(userVote);
            }
        }

        dangerZoneRepository.save(dangerZone);
        return dangerZone;
    }

    @Override
    public DangerZoneDto updateDangerZone(UUID id, DangerZoneDto dangerZoneDto) {
        DangerZone dangerZone = dangerZoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(
                        ExceptionMessagesGenerator.generateNotFoundMessage(ENTITY_NAME, id)));

        dangerZoneDto.setId(id);
        dangerZoneDto.setUserId(dangerZone.getUserId());
        dangerZoneDto.setUpdated(LocalDateTime.now());
        return dangerZoneMapper.toDto(dangerZoneRepository.save(dangerZoneMapper.fromDto(dangerZoneDto)));
    }

    @Override
    public UUID deleteById(UUID id) {
        DangerZone dangerZone = dangerZoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(
                        ExceptionMessagesGenerator.generateNotFoundMessage(ENTITY_NAME, id)));
        dangerZoneRepository.delete(dangerZone);
        return id;
    }
}
