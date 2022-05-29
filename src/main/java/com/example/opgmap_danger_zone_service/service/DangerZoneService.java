package com.example.opgmap_danger_zone_service.service;

import com.example.opgmap_danger_zone_service.dto.DangerZoneDto;

import java.util.UUID;

public interface DangerZoneService {

    UUID createDangerZone(DangerZoneDto dangerZoneDto);

    DangerZoneDto getDangerZoneById(UUID id);

    UUID changeDangerZoneRating(UUID id, boolean vote);

    DangerZoneDto updateDangerZone(UUID id, DangerZoneDto dangerZoneDto);

    void deleteById(UUID id);
}
