package com.example.opgmap_danger_zone_service.service.impl;

import com.example.opgmap_danger_zone_service.dto.DangerZoneDto;
import com.example.opgmap_danger_zone_service.exception.model.EntityNotExistsException;
import com.example.opgmap_danger_zone_service.exception.utils.ExceptionMessagesGenerator;
import com.example.opgmap_danger_zone_service.mapper.DangerZoneMapper;
import com.example.opgmap_danger_zone_service.model.DangerZone;
import com.example.opgmap_danger_zone_service.repository.DangerZoneRepository;
import com.example.opgmap_danger_zone_service.service.DangerZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DangerZoneServiceImpl implements DangerZoneService {

    private static final String ENTITY_NAME = "Danger Zone";

    private final DangerZoneRepository dangerZoneRepository;
    private final DangerZoneMapper dangerZoneMapper;

    @Override
    public UUID createDangerZone(DangerZoneDto dangerZoneDto) {
        DangerZone dangerZone = dangerZoneMapper.fromDto(dangerZoneDto);
        dangerZone.setCreated(LocalDateTime.now());
        dangerZone.setRating(0L);
        dangerZoneRepository.save(dangerZone);
        return dangerZone.getId();
    }

    @Override
    public DangerZoneDto getDangerZoneById(UUID id) {
        DangerZone dangerZone = dangerZoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(
                        ExceptionMessagesGenerator.generateNotFoundMessage(ENTITY_NAME, id)));
        return dangerZoneMapper.toDto(dangerZone);
    }

    @Override
    public UUID changeDangerZoneRating(UUID id, boolean vote) {
        DangerZone dangerZone = dangerZoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(
                        ExceptionMessagesGenerator.generateNotFoundMessage(ENTITY_NAME, id)));
        if (vote) {
            dangerZone.setRating(dangerZone.getRating() + 1);
        } else {
            dangerZone.setRating(dangerZone.getRating() - 1);
        }
        dangerZoneRepository.save(dangerZone);
        return dangerZone.getId();
    }

    @Override
    public DangerZoneDto updateDangerZone(UUID id, DangerZoneDto dangerZoneDto) {
        DangerZone dangerZone = dangerZoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(
                        ExceptionMessagesGenerator.generateNotFoundMessage(ENTITY_NAME, id)));
        dangerZoneDto.setUpdated(LocalDateTime.now());
        return dangerZoneMapper.toDto(dangerZoneRepository.save(dangerZoneMapper.fromDto(dangerZoneDto)));
    }

    @Override
    public String deleteById(UUID id) {
        DangerZone dangerZone = dangerZoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotExistsException(
                        ExceptionMessagesGenerator.generateNotFoundMessage(ENTITY_NAME, id)));
        dangerZoneRepository.deleteById(id);
        return "Опасная зона была удалена";
    }
}
